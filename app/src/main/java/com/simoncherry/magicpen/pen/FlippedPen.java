package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.util.Log;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.CurveModifier;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.simoncherry.magicpen.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Simon on 2017/3/25.
 */

public class FlippedPen extends BasePen {

    public FlippedPen(Activity activity, int backgroundResId) {
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
                ps = createHeart(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createHeart(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 250, R.drawable.heart1, 1500, backgroundResId);
        ps.setScaleRange(0.1f, 0.3f);
        ps.setSpeedRange(0.3f, 0.3f);
        ps.addModifier(new AlphaModifier(255, 0, 0, 1500));
        ps.addModifier(new ScaleModifier(0.3f, 0.01f, 0, 1500));
        ps.addModifier(new CurveModifier(0.001f, 165));
        ps.emit(x, y, 20);
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
        int offset = new Random(System.nanoTime()).nextInt(401)-200;
        Log.e("offset", String.valueOf(offset));
        return offset;
    }
}
