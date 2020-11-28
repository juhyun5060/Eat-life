package kr.hs.emirim.s2019w27.calender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.*;

import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendarview;
    private List<EventDay> events = new ArrayList<>();
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarview = (CalendarView) findViewById(R.id.calendarView);
        calendarview.showCurrentMonthPage();

        ImageView lv = findViewById(R.id.listview);
        lv.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, kr.hs.emirim.s2019w27.calender.listView.ListActivity.class);
                startActivity(intent);
            }
        });

        calendarview.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickDay = eventDay.getCalendar();
                int Year = clickDay.get(Calendar.YEAR);
                int Month = clickDay.get(Calendar.MONTH)+1;
                int Day = clickDay.get(Calendar.DAY_OF_MONTH);

                Date = String.valueOf(Year+"/"+Month+"/"+Day);
//                Toast.makeText(MainActivity.this, Date, Toast.LENGTH_SHORT).show();
                Log.i("Date Log", Date+"");


                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Date", Date);
                setResult(Activity.RESULT_OK, intent);  //인텐트 값이 넘어갔는지 체크
                startActivity(intent);
            }
        });

        // 사진 추가
        final AppDatabase db = AppDatabase.getInstance(this);
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
//        String imgUri = db.memoDAO().getUri(Date);

        events.add(new EventDay(calendar, R.drawable.add_img));

        calendarview.setEvents(events);
    }
}