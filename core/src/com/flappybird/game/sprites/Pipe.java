package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pipe {
    public static final int FLUCTUATION = 130;
    public static final int PIPE_WIDTH = 52;
    public static final int PIPE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture topPipe, botPipe;
    private Vector2 posTopPipe, posBotPipe;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Pipe(float x) {
        topPipe = new Texture("toptube.png"); // TODO: Make texture static
        botPipe = new Texture("bottomtube.png");
        rand = new Random();

        float topPipeY = rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING;
        float botPipeY = topPipeY - PIPE_GAP - botPipe.getHeight();
        this.posTopPipe = new Vector2(x, topPipeY);
        this.posBotPipe = new Vector2(x, botPipeY);

        this.boundsTop = new Rectangle(posTopPipe.x, posTopPipe.y, topPipe.getWidth(), topPipe.getHeight());
        this.boundsBot = new Rectangle(posBotPipe.x, posBotPipe.y, botPipe.getWidth(), botPipe.getHeight());
    }

    public void reposition(float x) {
        float topPipeY = rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING;
        float botPipeY = topPipeY - PIPE_GAP - botPipe.getHeight();

        this.posTopPipe.set(x, topPipeY);
        this.posBotPipe.set(x, botPipeY);
        this.boundsTop.setPosition(x, topPipeY);
        this.boundsBot.setPosition(x, botPipeY);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topPipe.dispose();
        botPipe.dispose();
    }

    public Texture getTopPipe() {
        return topPipe;
    }

    public Texture getBotPipe() {
        return botPipe;
    }

    public Vector2 getPosTopPipe() {
        return posTopPipe;
    }

    public Vector2 getPosBotPipe() {
        return posBotPipe;
    }
}
