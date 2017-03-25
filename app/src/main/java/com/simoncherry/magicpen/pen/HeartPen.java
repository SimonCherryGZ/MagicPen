package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.simoncherry.magicpen.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Simon on 2017/3/24.
 */

public class HeartPen extends BasePen {

    private int screenWidth = 200;

    public HeartPen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
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
                ps = createHeart1(x, y);
                break;
            case 1:
                ps = createHeart2(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createHeart1(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.heart1_swing, 2000, backgroundResId);
        ps.setSpeedModuleAndAngleRange(0.1f, 0.2f, 210, 330);
        ps.setScaleRange(0.01f, 0.03f);
        ps.setAcceleration(0.0001f, 270);
        ps.addModifier(new ScaleModifier(0.4f, 0.01f, 0, 2000));
        ps.setFadeOut(1000, new AccelerateInterpolator());
        ps.emit(x, y, 10);
        return ps;
    }

    private ParticleSystem createHeart2(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 100, R.drawable.heart2_swing, 2000, backgroundResId);
        ps.setSpeedModuleAndAngleRange(0.1f, 0.2f, 210, 330);
        ps.setScaleRange(0.01f, 0.03f);
        ps.setAcceleration(0.0001f, 270);
        ps.addModifier(new ScaleModifier(0.4f, 0.01f, 0, 2000));
        ps.setFadeOut(1000, new AccelerateInterpolator());
        ps.emit(x, y, 10);
        return ps;
    }

    @Override
    public void updateParticleSystem(int x, int y) {
        for (int i=0; i<particleList.size(); i++) {
            List<ParticleSystem> itemList = particleList.get(i);
            if (itemList.size() > 0) {
                ParticleSystem ps = itemList.get(itemList.size() - 1);
                x += getRandomOffset();
                y += getRandomOffset();
                ps.updateEmitPoint(x, y);
            }
        }
    }

    private int getRandomOffset() {
        int offset = new Random(System.nanoTime()).nextInt(201)-100;
        Log.e("offset", String.valueOf(offset));
        return offset;
    }
}
