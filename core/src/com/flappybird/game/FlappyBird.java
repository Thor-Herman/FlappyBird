package com.flappybird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.States.GameStateManager;
import com.flappybird.game.States.MenuState;
import com.flappybird.game.States.State;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch sb; // Should only have a single sb
	Texture img;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		State menuState = new MenuState(gsm);
		gsm.push(menuState);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Wipes screen and redraws everything
		float dt = Gdx.graphics.getDeltaTime();
		gsm.update(dt);
		gsm.render(this.sb);
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		img.dispose();
	}
}
