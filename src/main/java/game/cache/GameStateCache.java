package game.cache;

import game.state.GameState;

public interface GameStateCache {
    boolean contains(GameState gameState);
    void add(GameState gameState);
    int size();
}
