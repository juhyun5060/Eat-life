package kr.hs.emirim.s2019w27.calender.DB;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    public Context context; // 컨텍스트 저장
    ArrayList<Memo> MemoList;   // 아이템 리스트

    public CustomAdapter(Context context, ArrayList<Memo> MemoList) {
        this.context = context;
        this.MemoList = MemoList;
    }
    
    @Override
    public int getCount() {
        return MemoList.size();
    }

    @Override
    public Object getItem(int i) {
        return MemoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {    // view 생성
        return null;
    }
}
