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

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.s2019w27.calender.AddActivity;
import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;
import kr.hs.emirim.s2019w27.calender.DB.MemoMinimal;
import kr.hs.emirim.s2019w27.calender.GVCalendarActivity;
import kr.hs.emirim.s2019w27.calender.R;

public class ListActivity extends AppCompatActivity {
    // 리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;

    private ImageView goToCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initialized();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        goToCalendar = findViewById(R.id.goToCalendarImg);

        goToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GVCalendarActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialized() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        int size = AppDatabase.getInstance(this).memoDAO().getAll().size();
        for(int i=0; i<size; i++) {
            adapter.addItems(AppDatabase.getInstance(this).memoDAO().getTD().get(i));
        }
    }

}