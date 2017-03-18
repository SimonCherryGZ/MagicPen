package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.view.animation.DecelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
import com.simoncherry.magicpen.R;

/**
 * Created by Simon on 2017/3/18.
 */

public class FlowerPen extends BasePen {

    public FlowerPen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);
    }

    @Override
    int getParticleKind() {
        return 5;
    }

    @Override
    ParticleSystem createParticleSystem(int x, int y, int type) {
        ParticleSystem ps;
        switch (type) {
            case 0:
                ps = createFlower1(x, y);
                break;
            case 1:
                ps = createFlower2(x, y);
                break;
            case 2:
                ps = createFlower3(x, y);
                break;
            case 3:
                ps = createFlower4(x, y);
                break;
            case 4:
                ps = createFlower5(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createFlower1(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.flower1, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }

    private ParticleSystem createFlower2(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.flower2, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }

    private ParticleSystem createFlower3(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.flower3, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }

    private ParticleSystem createFlower4(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.flower4, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }

    private ParticleSystem createFlower5(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.flower5, 1600, backgroundResId);
        ps.setScaleRange(0.2f, 1.0f);
        ps.setSpeedRange(0.01f, 0.05f);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new DecelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }
}
