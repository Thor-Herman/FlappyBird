package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    public static final int GRAVITY = -15;
    private Vector3 pos;
    private Vector3 velocity;
    private Texture birdTexture;

    public Bird(int x, int y) {
        this.pos = new Vector3(x, y, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.birdTexture = new Texture("bird.png");
    }

    public void update(float dt) {
        if (pos.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt); // Scales everything by dt
        pos.add(0, velocity.y, 0);
        final boolean HIT_SCREEN_BOTTOM = pos.y < 0; // Needs to be declared after pos.add
        if (HIT_SCREEN_BOTTOM)
            pos.y = 0;
        velocity.scl(1 / dt); // Reverses previous scale
    }

    public void jump() {
        velocity.y = 250;
    }

    public Vector3 getPos() {
        return pos;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }
}
