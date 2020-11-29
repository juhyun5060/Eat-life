package kr.hs.emirim.s2019w27.calender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendarview;
    private String Date;
    private Button gallerybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //화면 꺼짐 방지

        calendarview = (CalendarView) findViewById(R.id.calendarView);
        calendarview.showCurrentMonthPage();

        gallerybtn = findViewById(R.id.gallerybtn);


        // 오른쪽 상단에 있는 아이콘 제어
        ImageView lv = findViewById(R.id.listView);
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

                Date = String.valueOf(Year+"/"+Month+"/"+Day);
                Log.i("Date Log", Date+"");

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Date", Date);
                setResult(Activity.RESULT_OK, intent);  //인텐트 값이 넘어갔는지 체크
                startActivity(intent);
            }
        });

        gallerybtn.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, kr.hs.emirim.s2019w27.calender.slider.GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("앱 종료");
        dialog.setMessage("정말로 종료하시겠습니까?");
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                finishAndRemoveTask();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
}