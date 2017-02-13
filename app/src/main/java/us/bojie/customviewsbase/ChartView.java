package us.bojie.customviewsbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.io.InputStream;
import java.util.List;

/**
 * Created by bojiejiang on 2/12/17.
 */

public class ChartView extends View {

    List<StockData> mDatas;
    List<StockData> mSubSet;
    float mWidth, mHeight, mMaxPrice, mMinPrice;
    Paint mPaint = new Paint();
    Paint mStrokePaint = new Paint();

    public ChartView(Context context, int resId) {
        super(context);
        InputStream inputStream = getResources().openRawResource(resId);
        mDatas = CSVParser.read(inputStream);
        mStrokePaint.setColor(Color.WHITE);
        showLast();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        float reactWidth = mWidth / mSubSet.size();
        mStrokePaint.setStrokeWidth(reactWidth / 8);
        float left = 0;
        float bottom, top;

        for (int i = mSubSet.size() - 1; i >= 0; i--) {
            StockData stockData = mSubSet.get(i);
            if (stockData.close >= stockData.open) {
                mPaint.setColor(Color.GREEN);
                top = stockData.close;
                bottom = stockData.open;
            } else {
                mPaint.setColor(Color.RED);
                top = stockData.open;
                bottom = stockData.close;
            }
            canvas.drawLine(left + reactWidth / 2, getYPosition(stockData.high),
                    left + reactWidth / 2, getYPosition(stockData.low), mStrokePaint);
            canvas.drawRect(left, getYPosition(top), left + reactWidth,
                    getYPosition(bottom), mPaint);
            left += reactWidth;
        }
    }

    private float getYPosition(float price) {
        float scaleFactorY = (price - mMinPrice) / (mMaxPrice - mMinPrice);
        return mHeight - mHeight * scaleFactorY;
    }

    public void showLast(int n) {
        mSubSet = mDatas.subList(0, n);
        updateMaxAndMin();
        invalidate();
    }

    private void updateMaxAndMin() {
        mMaxPrice = -1f;
        mMinPrice = 99999f;
        for (StockData stockData : mSubSet) {
            if (stockData.high > mMaxPrice) {
                mMaxPrice = stockData.high;
            }
            if (stockData.low < mMinPrice) {
                mMinPrice = stockData.low;
            }
        }
    }

    public void showLast() {
        showLast(mDatas.size());
    }
}
