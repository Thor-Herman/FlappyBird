package com.flappybird.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.FlappyBird;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.setState(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); // Always checks this

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // "Open" the SB for drawing
        sb.draw(background, 0, 0);
        float playBtnX = cam.position.x - playBtn.getWidth() / 2;
        float playBtnY = cam.position.y;
        sb.draw(playBtn, playBtnX, playBtnY); // If no limits are given it will use default img val
        sb.end(); // "Close" the SB box
    }

    @Override
    public void dispose() { // Called when transitioning states
        background.dispose();
        playBtn.dispose();
    }
}
