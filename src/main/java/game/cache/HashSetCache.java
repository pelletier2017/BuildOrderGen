package game.cache;

import game.state.GameState;

import java.util.HashSet;
import java.util.Set;

public class HashSetCache implements GameStateCache {

    Set<GameState> gameStatesSeen = new HashSet<>();

    @Override
    public boolean contains(GameState gameState) {
        return gameStatesSeen.contains(gameState);
    }

    @Override
    public void add(GameState gameState) {
        gameStatesSeen.add(gameState);
    }

    @Override
    public int size() {
        return gameStatesSeen.size();
    }
}
