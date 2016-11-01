package com.bigkoo.pickerviewdemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoz on 2016/7/27 0027.
 */
public class FundFilterView extends FrameLayout implements TimePickerView.OnTimeSelectListener {

    private TextView tv_begin_date;
    private TextView tv_end_date;
    private Button bt_confirm;
    private TimePickerView pvTime;
    private Date beginDate;
    private Date endDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.M.d");
    private boolean isBeginDate = true;
    public FundFilterView(Context context) {
        super(context);
        init(context);
    }

    public FundFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FundFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FundFilterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_begin_date = (TextView) findViewById(R.id.tv_begin_date);
        tv_end_date = (TextView) findViewById(R.id.tv_end_date);
        bt_confirm = (Button) findViewById(R.id.bt_confirm);

        tv_begin_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isBeginDate = true;
                setDatePickerTitle("起始日期");
                pvTime.setTime(beginDate);
                pvTime.show();
            }
        });
        tv_end_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isBeginDate = false;
                setDatePickerTitle("截至日期");
                pvTime.setTime(endDate);
                pvTime.show();
            }
        });

        bt_confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show(false);
            }
        });
    }

    private void init(Context context) {
//        //时间选择器
        pvTime = new TimePickerView(context, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(this);
    }

    public void show(boolean toShow) {
        if (toShow) {
            scaleIn();
        } else {
            scaleOut();
        }
//        setVisibility(toShow ? View.VISIBLE : View.INVISIBLE);
    }

    private void scaleIn() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0.0F,
                0.0F, 1.0F);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0.0F,
                0.0F, 1.0F);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0.0F,
                0.0F, 1.0F);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY, pvhZ).setDuration(1000);
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                FundFilterView.this.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        oa.start();
    }

    private void scaleOut() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1.0F,
                1.0F, 0.0F);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1.0F,
                1.0F, 0.0F);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1.0F,
                1.0F, 0.0F);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY, pvhZ).setDuration(1000);
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                FundFilterView.this.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        oa.start();
    }

    public void setSelectDateRange(int beginYear, int endYear) {
        pvTime.setRange(beginYear, endYear);
    }

    public void setRange(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        tv_begin_date.setText(sdf.format(beginDate));
        tv_end_date.setText(sdf.format(endDate));
    }

    private void setDatePickerTitle(CharSequence title) {
        pvTime.setTitle(title);
    }

    @Override
    public void onTimeSelect(Date date, TimePickerView v) {
        String dateString = sdf.format(date);
        if (isBeginDate) {
            this.beginDate = date;
            tv_begin_date.setText(dateString);
        } else {
            this.endDate = date;
            tv_end_date.setText(dateString);
        }
    }
}
