package com.plattysoft.leonids.modifiers;

import com.plattysoft.leonids.Particle;

import java.util.Random;

/**
 * Created by Simon on 2017/3/25.
 */

public class BezierModifier implements ParticleModifier {

    private int screenWidth;

    public BezierModifier(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    @Override
    public void apply(Particle particle, long miliseconds) {
        long seed = (long) (particle.mInitialX * particle.mInitialY);
        float start = screenWidth/2f;
        float end = screenWidth * new Random(seed).nextFloat();
        float secondX = screenWidth * new Random(seed).nextFloat();
        float thirdX = screenWidth * new Random(seed).nextFloat();

        particle.mCurrentX += (start * (1 - miliseconds) * (1 - miliseconds) * (1 - miliseconds)
                + secondX * 3 * miliseconds * (1 - miliseconds) * (1 - miliseconds)
                + thirdX * 3 * (1 - miliseconds) * miliseconds * miliseconds
                + end * miliseconds * miliseconds * miliseconds) * 0.000000001f;
    }
}
