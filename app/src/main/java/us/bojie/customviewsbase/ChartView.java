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
    float mWidth, mHeight;
    Paint mPaint = new Paint();

    public ChartView(Context context, int resId) {
        super(context);
        InputStream inputStream = getResources().openRawResource(resId);
        mDatas = CSVParser.read(inputStream);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        float reactWidth = mWidth / mDatas.size();
        float left = 0;

        for (int i = mDatas.size() - 1; i >= 0; i--) {
            StockData stockData = mDatas.get(i);
            canvas.drawRect(left, mHeight - stockData.high, left + reactWidth,
                    mHeight - stockData.low, mPaint);
            left += reactWidth;
        }
    }
}
