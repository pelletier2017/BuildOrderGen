package game.timeline;

import java.util.List;

public interface Timeline<T> {
    // returns List<T> that just finished starting next moment
    List<T> nextMoment();

    // returns List<T> that are no longer fininished starting last moment
    List<T> previousMoment();

    // add an event ranging from [n, n+time)
    void add(T object, int time);

    // seconds in game
    int seconds();
}
