package com.flappybird.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager() {
        this.states = new Stack<> ();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void setState(State state) {
        this.pop();
        this.push(state);
    }

    public void update(float dt) {
        this.states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        this.states.peek().render(sb);
    }
}
