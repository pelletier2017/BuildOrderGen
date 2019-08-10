package game.cache;

import game.state.GameState;

public class EmptyCache implements GameStateCache {

    @Override
    public boolean contains(GameState gameState) {
        return false;
    }

    @Override
    public void add(GameState gameState) {
    }
}
