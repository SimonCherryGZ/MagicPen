package com.simoncherry.magicpen.pen;

import android.app.Activity;

import com.plattysoft.leonids.ParticleSystem;
import com.simoncherry.magicpen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2017/3/18.
 */

public abstract class BasePen {

    public List<List<ParticleSystem>> particleList = new ArrayList<>();
    public Activity activity;
    public int backgroundResId;

    abstract int getParticleKind();

    abstract ParticleSystem createParticleSystem(int x, int y, int type);

    public BasePen(Activity activity, int backgroundResId) {
        this.activity = activity;
        this.backgroundResId = backgroundResId;
        //initParticleList();
    }

    public void initParticleList() {
        for (int i=0; i<getParticleKind(); i++) {
            List<ParticleSystem> itemList = new ArrayList<>();
            //itemList.add(new ParticleSystem(activity, 1, R.drawable.fake_particle, 1, backgroundResId));
            particleList.add(itemList);
        }
    }

    public void addOldParticleSystem(List<List<ParticleSystem>> oldParticle) {
        particleList.addAll(oldParticle);
    }

    public List<List<ParticleSystem>> getOldParticle() {
        return particleList;
    }

    public void addNewParticleSystem(int x, int y) {
        for (int i=0; i<particleList.size(); i++) {
            List<ParticleSystem> itemList = particleList.get(i);
            itemList.add(createParticleSystem(x, y, i));
        }
    }

    public void updateParticleSystem(int x, int y) {
        for (int i=0; i<particleList.size(); i++) {
            List<ParticleSystem> itemList = particleList.get(i);
            if (itemList.size() > 0) {
                ParticleSystem ps = itemList.get(itemList.size() - 1);
                ps.updateEmitPoint(x, y);
            }
        }
    }

    public void stopLatestParticle() {
        for (int i=0; i<particleList.size(); i++) {
            List<ParticleSystem> itemList = particleList.get(i);
            if (itemList.size() > 0) {
                ParticleSystem ps = itemList.get(itemList.size() - 1);
                ps.cancelButNotClear();
            }
        }
    }

    public void removeLatestParticle() {
        for (int i=0; i<particleList.size(); i++) {
            List<ParticleSystem> itemList = particleList.get(i);
            if (itemList.size() > 0) {
                ParticleSystem ps = itemList.get(itemList.size() - 1);
                ps.cleanupAnimation();
                itemList.remove(ps);
            }
        }
    }

    public ParticleSystem createFakeParticle() {
        return new ParticleSystem(activity, 1, R.drawable.fake_particle, 1, backgroundResId);
    }
}
