package kr.hs.emirim.s2019w27.calender;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.view.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class GVCalendarActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView calendarTitle_currentDate;
    private GridView gridView;
    private ArrayList<DayInfo> dayList;
    private CalendarAdapter adapter;
    private ImageButton previousBtn, nextBtn;

    Calendar thisMonthCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dayList = new ArrayList<DayInfo>();

        calendarTitle_currentDate = (TextView) findViewById(R.id.currentDate);
        gridView = (GridView) findViewById(R.id.gridview);
        previousBtn = (ImageButton) findViewById(R.id.previousBtn);
        nextBtn = (ImageButton) findViewById(R.id.nextBtn);

        previousBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        thisMonthCalendar = Calendar.getInstance();
        thisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(thisMonthCalendar);
    }

    private void getCalendar(Calendar calendar) {
        int dayOfMonth;
        int thisMonthLastDay;

        dayList.clear();
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.MONTH, 1);
        Log.e("this month start day", calendar.get(Calendar.DAY_OF_MONTH) + "");

        if (dayOfMonth == 1) {  //일주일마다 줄바꿈
            dayOfMonth += 7;
        }

//        calendarTitle_currentDate.setText((thisMonthCalendar.get(calendar.MONTH) + 1) + " " + thisMonthCalendar.get(Calendar.YEAR));
        setMonthText();

        DayInfo day;
        Log.e("DayOfMonth", dayOfMonth + "");

        for (int i = 1; i <= thisMonthLastDay; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);

            dayList.add(day);
        }
        initCalendarAdapter();
    }

    private void initCalendarAdapter() {
        adapter = new CalendarAdapter(this, R.layout.day, dayList);
        gridView.setAdapter(adapter);
    }

    private void setMonthText() {
        int curYear = thisMonthCalendar.get(Calendar.YEAR);
        int curMonth = thisMonthCalendar.get(Calendar.MONTH);

        // 월, 년 표시
        if ((curMonth+1)==1){
            calendarTitle_currentDate.setText("JAN "+curYear);
        }else if ((curMonth+1)==2){
            calendarTitle_currentDate.setText("FEB "+curYear);
        }else if ((curMonth+1)==3){
            calendarTitle_currentDate.setText("MAR "+curYear);
        }else if ((curMonth+1)==4){
            calendarTitle_currentDate.setText("APR "+curYear);
        }else if ((curMonth+1)==5){
            calendarTitle_currentDate.setText("MAY "+curYear);
        }else if ((curMonth+1)==6){
            calendarTitle_currentDate.setText("JUN "+curYear);
        }else if ((curMonth+1)==7){
            calendarTitle_currentDate.setText("JUL "+curYear);
        }else if ((curMonth+1)==8){
            calendarTitle_currentDate.setText("AUG "+curYear);
        }else if ((curMonth+1)==9){
            calendarTitle_currentDate.setText("SEP "+curYear);
        }else if ((curMonth+1)==10){
            calendarTitle_currentDate.setText("OCT "+curYear);
        }else if ((curMonth+1)==11){
            calendarTitle_currentDate.setText("NOV "+curYear);
        }else if ((curMonth+1)==12){
            calendarTitle_currentDate.setText("DEC "+curYear);
        }
    }

    private Calendar getLastMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
//        calendarTitle_currentDate.setText(thisMonthCalendar.get(Calendar.YEAR) + "year" + (thisMonthCalendar.get(Calendar.MONTH) + 1) + "month");
        setMonthText();
        return calendar;
    }

    private Calendar getNexMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
//        calendarTitle_currentDate.setText(thisMonthCalendar.get(Calendar.YEAR) + "year" + (thisMonthCalendar.get(Calendar.MONTH) + 1) + "month");
        setMonthText();

        return calendar;
    }

    @Override
    public void onItemClick (AdapterView < ? > parent, View view, int position, long id){
        //day 값 수정필요
        int selectYear = thisMonthCalendar.get(Calendar.YEAR);
        int selectMonth = thisMonthCalendar.get(Calendar.MONTH);
        int selectDay = thisMonthCalendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        intent.putExtra("year", selectYear);
        intent.putExtra("month", selectMonth+1);
        intent.putExtra("day", selectDay);
        Toast.makeText(getApplicationContext(),
                (selectYear+"/"+(selectMonth+1)+"/"+dayList.get(position)), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previousBtn:
                thisMonthCalendar = getLastMonth(thisMonthCalendar);
                getCalendar(thisMonthCalendar);
                break;
            case R.id.nextBtn:
                thisMonthCalendar = getNexMonth(thisMonthCalendar);
                getCalendar(thisMonthCalendar);
                break;
        }
    }
}