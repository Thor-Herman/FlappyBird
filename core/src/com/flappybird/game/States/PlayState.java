package com.flappybird.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.FlappyBird;
import com.flappybird.game.sprites.Bird;

public class PlayState extends State {
    private Bird bird;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined); 
        sb.begin();
        sb.draw(bird.getBirdTexture(), bird.getPos().x, bird.getPos().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
