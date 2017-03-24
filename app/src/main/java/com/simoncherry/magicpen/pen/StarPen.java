package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.CurveModifier;
import com.simoncherry.magicpen.R;

/**
 * Created by Simon on 2017/3/18.
 */

public class StarPen extends BasePen {

    public StarPen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);
    }

    @Override
    int getParticleKind() {
        return 1;
    }

    @Override
    ParticleSystem createParticleSystem(int x, int y, int type) {
        ParticleSystem ps;
        switch (type) {
            case 0:
                ps = createStar(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createStar(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 250, R.drawable.star, 2500, backgroundResId);
        ps.setScaleRange(0.3f, 1.0f);
        ps.setSpeedRange(0.08f, 0.08f);
        ps.setRotationSpeedRange(180, 360);
        ps.setInitialRotationRange(0, 180);
        ps.addModifier(new CurveModifier(0.0001f));
        ps.setFadeOut(1000, new AccelerateInterpolator());
        ps.emit(x, y, 25);
        return ps;
    }
}
