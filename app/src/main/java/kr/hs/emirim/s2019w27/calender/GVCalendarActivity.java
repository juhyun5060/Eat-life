package kr.hs.emirim.s2019w27.calender;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.*;

import kr.hs.emirim.s2019w27.calender.listView.ListActivity;

public class GVCalendarActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView calendarTitle_currentDate;
    private GridView gridView;
    private ArrayList<DayInfo> dayList;
    private CalendarAdapter adapter;
    private ImageButton previousBtn, nextBtn;

    private Calendar Mcalendar;
    private ImageView goToListImg;

//        // 오늘에 날짜를 세팅 해준다.
//        long now = System.currentTimeMillis();
//        final Date date = new Date(now);
//        //연,월,일을 따로 저장
//        SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
//        SimpleDateFormat MonthFormat = new SimpleDateFormat("mm", Locale.KOREA);
//        SimpleDateFormat DayFormat = new SimpleDateFormat("dd", Locale.KOREA);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dayList = new ArrayList<DayInfo>();

        calendarTitle_currentDate = (TextView) findViewById(R.id.currentDate);
        gridView = (GridView) findViewById(R.id.gridview);
        previousBtn = (ImageButton) findViewById(R.id.previousBtn);
        nextBtn = (ImageButton) findViewById(R.id.nextBtn);
        goToListImg = (ImageView)findViewById(R.id.goToListImage);
        previousBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        gridView.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        //Mcalendar에 년도 월 일 등을 set 하는 부분이 안보여요....
        super.onResume();
        Mcalendar = Calendar.getInstance();
//        int curDay = Mcalendar.get(Calendar.DAY_OF_MONTH);
        Mcalendar.set(Calendar.DAY_OF_MONTH, 1);    //기본값 1로 설정
        getCalendar(Mcalendar);
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

        //이전 달의 날짜를 공백으로 추가해 달력 위치를 맞춤
        for(int i = 0; i< dayOfMonth - 1; i++){
            String date = "";
            day = new DayInfo();
            day.setDay(String.valueOf(date));
            day.setInMonth(true);

            dayList.add(day);
        }

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
        int curYear = Mcalendar.get(Calendar.YEAR);
        int curMonth = Mcalendar.get(Calendar.MONTH);

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

    private Calendar getNextMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
//        calendarTitle_currentDate.setText(thisMonthCalendar.get(Calendar.YEAR) + "year" + (thisMonthCalendar.get(Calendar.MONTH) + 1) + "month");
        setMonthText();

        return calendar;
    }

    @Override
    public void onItemClick (AdapterView < ? > parent, View view, int position, long id){
        int selectYear = Mcalendar.get(Calendar.YEAR);
        int selectMonth = Mcalendar.get(Calendar.MONTH);
        int selectDay = Mcalendar.get(Calendar.DAY_OF_MONTH);
//        int curDay = Mcalendar.get(Calendar.DAY_OF_MONTH);
//        Mcalendar.set(Calendar.DAY_OF_MONTH, dayList.getItem(position));

        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("year", selectYear);
        intent.putExtra("month", selectMonth+1);
        intent.putExtra("day", selectDay+position);
        setResult(Activity.RESULT_OK, intent);  //인텐트 값이 넘어갔는지 체크

        Toast.makeText(getApplicationContext(),
                (selectYear+"/"+(selectMonth+1)+"/"+(selectDay+position)), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previousBtn:
                Mcalendar = getLastMonth(Mcalendar);
                getCalendar(Mcalendar);
                break;
            case R.id.nextBtn:
                Mcalendar = getNextMonth(Mcalendar);
                getCalendar(Mcalendar);
                break;
            case R.id.goToListImage:
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                break;
        }
    }
}