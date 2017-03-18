package com.simoncherry.magicpen.model;

/**
 * Created by Simon on 2017/3/18.
 */

public class PenModel {

    private int type;
    private int resId;
    private String name;
    private boolean isSelect;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "PenModel{" +
                "type=" + type +
                ", resId=" + resId +
                ", name='" + name + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
