package kr.hs.emirim.s2019w27.calender;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import java.util.*;


public class CalendarAdapter extends BaseAdapter {
    private ArrayList<DayInfo> dayList;
    private Context context;
    private int resource;
    private LayoutInflater inflater;

    public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList) {
        this.context = context;
        this.dayList = dayList;
        this.resource = textResource;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() { return dayList.size(); }

    @Override
    public Object getItem(int position) { return dayList.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DayInfo day = dayList.get(position);
        DayViewHolder dayViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(resource, null);

            if (position % 7 == 6) {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP() + getRestCellWidthDP(), getCellHeightDP()));
            } else {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));
            }
            dayViewHolder = new DayViewHolder();

            dayViewHolder.DayBackground = (LinearLayout) convertView.findViewById(R.id.day_background);
            dayViewHolder.tvDay = (TextView) convertView.findViewById(R.id.tv_day);

            convertView.setTag(dayViewHolder);
        } else {
            dayViewHolder = (DayViewHolder) convertView.getTag();
        }

        if (day != null) {
            dayViewHolder.tvDay.setText(day.getDay());

            if (day.isInMonth()) {
                if (position % 7 == 0) {
                    dayViewHolder.tvDay.setTextColor(Color.RED);
                } else if (position % 7 == 6) {
                    dayViewHolder.tvDay.setTextColor(Color.BLUE);
                } else {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            } else {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }
        }
        return convertView;
    }

    public class DayViewHolder {
        public LinearLayout DayBackground;
        public TextView tvDay;
    }


    /* 화면 크기를 받아와 셀 가로/세로 크기 조정 */
    private int getCellWidthDP() {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = width / 7;
        return cellWidth;
    }

    private int getRestCellWidthDP() {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = width % 7;
        return cellWidth;
    }

    private int getCellHeightDP() {
        int height = context.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = height / 6;
        return cellHeight;
    }
}