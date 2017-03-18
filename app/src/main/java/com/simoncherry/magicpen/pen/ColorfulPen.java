package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.simoncherry.magicpen.R;

/**
 * Created by Simon on 2017/3/18.
 */

public class ColorfulPen extends BasePen {


    public ColorfulPen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);
    }

    @Override
    int getParticleKind() {
        return 2;
    }

    @Override
    ParticleSystem createParticleSystem(int x, int y, int type) {
        ParticleSystem ps;
        switch (type) {
            case 0:
                ps = createPinkRing(x, y);
                break;
            case 1:
                ps = createYellowRing(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createPinkRing(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 180, R.drawable.light_ring_pink, 1000, backgroundResId);
        ps.setSpeedRange(0.1f, 0.25f);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.addModifier(new ScaleModifier(1.5f, 0.0f, 0, 1000));
        ps.emit(x, y, 30);
        return ps;
    }

    private ParticleSystem createYellowRing(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 180, R.drawable.light_ring_yellow, 1000, backgroundResId);
        //ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.1f, 0.25f);
        //ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.addModifier(new ScaleModifier(1.5f, 0.0f, 0, 1000));
        ps.emit(x, y, 30);
        return ps;
    }
}
