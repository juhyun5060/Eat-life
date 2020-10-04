package kr.hs.emirim.s2019w27.calender;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    Calendar calendar;
    Context mContext;
    DayItem[] items;
    int curYear;
    int curMonth;

    CalendarAdapter(MainActivity context){
        super();
        mContext = context;
        init();
    }

    CalendarAdapter(Context context, AttributeSet attrs){
        super();
        mContext = context;
        init();
    }

    public void init(){
        calendar = Calendar.getInstance();  //Calendar 객체 가져오기
        items = new DayItem[7*6];         //달력 크기 결정
        calculate();
    }

    public void calculate(){
        for(int i=0; i<items.length; i++){
            items[i] = new DayItem(0);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1); // 1일 부터 시작
        //현재 달 1일의 요일 (1: 일 2: 월 3: 화 4: 수 5: 목 6: 금 7: 토)
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);

        // 1일의 요일에 따라 시작위치를 정하고 마지막 날짜까지 값 지정
        int cnt = 1;
        for(int i=startDay-1; i<startDay-1+lastDay; i++){
            items[i] = new DayItem(cnt);
            cnt++;
        }
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH);
    }

    public void setPreviousMonth(){             // 이전달
        calendar.add(Calendar.MONTH, -1);
        calculate();
    }

    public void setNextMonth(){
        calendar.add(Calendar.MONTH, 1);    // 다음달
        calculate();
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalendarView view = new CalendarView(mContext);
        DayItem item = items[position];
        view.setItem(item); //날짜 값이 0이면 공백으로, 아니면 날짜값으로

        // 토,일요일 색상 지정
        if(position%7==0){                                   //SUN
            view.setTextColor(Color.RED);
        }else if(position%7==6) {                            //SAT
            view.setTextColor(Color.BLUE);
        }
        GridView.LayoutParams params = new GridView.LayoutParams( GridView.LayoutParams.MATCH_PARENT,225);
        view.setLayoutParams(params);

        return view;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getCurYear(){
        return curYear;
    }

    public int getCurMonth(){
        return curMonth;
    }
}
