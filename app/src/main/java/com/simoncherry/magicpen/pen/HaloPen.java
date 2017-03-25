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

public class HaloPen extends BasePen {

    public HaloPen(Activity activity, int backgroundResId) {
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
                ps = createHalo(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createHalo(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 250, R.drawable.halo_orange, 1000, backgroundResId);
        ps.setScaleRange(0.5f, 1.0f);
        ps.setSpeedRange(0.3f, 0.3f);
        ps.addModifier(new AlphaModifier(255, 0, 0, 1000));
        ps.addModifier(new ScaleModifier(1.0f, 0.3f, 0, 1000));
        ps.addModifier(new CurveModifier(0.001f, 165));
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
