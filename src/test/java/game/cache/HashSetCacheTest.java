package game.cache;

import buildorder.BuildOrder;
import buildorder.ReusedBuildOrderArrayList;
import game.state.GameState;
import game.state.UnitCollection;
import game.stats.GameUnit;
import game.timeline.SimpleTimeline;
import game.timeline.Timeline;
import game.units.ZergUnits;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashSetCacheTest {

    @BeforeClass
    public static void setup() {
        ZergUnits.initializeFromJson();
    }

    private BuildOrder buildOrder() {
        return new ReusedBuildOrderArrayList();
    }

    private Timeline<GameUnit> timeline() {
        return new SimpleTimeline<>();
    }

    @Test
    public void testSingle() {
        UnitCollection collection = new UnitCollection();
        collection.add(ZergUnits.getOverlord(), 3);
        collection.add(ZergUnits.getDrone(), 10);

        GameState gameState = new GameState(collection, buildOrder(), timeline());

        GameStateCache cache = new HashSetCache();
        cache.add(gameState);

        assertTrue(cache.contains(gameState));
        assertEquals(1, cache.size());
    }

    @Test
    public void testSameCollection() {
        UnitCollection collectionA = new UnitCollection();
        collectionA.add(ZergUnits.getOverlord(), 3);
        collectionA.add(ZergUnits.getDrone(), 10);

        BuildOrder buildOrder = new ReusedBuildOrderArrayList();
        Timeline<GameUnit> gameUnitTimeline = new SimpleTimeline<>();

        GameState gameStateA = new GameState(collectionA, buildOrder(), timeline());
        GameState gameStateB = new GameState(collectionA, buildOrder(), timeline());
        GameStateCache cache = new HashSetCache();

        cache.add(gameStateA);
        cache.add(gameStateB);

        assertTrue(cache.contains(gameStateA));
        assertTrue(cache.contains(gameStateB));
        assertEquals(1, cache.size());
    }

    @Test
    public void testMutatedCollection() {
        UnitCollection collectionA = new UnitCollection();
        collectionA.add(ZergUnits.getOverlord(), 3);
        collectionA.add(ZergUnits.getDrone(), 10);

        GameState gameStateA = new GameState(collectionA, buildOrder(), timeline());
        GameStateCache cache = new HashSetCache();

        cache.add(gameStateA);
        collectionA.add(ZergUnits.getDrone(), 1);

        // after changing collection, the state hash should be different
        assertFalse(cache.contains(gameStateA));

        cache.add(gameStateA);

        // should now be both in the set
        assertTrue(cache.contains(gameStateA));
        assertEquals(2, cache.size());
    }

    @Test
    public void testContainsSameUnits() {
        UnitCollection collectionA = new UnitCollection();
        collectionA.add(ZergUnits.getOverlord(), 3);
        collectionA.add(ZergUnits.getDrone(), 10);

        UnitCollection collectionB = new UnitCollection();
        collectionB.add(ZergUnits.getOverlord(), 3);
        collectionB.add(ZergUnits.getDrone(), 10);

        GameState gameStateA = new GameState(collectionA, buildOrder(), timeline());
        GameState gameStateB = new GameState(collectionB, buildOrder(), timeline());
        GameStateCache cache = new HashSetCache();

        cache.add(gameStateA);
        cache.add(gameStateB);

        assertTrue(cache.contains(gameStateA));
        assertTrue(cache.contains(gameStateB));
        assertEquals(1, cache.size());
        // TODO this fails...
    }

    @Test
    public void testMutatedStateNotContained() {
        UnitCollection collection = new UnitCollection();
        collection.add(ZergUnits.getOverlord(), 3);
        collection.add(ZergUnits.getDrone(), 10);

        GameState gameState = new GameState(collection, buildOrder(), timeline());
        GameStateCache cache = new HashSetCache();

        cache.add(gameState);
        gameState.nextSecond();

        assertFalse(cache.contains(gameState));
    }

    @Test
    public void testAddingMutatedState() {
        UnitCollection collection = new UnitCollection();
        collection.add(ZergUnits.getOverlord(), 3);
        collection.add(ZergUnits.getDrone(), 10);

        GameState gameState = new GameState(collection, buildOrder(), timeline());
        GameStateCache cache = new HashSetCache();

        cache.add(gameState);
        gameState.nextSecond();
        cache.add(gameState);

        assertTrue(cache.contains(gameState));
        assertEquals(2, cache.size());
    }
}