package kr.hs.emirim.s2019w27.calender.listView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import kr.hs.emirim.s2019w27.calender.AddActivity;
import kr.hs.emirim.s2019w27.calender.DB.Memo;
import kr.hs.emirim.s2019w27.calender.DB.MemoMinimal;
import kr.hs.emirim.s2019w27.calender.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<MemoMinimal> memoData = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(memoData.get(position));
    }

    @Override
    public int getItemCount() {
        return memoData.size();
    }

    public void addItems(MemoMinimal memo) {
        memoData.add(memo);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.listTitle);
            date = itemView.findViewById(R.id.listDate);
            image = itemView.findViewById(R.id.listImg);
        }

        public void onBind(MemoMinimal memo) {
            title.setText(memo.getTitle());
            date.setText(memo.getDate());
            image.setImageURI(Uri.parse(memo.getImgUri()));
        }
    }
}