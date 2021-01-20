package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pipe {
    public static final int FLUCTUATION = 130;
    public static final int PIPE_WIDTH = 52;
    public static final int PIPE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture topPipe, bottomPipe;
    private Vector2 posTopPipe, posBotPipe;
    private Random rand;

    public Pipe(float x) {
        topPipe = new Texture("toptube.png");
        bottomPipe = new Texture("bottomtube.png");
        rand = new Random();
        this.reposition(x);
    }

    public void reposition(float x) {
        float topPipeY = rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING;
        float botPipeY = topPipeY - PIPE_GAP - bottomPipe.getHeight();
        this.posTopPipe = new Vector2(x, topPipeY);
        this.posBotPipe = new Vector2(x, botPipeY);
    }

    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBotPipe() {
        return bottomPipe;
    }

    public Vector2 getPosTopPipe() {
        return posTopPipe;
    }

    public Vector2 getPosBotPipe() {
        return posBotPipe;
    }
}
