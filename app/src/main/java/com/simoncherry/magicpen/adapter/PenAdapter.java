package com.simoncherry.magicpen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.simoncherry.magicpen.R;
import com.simoncherry.magicpen.model.PenModel;

import java.util.List;

/**
 * Created by Simon on 2017/3/18.
 */

public class PenAdapter extends RecyclerView.Adapter<PenAdapter.MyViewHolder> {

    private final static String TAG = PenAdapter.class.getSimpleName();

    private Context mContext;
    private RecyclerView mRv;
    private List<PenModel> mData;
    private int mSelectedPos = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PenAdapter(Context mContext, RecyclerView mRv, List<PenModel> mData) {
        this.mContext = mContext;
        this.mRv = mRv;
        this.mData = mData;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPen;
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPen = (ImageView) itemView.findViewById(R.id.iv_pen);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_pen, parent, false));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PenModel penModel = mData.get(position);
        if (penModel != null) {
            //Log.e(TAG, penModel.toString());
            holder.ivPen.setTag(position);
            holder.ivPen.setImageResource(penModel.getResId());
            holder.tvName.setText(penModel.getName());
            if (penModel.isSelect()) {
                selectPenAnimation(holder.ivPen);
            } else {
                unSelectPenAnimation(holder.ivPen);
            }

            holder.ivPen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyViewHolder couponVH = (MyViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                    if (couponVH != null) {//还在屏幕里
                        unSelectPenAnimation(couponVH.ivPen);
                    }else {
                        //add by 2016 11 22 for 一些极端情况，holder被缓存在Recycler的cacheView里，
                        //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
                        notifyItemChanged(mSelectedPos);
                    }
                    mData.get(mSelectedPos).setSelect(false);//不管在不在屏幕里 都需要改变数据
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mData.get(mSelectedPos).setSelect(true);
                    selectPenAnimation(holder.ivPen);

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    private void refreshPenSelect(int position) {
        if (mData.size() > position) {
            for (int i=0; i<mData.size(); i++) {
                PenModel penModel = mData.get(i);
                if (i == position) {
                    penModel.setSelect(true);
                } else {
                    penModel.setSelect(false);
                }
                mData.set(i, penModel);
            }
            notifyDataSetChanged();
        }
    }

    private void selectPenAnimation(View view) {
        view.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    private void unSelectPenAnimation(View view) {
        view.clearAnimation();
    }
}
