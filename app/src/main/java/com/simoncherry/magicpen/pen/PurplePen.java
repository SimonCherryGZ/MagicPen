package com.simoncherry.magicpen.pen;

import android.app.Activity;
import android.util.Log;

import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;
import com.plattysoft.leonids.modifiers.ScaleModifier;
import com.simoncherry.magicpen.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Simon on 2017/3/19.
 */

public class PurplePen extends BasePen {

    public PurplePen(Activity activity, int backgroundResId) {
        super(activity, backgroundResId);
    }

    @Override
    int getParticleKind() {
        return 4;
    }

    @Override
    ParticleSystem createParticleSystem(int x, int y, int type) {
        ParticleSystem ps;
        x += getRandomOffset();
        y += getRandomOffset();
        type = getRandomType();
        Log.e("random type" , String.valueOf(type));
        switch (type) {
            case 0:
                ps = createRiseHalo1(x, y);
                break;
            case 1:
                ps = createSinkHalo1(x, y);
                break;
            case 2:
                ps = createRiseHalo2(x, y);
                break;
            case 3:
                ps = createSinkHalo2(x, y);
                break;
            default:
                ps = createFakeParticle();
        }

        return ps;
    }

    private ParticleSystem createRiseHalo1(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 15, R.drawable.halo_purple1, 5000, backgroundResId);
        ps.setScaleRange(0.1f, 0.5f);
        ps.setSpeedRange(0.0f, 0.0f);
        ps.addModifier(new ScaleModifier(0.1f, 0.8f, 0, 5000));
        ps.addModifier(new AlphaModifier(127, 127, 0, 5000));
        ps.emit(x, y, 2);
        return ps;
    }

    private ParticleSystem createSinkHalo1(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 15, R.drawable.halo_purple1, 5000, backgroundResId);
        ps.setScaleRange(0.1f, 0.5f);
        ps.setSpeedRange(0.0f, 0.0f);
        ps.addModifier(new ScaleModifier(1.0f, 0.1f, 0, 5000));
        ps.addModifier(new AlphaModifier(127, 127, 0, 5000));
        ps.emit(x, y, 1);
        return ps;
    }

    private ParticleSystem createRiseHalo2(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 15, R.drawable.halo_purple2, 5000, backgroundResId);
        ps.setScaleRange(0.1f, 0.5f);
        ps.setSpeedRange(0.0f, 0.0f);
        ps.addModifier(new ScaleModifier(0.1f, 0.8f, 0, 5000));
        ps.addModifier(new AlphaModifier(127, 127, 0, 5000));
        ps.emit(x, y, 2);
        return ps;
    }

    private ParticleSystem createSinkHalo2(int x, int y) {
        ParticleSystem ps = new ParticleSystem(activity, 15, R.drawable.halo_purple2, 5000, backgroundResId);
        ps.setScaleRange(0.1f, 0.5f);
        ps.setSpeedRange(0.0f, 0.0f);
        ps.addModifier(new ScaleModifier(1.0f, 0.1f, 0, 5000));
        ps.addModifier(new AlphaModifier(127, 127, 0, 5000));
        ps.emit(x, y, 1);
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

    private int getRandomType() {
        return new Random(System.nanoTime()).nextInt(getParticleKind());
    }

    private int getRandomOffset() {
        int offset = new Random(System.nanoTime()).nextInt(201)-100;
        Log.e("offset", String.valueOf(offset));
        return offset;
    }
}
