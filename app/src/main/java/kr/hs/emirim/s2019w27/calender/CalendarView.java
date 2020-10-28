package kr.hs.emirim.s2019w27.calender;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class CalendarView extends AppCompatTextView {
    private DayItem item;

    public CalendarView(Context context){
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        setBackgroundColor(Color.WHITE);
    }

    public DayItem getItem(){
        return item;
    }

    public void setItem(DayItem item){
        this.item = item;

        int day = item.getDay();
        if (day != 0) {        // day 위치, 색상, 크기변경
            setText(String.valueOf(day));
            setGravity(Gravity.LEFT);
            setTextColor(Color.BLACK);
            setTextSize(17);
            setPadding(14,8,0,0);
        }else{
            setText("");
        }
    }
}
