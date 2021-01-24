package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime; // How long a frame can stay in view
    private float currentFrameTime; // How long current frame has been in view
    private int frameCount; // No of frames in animation
    private int frame; // Current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<>();
        int frameWidth = region.getRegionWidth() / frameCount; // Region contains all the frames
        int frameHeight = region.getRegionHeight();
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth,0, frameWidth, frameHeight));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
