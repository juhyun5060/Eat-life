package kr.hs.emirim.s2019w27.calender.listView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kr.hs.emirim.s2019w27.calender.AddActivity;
import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;
import kr.hs.emirim.s2019w27.calender.DB.MemoMinimal;
import kr.hs.emirim.s2019w27.calender.MainActivity;
import kr.hs.emirim.s2019w27.calender.R;

public class ListActivity extends AppCompatActivity {
    // 리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;

    private ImageView goToCalendar;
    private TextView currentDate;
    private ImageView btnBack;
    private ImageView btnNext;

    private String start_date;
    private String end_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        currentDate = findViewById(R.id.currentDate);

        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        final SimpleDateFormat dateString = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        final SimpleDateFormat dateString_db = new SimpleDateFormat("yyyy/MM/", Locale.getDefault());
        final Calendar cal = Calendar.getInstance();

        start_date = dateString_db.format(date) + "1";
        end_date = dateString_db.format(date) + "31";

        currentDate.setText(dateString.format(date));

        initialized();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        goToCalendar = findViewById(R.id.goToCalendarImg);
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);

        goToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(cal.MONTH, -1);
                start_date = dateString_db.format(cal.getTime()) + "1";
                end_date = dateString_db.format(cal.getTime()) + "31";
                currentDate.setText(dateString.format(cal.getTime()));
                Toast.makeText(ListActivity.this, start_date + ", " + end_date, Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(cal.MONTH, +1);
                start_date = dateString_db.format(cal.getTime()) + "1";
                end_date = dateString_db.format(cal.getTime()) + "31";
                currentDate.setText(dateString.format(cal.getTime()));
                Toast.makeText(ListActivity.this, start_date + ", " + end_date, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialized() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        int size = AppDatabase.getInstance(this).memoDAO().getListItem(start_date, end_date).size();
        for(int i=0; i<size; i++) {
            adapter.addItems(AppDatabase.getInstance(this).memoDAO().getListItem(start_date, end_date).get(i));
        }
    }

}