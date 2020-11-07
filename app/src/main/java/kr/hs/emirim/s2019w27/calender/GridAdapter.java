//package kr.hs.emirim.s2019w27.calender.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.Calendar;
//import java.util.List;
//
//import kr.hs.emirim.s2019w27.calender.R;
//import kr.hs.emirim.s2019w27.calender.activity.MainActivity;
//
//public class GridAdapter extends BaseAdapter {
//    final List<String> list;
//    final LayoutInflater inflater;
//
//
//    public GridAdapter(Context context, List<String> list) {
//        this.list = list;
//        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//       ViewHolder holder = null;
//
//        if(convertView==null){
//            convertView =inflater.inflate(R.layout.item_calendar_gridview, parent, false);
//            holder = new ViewHolder();
//            holder.tv_item_gv = (TextView)convertView.findViewById(R.id.tv_item_gv);
//            convertView.setTag(holder);
//        }else{
//            holder=(ViewHolder)convertView.getTag();
//        }
//        holder.tv_item_gv.setText(""+getItem(position));
//
//        // set textColor, backgroundColor
//        MainActivity.calendar = Calendar.getInstance();
//
//        // get today
//        Integer today = MainActivity.calendar.get(Calendar.DAY_OF_MONTH);
//        String sToday = String.valueOf(today);
//
//        //changing today textColor
//        if(sToday.equals(getItem(position))) {
//            holder.tv_item_gv.setTextColor(getResources().getColor(R.color.colorOrange));
//        }
//
//        return convertView;
//    }
//
//    public static class ViewHolder{
//        TextView tv_item_gv;
//    }
//}
//
