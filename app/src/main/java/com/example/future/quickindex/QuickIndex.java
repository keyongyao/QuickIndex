package com.example.future.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Author: Future <br>
 * QQ: <br>
 * Description:快速索引View<br>
 * date: 8:23  8:23.
 */

public class QuickIndex extends View {
    private static final String TAG = "QuickIndex";
    private String[] alphaIndex = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    // 宽度
    private float mViewWidth;
    // 每个字母的高度
    private float mViewCellHeight;
    // 画笔
    private Paint mPaint;
    // 显示 选择字母
    private Toast toast;
    // 索引改变监听接口
    private OnIndexChange mOnIndexChange;
    // 处理触摸事件
    private int lastAlphaIndex = -1;

    public QuickIndex(Context context) {
        this(context, null);
    }

    public QuickIndex(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化一些全局的对象
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE: {
                int index = (int) (event.getY() / mViewCellHeight);
                if (index != lastAlphaIndex)
                    //  Log.i(TAG, "onTouchEvent: "+alphaIndex[index]);
                    if (mOnIndexChange != null) {
                        mOnIndexChange.onTouchAlpha(alphaIndex[index]);
                    }
                lastAlphaIndex = index;
                // 由于重设了画笔颜色 需要重画
                showIndex(alphaIndex[index]);
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                lastAlphaIndex = -1;
                invalidate();
                break;
            }
        }

        return true;
    }

    // 获取 View 的 宽和高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getMeasuredWidth();
        mViewCellHeight = getMeasuredHeight() * 1f / alphaIndex.length;

    }

    // 绘制字索引母列表
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < alphaIndex.length; i++) {
            float positionX = mViewWidth / 2.0f;
            float positionY = mViewCellHeight / 2.0f + mViewCellHeight * i + getAlphaHeight(alphaIndex[i]) / 2;
            // 设置 当前选中字母的颜色为黑色
            mPaint.setColor(lastAlphaIndex == i ? Color.BLACK : Color.WHITE);
            canvas.drawText(alphaIndex[i], positionX, positionY, mPaint);
        }
    }

    // 获取字母的高度
    private float getAlphaHeight(String alpha) {
        Rect bound = new Rect();
        mPaint.getTextBounds(alpha, 0, alpha.length(), bound);
        return bound.height();
    }

    // 显示选择的字母
    private void showIndex(String alpha) {
        if (toast == null) {
            toast = Toast.makeText(getContext(), alpha, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL, 0, 0);

        } else {
            toast.setText(alpha);
        }
        toast.show();
    }

    /**
     * 设置监听接口
     *
     * @param mOnIndexChange
     */
    public void setOnIndexChange(OnIndexChange mOnIndexChange) {
        this.mOnIndexChange = mOnIndexChange;
    }

    /**
     *
     */
    public interface OnIndexChange {
        void onTouchAlpha(String alpha);
    }
}
