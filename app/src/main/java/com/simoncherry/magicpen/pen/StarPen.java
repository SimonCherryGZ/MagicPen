package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.view.animation.AccelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
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
        ParticleSystem ps = new ParticleSystem(activity, 50, R.drawable.star, 2500, backgroundResId);
        ps.setScaleRange(0.3f, 1.2f);
        ps.setSpeedRange(0.10f, 0.2f);
        ps.setRotationSpeedRange(180, 360);
        ps.setInitialRotationRange(0, 180);
        ps.setFadeOut(1000, new AccelerateInterpolator());
        ps.emit(x, y, 5);
        return ps;
    }
}
