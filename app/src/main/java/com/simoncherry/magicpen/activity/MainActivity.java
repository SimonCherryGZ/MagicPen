package com.simoncherry.magicpen.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.simoncherry.magicpen.R;
import com.simoncherry.magicpen.adapter.PenAdapter;
import com.simoncherry.magicpen.model.PenModel;
import com.simoncherry.magicpen.pen.PenUtils;
import com.simoncherry.magicpen.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rvPen;
    private PenAdapter mAdapter;
    private List<PenModel> mData;
    private LinearLayoutManager mLinearLayoutManager;

    private FrameLayout layoutDraw;
    private Button btnBack;

    private Context mContext;
    private int touchOffsetY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        init();
    }

    private void init() {
        PenUtils.getInstance().init(MainActivity.this, R.id.layout_draw);
        findView();
        initDrawLayout();
        initButton();
        initRecyclerView();
        loadPenModel();
    }

    private void findView() {
        rvPen = (RecyclerView) findViewById(R.id.rv_pen);
        layoutDraw = (FrameLayout) findViewById(R.id.layout_draw);
        btnBack = (Button) findViewById(R.id.btn_back);
    }

    private void initDrawLayout() {
        int statusBarHeight = ViewUtils.getStatusBarHeight(mContext);
        int toolBarHeight = ViewUtils.getToolBarHeight(mContext);
        touchOffsetY = statusBarHeight + toolBarHeight;
        Log.e(TAG, "statusBarHeight: " + statusBarHeight);
        Log.e(TAG, "toolBarHeight: " + toolBarHeight);
        Log.e(TAG, "touchOffsetY: " + touchOffsetY);

        layoutDraw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY() + touchOffsetY;
                //Log.e(TAG, "event.getY(): " + event.getY());

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        PenUtils.getInstance().getPen().addNewParticleSystem(x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        PenUtils.getInstance().getPen().updateParticleSystem(x, y);
                        break;
                    case MotionEvent.ACTION_UP:
                        PenUtils.getInstance().getPen().stopLatestParticle();
                        break;
                }

                return true;
            }
        });
    }

    private void initButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PenUtils.getInstance().getPen().removeLatestParticle();
            }
        });
    }

    private void initRecyclerView() {
        mData = new ArrayList<>();
        mAdapter = new PenAdapter(mContext, rvPen, mData);
        mAdapter.setOnItemClickListener(new PenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(mContext, "click No." + position + " pen", Toast.LENGTH_SHORT).show();
                PenUtils.getInstance().setPen(position);
                adjustPenPosition(position);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rvPen.setLayoutManager(mLinearLayoutManager);
        rvPen.setAdapter(mAdapter);
    }

    private void adjustPenPosition(int position) {
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        //Log.e(TAG, "firstItem: " + firstItem);
        //Log.e(TAG, "lastItem: " + lastItem);
        if (position == lastItem-1) {                         // 如果点击倒数第2个
            rvPen.smoothScrollToPosition(lastItem);
        } else if (position == lastItem) {                    // 如果点击倒数第1个
            if (mData.size() > lastItem + 1) {
                rvPen.smoothScrollToPosition(lastItem + 1);
            } else {
                rvPen.smoothScrollToPosition(lastItem);
            }
        } else if (position == firstItem+1) {                // 如果点击顺数第2个
            rvPen.smoothScrollToPosition(firstItem);
        } else if (position == firstItem) {                  // 如果点击顺数第1个
            if (firstItem > 0) {
                rvPen.smoothScrollToPosition(firstItem - 1);
            } else {
                rvPen.smoothScrollToPosition(firstItem);
            }
        }
    }

    private void loadPenModel() {
        for (int i = 0; i< PenUtils.penResId.length; i++) {
            PenModel penModel = new PenModel();
            penModel.setType(i);
            penModel.setResId(PenUtils.penResId[i]);
            penModel.setName(PenUtils.penName[i]);
            if (i == 0) {
                penModel.setSelect(true);
            } else {
                penModel.setSelect(false);
            }
            mData.add(penModel);
        }
        mAdapter.notifyDataSetChanged();
    }
}
