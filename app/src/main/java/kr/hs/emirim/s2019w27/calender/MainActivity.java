package kr.hs.emirim.s2019w27.calender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //화면 꺼짐 방지

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.icon));

        calendarview = (CalendarView) findViewById(R.id.calendarView);
        calendarview.setEvents(events);
        calendarview.showCurrentMonthPage();


        // 오른쪽 상단에 있는 아이콘 제어
        ImageView lv = findViewById(R.id.listview);
        lv.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, kr.hs.emirim.s2019w27.calender.listView.ListActivity.class);
                startActivity(intent);
            }
        });

        // 캘린더에서 날짜 클릭 시 해당 일자의 Year/Month/Day 값을 넘김
        calendarview.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickDay = eventDay.getCalendar();
                int Year = clickDay.get(Calendar.YEAR);
                int Month = clickDay.get(Calendar.MONTH)+1;
                int Day = clickDay.get(Calendar.DAY_OF_MONTH);

                String Date = String.valueOf(Year+"/"+Month+"/"+Day);
//                Toast.makeText(MainActivity.this, Date, Toast.LENGTH_SHORT).show();
                Log.i("Date Log", Date+"");


                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Date", Date);
                setResult(Activity.RESULT_OK, intent);  //인텐트 값이 넘어갔는지 체크
                startActivity(intent);
            }
        });
    }
}