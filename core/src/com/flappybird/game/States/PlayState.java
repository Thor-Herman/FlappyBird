package com.flappybird.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBird;
import com.flappybird.game.sprites.Bird;
import com.flappybird.game.sprites.Pipe;

public class PlayState extends State {
    public static final int PIPE_SPACING = 125; // Space between pipes including themselves
    public static final int PIPE_COUNT = 4; // Total count of pipes in game

    private Bird bird;
    private Texture bg;

    private Array<Pipe> pipes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        pipes = new Array<>();
        for (int i = 0; i < PIPE_COUNT; i++) {
            float x = i * (PIPE_SPACING + Pipe.PIPE_GAP);
            pipes.add(new Pipe(x));
        }
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        for (Pipe pipe : pipes) {
            float screenLeftPos = cam.position.x - cam.viewportWidth / 2;
            float pipeRightPos = pipe.getPosTopPipe().x + pipe.getTopPipe().getWidth();
            if (screenLeftPos > pipeRightPos) {
                float rightOfPipesPos = pipe.getPosTopPipe().x + (pipe.getTopPipe().getWidth() + PIPE_SPACING) * PIPE_COUNT;
                pipe.reposition(rightOfPipesPos);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        float bgX = cam.position.x - cam.viewportWidth / 2;
        float bgY = 0;
        sb.draw(bg, bgX, bgY);
        sb.draw(bird.getBirdTexture(), bird.getPos().x, bird.getPos().y);
        for (Pipe pipe : pipes) {
            sb.draw(pipe.getTopPipe(), pipe.getPosTopPipe().x, pipe.getPosTopPipe().y);
            sb.draw(pipe.getBotPipe(), pipe.getPosBotPipe().x, pipe.getPosBotPipe().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
