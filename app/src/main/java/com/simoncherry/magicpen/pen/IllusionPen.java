package com.simoncherry.magicpen.pen;

import android.app.Activity;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.simoncherry.magicpen.R;

/**
 * Created by Simon on 2017/3/19.
 */

public class IllusionPen extends BasePen {

    public IllusionPen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);
    }

    @Override
    int getParticleKind() {
        return 4;
    }

    @Override
    ParticleSystem createParticleSystem(int x, int y, int type) {
        ParticleSystem ps;
        switch (type) {
            case 0:
                ps = createPurpleLight(x, y);
                break;
            case 1:
                ps = createBlueLight(x, y);
                break;
            case 2:
                ps = createGreenLight(x, y);
                break;
            case 3:
                ps = createYellowLight(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createPurpleLight(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.light_ball_purple, 2000, backgroundResId);
        ps.addModifier(new AlphaModifier(180, 127, 0, 2000));
        ps.addModifier(new ScaleModifier(0.2f, 2.0f, 0, 2000));
        ps.setSpeedRange(0.2f, 0.3f);
        ps.setScaleRange(0.8f, 1.5f);
        ps.emit(x, y, 7);
        return ps;
    }

    private ParticleSystem createBlueLight(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.light_ball_blue, 2000, backgroundResId);
        ps.addModifier(new AlphaModifier(180, 127, 0, 2000));
        ps.addModifier(new ScaleModifier(0.2f, 2.0f, 0, 2000));
        ps.setSpeedRange(0.2f, 0.3f);
        ps.setScaleRange(0.8f, 1.5f);
        ps.emit(x, y, 7);
        return ps;
    }

    private ParticleSystem createGreenLight(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.light_ball_green, 2000, backgroundResId);
        ps.addModifier(new AlphaModifier(180, 127, 0, 2000));
        ps.addModifier(new ScaleModifier(0.2f, 2.0f, 0, 2000));
        ps.setSpeedRange(0.2f, 0.3f);
        ps.setScaleRange(0.8f, 1.5f);
        ps.emit(x, y, 7);
        return ps;
    }

    private ParticleSystem createYellowLight(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.light_ball_yellow, 2000, backgroundResId);
        ps.addModifier(new AlphaModifier(180, 127, 0, 2000));
        ps.addModifier(new ScaleModifier(0.2f, 2.0f, 0, 2000));
        ps.setSpeedRange(0.2f, 0.3f);
        ps.setScaleRange(0.8f, 1.5f);
        ps.emit(x, y, 7);
        return ps;
    }
}
