package kr.hs.emirim.s2019w27.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final String[] LIST_MENU = {"리스트1", "리스트2", "리스트3", "리스트4", "리스트5"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listView = findViewById(R.id.listview1);

        listView.setAdapter(adapter);

    }
}