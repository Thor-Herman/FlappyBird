package com.flappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private static final int ANIMATION_FRAMES = 3;

    private Vector3 pos;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;
    private Sound flapSound;

    public Bird(int x, int y) {
        this.pos = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), ANIMATION_FRAMES, 0.5f);
        this.bounds = new Rectangle(x, y, texture.getWidth() / ANIMATION_FRAMES, this.texture.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (pos.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt); // Scales everything by dt
        pos.add(MOVEMENT * dt, velocity.y, 0);
        final boolean HIT_SCREEN_BOTTOM = pos.y < 0; // Needs to be declared after pos.add
        if (HIT_SCREEN_BOTTOM)
            pos.y = 0;
        velocity.scl(1 / dt); // Reverses previous scale
        bounds.setPosition(pos.x, pos.y);
    }

    public void jump() {
        velocity.y = 250;
        flapSound.play(0.4f);
    }

    public void dispose() {
        texture.dispose();
        flapSound.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPos() {
        return pos;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
}
