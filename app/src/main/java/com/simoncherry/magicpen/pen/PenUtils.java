package com.simoncherry.magicpen.pen;

import android.app.Activity;

import com.plattysoft.leonids.ParticleSystem;
import com.simoncherry.magicpen.R;

import java.util.List;

/**
 * Created by Simon on 2017/3/18.
 */

public class PenUtils {

    public final static int TYPE_GEL = 0;
    public final static int TYPE_PAINT = 1;
    public final static int TYPE_FIREWORKS = 2;
    public final static int TYPE_DAZZLE = 3;
    public final static int TYPE_SILK = 4;
    public final static int TYPE_STROKE = 5;
    public final static int TYPE_RAINBOW = 6;
    public final static int TYPE_CLOVER = 7;
    public final static int TYPE_PURPLE = 8;
    public final static int TYPE_FLOWER = 9;
    public final static int TYPE_COLORFUL = 10;
    public final static int TYPE_HEART = 11;
    public final static int TYPE_HALO = 12;
    public final static int TYPE_SNOW = 13;
    public final static int TYPE_FLIPPED = 14;
    public final static int TYPE_STAR = 15;
    public final static int TYPE_ILLUSION = 16;
    public final static int TYPE_FIREFLY = 17;
    public final static int TYPE_MARKER = 18;
    public final static int TYPE_ERASER = 19;

    public final static int[] penResId = {
            R.drawable.pen_gel, R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_default,
            R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_clover, R.drawable.pen_purple, R.drawable.pen_flower,
            R.drawable.pen_colorful, R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_default,
            R.drawable.pen_star, R.drawable.pen_illusion, R.drawable.pen_default, R.drawable.pen_default, R.drawable.pen_default
    };

    public final static String[] penName = {
            "签字笔", "油漆笔", "烟花笔", "炫光", "丝带",
            "描边笔", "彩虹", "四叶草", "紫光", "花瓣",
            "七彩光圈", "爱心", "光晕", "飘雪", "心动",
            "小星星", "梦幻", "萤火虫", "荧光笔", "橡皮擦"
    };


    private Activity activity;
    private int backgroundResId;
    private BasePen basePen;

    // 定义私有构造方法（防止通过 new SingletonTest()去实例化）
    private PenUtils() {
    }

    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static PenUtils instance;

    public static PenUtils getInstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (PenUtils.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new PenUtils();
                }
            }
        }
        return instance;
    }

    public void init(Activity activity, int backgroundResId) {
        this.activity = activity;
        this.backgroundResId = backgroundResId;

        basePen = new FlowerPen(activity, backgroundResId);
        basePen.initParticleList();
    }

    public BasePen getPen() {
        return basePen;
    }

    public void setPen(int type) {
        List<List<ParticleSystem>> oldParticle = basePen.getOldParticle();
        switch (type) {
            case TYPE_GEL:
                break;
            case TYPE_PAINT:
                break;
            case TYPE_FIREWORKS:
                break;
            case TYPE_DAZZLE:
                break;
            case TYPE_SILK:
                break;
            case TYPE_STROKE:
                break;
            case TYPE_RAINBOW:
                break;
            case TYPE_CLOVER:
                basePen = new CloverPen(activity, backgroundResId);
                break;
            case TYPE_PURPLE:
                basePen = new PurplePen(activity, backgroundResId);
                break;
            case TYPE_FLOWER:
                basePen = new FlowerPen(activity, backgroundResId);
                break;
            case TYPE_COLORFUL:
                basePen = new ColorfulPen(activity, backgroundResId);
                break;
            case TYPE_HEART:
                break;
            case TYPE_HALO:
                break;
            case TYPE_SNOW:
                break;
            case TYPE_FLIPPED:
                break;
            case TYPE_STAR:
                basePen = new StarPen(activity, backgroundResId);
                break;
            case TYPE_ILLUSION:
                basePen = new IllusionPen(activity, backgroundResId);
                break;
            case TYPE_FIREFLY:
                break;
            case TYPE_MARKER:
                break;
            case TYPE_ERASER:
                break;
            default:
                basePen = new FlowerPen(activity, backgroundResId);
        }
        if (oldParticle != null && oldParticle.size() > 0) {
            basePen.addOldParticleSystem(oldParticle);
        }
        basePen.initParticleList();
    }
}
