package game.timeline;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleTimelineTest {

    @Test
    public void testSimple() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.add("this", 1);

        assertEquals(Collections.singletonList("this"), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
    }

    @Test
    public void testPrev() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.add("that", 1);

        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        assertEquals(Collections.singletonList("that"), timeline.previousMoment());
        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
    }

    @Test
    public void testPrevBeforeAddShouldEraseHistory() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.nextMoment();
        // time 1

        timeline.add("that", 1);

        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        // time 2
        assertEquals(Collections.singletonList("that"), timeline.previousMoment());
        // time 1
        assertEquals(Collections.emptyList(), timeline.previousMoment());
        // time 0
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        // time 1
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        // time 2
    }

    @Test
    public void testEventLargerThanOne() {
        Timeline<String> timeline = new SimpleTimeline<>();

        timeline.add("that", 3);

        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        assertEquals(Collections.singletonList("that"), timeline.previousMoment());
        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
    }

    @Test(expected = IllegalStateException.class)
    public void testNegativeTimeThrowsException() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.nextMoment();
        timeline.previousMoment();
        timeline.previousMoment();
    }

    @Test
    public void testAddTwo() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.add("this", 1);
        timeline.add("that", 1);

        assertEquals(Arrays.asList("this", "that"), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
    }

    @Test
    public void testAddTwoDifferentTimes() {
        Timeline<String> timeline = new SimpleTimeline<>();
        timeline.add("this", 3);
        timeline.add("that", 6);

        nextN(timeline, 2);
        assertEquals(Collections.singletonList("this"), timeline.nextMoment());
        nextN(timeline, 2);
        assertEquals(Collections.singletonList("that"), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
        assertEquals(Collections.emptyList(), timeline.nextMoment());
    }

    private void nextN(Timeline timeline, int n) {
        for (int i = 0; i < n; i++) {
            timeline.nextMoment();
        }
    }

    private void prevN(Timeline timeline, int n) {
        for (int i = 0; i < n; i++) {
            timeline.previousMoment();
        }
    }

}