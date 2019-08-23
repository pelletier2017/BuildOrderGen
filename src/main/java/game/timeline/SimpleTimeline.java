package game.timeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleTimeline<T> implements Timeline<T> {

    private List<Event> currentEvents = new ArrayList<>();
    private List<Event> eventHistory = new ArrayList<>();
    private int time = 0;

    @Override
    public void add(T object, int time) {
        Event event = new Event(object, time);
        currentEvents.add(event);
    }

    @Override
    public int seconds() {
        return time;
    }

    @Override
    public List<T> nextMoment() {
        time++;

        List<T> recentlyFinished = new ArrayList<>();
        Iterator<Event> eventIterator = currentEvents.listIterator();

        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (time >= event.timeEnd) {
                eventHistory.add(event);
                recentlyFinished.add(event.object);
                eventIterator.remove();
            }
        }
        return recentlyFinished;
    }

    @Override
    public List<T> previousMoment() {
        time--;
        if (time < 0) {
            throw new IllegalStateException("Cannot go before time 0");
        }
        currentEvents.removeIf(event -> time < event.timeStart);

        List<T> recentlyUnfinished = new ArrayList<>();
        for (int i = eventHistory.size() - 1; i >= 0; i--) {
            Event event = eventHistory.get(i);
            if (time < event.timeEnd) {
                currentEvents.add(event);
                recentlyUnfinished.add(event.object);
                eventHistory.remove(i);
            }
        }
        return recentlyUnfinished;
    }

    private class Event {
        public int timeStart;
        public int timeEnd;
        public T object;

        public Event(T object, int timeLength) {
            this.timeStart = SimpleTimeline.this.time;
            this.timeEnd = SimpleTimeline.this.time + timeLength;
            this.object = object;
        }
    }
}
