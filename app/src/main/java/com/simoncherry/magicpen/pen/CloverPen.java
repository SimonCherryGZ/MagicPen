package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.view.animation.DecelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
import com.simoncherry.magicpen.R;

/**
 * Created by Simon on 2017/3/18.
 */

public class CloverPen extends BasePen {

    public CloverPen(Activity activity, int backgroundResId) {
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
                ps = createQuarterLeaf(x, y);
                break;
            case 1:
                ps = createFullLeaf(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createFullLeaf(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.leaf1, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.4f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 15);
        return ps;
    }

    private ParticleSystem createQuarterLeaf(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.leaf2, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 0.8f);
        ps.setSpeedRange(0.02f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 10);
        return ps;
    }
}
