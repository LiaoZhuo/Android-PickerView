package com.bigkoo.pickerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

public class RelativeActivity extends Activity implements View.OnClickListener {
    private Button bt_relation;
    OptionsPickerView pvOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);
        bt_relation = (Button) findViewById(R.id.bt_relation);

        bt_relation.setOnClickListener(this);
        //选项选择器
        pvOptions = new OptionsPickerView(this);


        ArrayList<String> options2Items_01=new ArrayList<>();
        options2Items_01.add("父亲");
        options2Items_01.add("母亲");
        options2Items_01.add("兄长");
        options2Items_01.add("舅舅");
        options2Items_01.add("伯伯");
        pvOptions.setPicker(options2Items_01);
        pvOptions.setCyclic(false);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        pvOptions.show();
    }
}
