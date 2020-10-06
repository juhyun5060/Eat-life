package kr.hs.emirim.s2019w27.calender;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    GridView monthView;
    TextView monthText;
    CalendarAdapter adapter;
    private String TAG = "Eat-Life Start";

    // 해상도 구하기
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthView = findViewById(R.id.monthView);
        adapter = new CalendarAdapter(this);
        monthView.setAdapter(adapter);

        monthText = findViewById(R.id.monthText);
        setMonthText();

        Button previousBtn = findViewById(R.id.previousBtn);
        Button nextBtn = findViewById(R.id.nextBtn);


        // back 버튼
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged(); //어댑터 데이터 갱신
                setMonthText();
            }
        });

        // next 버튼
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();
                setMonthText();
            }
        });


        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        AddActivity.class);
                intent.putExtra("date", position);
                startActivity(intent);
            }
        });
    }

    public void setMonthText(){
        int curYear = adapter.getCurYear();
        int curMonth = adapter.getCurMonth();

        // 월 표시
        if ((curMonth+1)==1){
            monthText.setText("JAN "+curYear);
        }else if ((curMonth+1)==2){
            monthText.setText("FEB "+curYear);
        }else if ((curMonth+1)==3){
            monthText.setText("MAR "+curYear);
        }else if ((curMonth+1)==4){
            monthText.setText("APR "+curYear);
        }else if ((curMonth+1)==5){
            monthText.setText("MAY "+curYear);
        }else if ((curMonth+1)==6){
            monthText.setText("JUN "+curYear);
        }else if ((curMonth+1)==7){
            monthText.setText("JUL "+curYear);
        }else if ((curMonth+1)==8){
            monthText.setText("AUG "+curYear);
        }else if ((curMonth+1)==9){
            monthText.setText("SEP "+curYear);
        }else if ((curMonth+1)==10){
            monthText.setText("OCT "+curYear);
        }else if ((curMonth+1)==11){
            monthText.setText("NOV "+curYear);
        }else if ((curMonth+1)==12){
            monthText.setText("DEC "+curYear);
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {   //해상도
        monthView = findViewById(R.id.monthView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = monthView.getWidth();
        height = monthView.getHeight();
//        monthView.setWidth(width);
        Log.d(TAG, ">>> size.x : " + size.x + ", size.y : " + size.y);
    }
}
