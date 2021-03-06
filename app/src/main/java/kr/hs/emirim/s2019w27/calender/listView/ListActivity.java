package kr.hs.emirim.s2019w27.calender.listView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;
import kr.hs.emirim.s2019w27.calender.MainActivity;
import kr.hs.emirim.s2019w27.calender.R;

public class ListActivity extends AppCompatActivity {
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
        final SimpleDateFormat dateString_db = new SimpleDateFormat("yyyy/M/", Locale.getDefault());
        final Calendar cal = Calendar.getInstance();

        start_date = dateString_db.format(date) + "1";
        end_date = dateString_db.format(date) + "31";

        currentDate.setText(dateString.format(date));

        initialized();

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
                listRefresh();

                cal.add(cal.MONTH, -1);
                start_date = dateString_db.format(cal.getTime()) + "1";
                end_date = dateString_db.format(cal.getTime()) + "31";
                currentDate.setText(dateString.format(cal.getTime()));

                initialized();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listRefresh();

                cal.add(cal.MONTH, +1);
                start_date = dateString_db.format(cal.getTime()) + "1";
                end_date = dateString_db.format(cal.getTime()) + "31";
                currentDate.setText(dateString.format(cal.getTime()));

                initialized();
            }
        });
    }

    private void initialized() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        int size = AppDatabase.getInstance(this).memoDAO().getListItem(start_date, end_date).size();
        for(int i=0; i<size; i++) {
            adapter.addItems(AppDatabase.getInstance(this).memoDAO().getListItem(start_date, end_date).get(i));
        }
    }

    private void listRefresh() {
        recyclerView.removeAllViewsInLayout();
        recyclerView.setAdapter(adapter);
    }

}