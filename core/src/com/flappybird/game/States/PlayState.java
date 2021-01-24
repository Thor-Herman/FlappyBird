package com.flappybird.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBird;
import com.flappybird.game.sprites.Bird;
import com.flappybird.game.sprites.Pipe;

public class PlayState extends State {
    public static final int PIPE_SPACING = 125; // Space between pipes including themselves
    public static final int PIPE_COUNT = 4; // Total count of pipes in game
    public static final int CAM_OFFSET = 80;
    public static final int GROUND_Y_OFFSET = -60;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Pipe> pipes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        pipes = new Array<>();
        for (int i = 1; i <= PIPE_COUNT; i++) {
            float x = i * (PIPE_SPACING + Pipe.PIPE_WIDTH);
            pipes.add(new Pipe(x));
        }
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        float groundStartPos = cam.position.x - cam.viewportWidth / 2;
        groundPos1 = new Vector2(groundStartPos, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(groundStartPos + ground.getWidth(), GROUND_Y_OFFSET);
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
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPos().x + CAM_OFFSET;
        for (Pipe pipe : pipes) {
            float pipePosX = pipe.getPosTopPipe().x;
            float screenLeftPos = cam.position.x - cam.viewportWidth / 2;
            float pipeRightPos = pipePosX + Pipe.PIPE_WIDTH;
            if (screenLeftPos > pipeRightPos) {
                float rightOfPipesPos = pipePosX + (Pipe.PIPE_WIDTH + PIPE_SPACING) * PIPE_COUNT;
                pipe.reposition(rightOfPipesPos);
            }
            if (pipe.collides(bird.getBounds())) {
                gsm.setState(new PlayState(gsm));
                break;
            }
        }
        if (bird.getPos().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.setState(new PlayState(gsm));
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        float bgX = cam.position.x - cam.viewportWidth / 2;
        float bgY = 0;
        sb.draw(bg, bgX, bgY);
        sb.draw(bird.getTexture(), bird.getPos().x, bird.getPos().y);
        for (Pipe pipe : pipes) {
            sb.draw(pipe.getTopPipe(), pipe.getPosTopPipe().x, pipe.getPosTopPipe().y);
            sb.draw(pipe.getBotPipe(), pipe.getPosBotPipe().x, pipe.getPosBotPipe().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Pipe pipe: pipes) {
            pipe.dispose();
        }
    }

    private void updateGround() {
        if (cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
