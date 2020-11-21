package kr.hs.emirim.s2019w27.calender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import kr.hs.emirim.s2019w27.calender.decorators.*;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(3000, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.addDecorators( new SaturdayDecorator(), new SundayDecorator(), new TodayDecorator() );

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year", Year + "");
                Log.i("Month", Month + "");
                Log.i("Day", Day + "");

                String selectedDate = Year + "/" + Month + "/" + Day;

                Log.i("selectedDate", selectedDate + "");
//                materialCalendarView.clearSelection();


                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Date", selectedDate);
                setResult(Activity.RESULT_OK, intent);  //인텐트 값이 넘어갔는지 체크

//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Data", String.valueOf(date));
//                intent.putExtras(bundle);
//                startActivity(intent);

                Toast.makeText(MainActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}