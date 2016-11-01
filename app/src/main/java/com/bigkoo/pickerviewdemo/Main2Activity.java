package com.bigkoo.pickerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends Activity {
    private FundFilterView ffv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        ffv = (FundFilterView) findViewById(R.id.ffv);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 3);
        Date beginDate = c.getTime();
        c.set(Calendar.MONTH, 9);
        Date endDate = c.getTime();
        ffv.setRange(beginDate, endDate);
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(Main2Activity.this, FundFilterActivity.class), 101);
                ffv.show(true);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
