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
        setBackgroundColor(Color.RED);
        InputStream inputStream = getResources().openRawResource(resId);
        mDatas = CSVParser.read(inputStream);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        canvas.drawRect(0, mHeight / 2, mWidth, mHeight, mPaint);
    }
}
