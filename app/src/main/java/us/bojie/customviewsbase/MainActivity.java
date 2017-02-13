package us.bojie.customviewsbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    static final String PAST_WEEK = "Past Week";
    static final String PAST_MONTH = "Past Month";
    static final String PAST_YEAR = "Past Year";
    static final String ALL_DATA = "All Data";
    ChartView mChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChartView = new ChartView(this, R.raw.goog);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        relativeLayout.addView(mChartView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case PAST_WEEK:
                mChartView.showLast(7);
                break;
            case PAST_MONTH:
                mChartView.showLast(30);
                break;
            case PAST_YEAR:
                mChartView.showLast(365);
                break;
            case ALL_DATA:
                mChartView.showLast();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(PAST_WEEK);
        menu.add(PAST_MONTH);
        menu.add(PAST_YEAR);
        menu.add(ALL_DATA);
        return true;
    }
}
