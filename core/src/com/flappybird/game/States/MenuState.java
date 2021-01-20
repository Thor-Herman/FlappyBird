package com.flappybird.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.FlappyBird;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // "Open" the SB for drawing
        sb.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        float playBtnX = FlappyBird.WIDTH / 2 - playBtn.getWidth() / 2;
        float playBtnY = FlappyBird.HEIGHT / 2;
        sb.draw(playBtn, playBtnX, playBtnY); // If no limits are given it will use default img val
        sb.end(); // "Close" the SB box
    }
}
