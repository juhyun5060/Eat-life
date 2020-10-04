package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Memo {
    @PrimaryKey(autoGenerate = true)
    //private Date date;
    private int category;
    private String title;
    private String memo;
    //private String img;

    public Memo(int category, String title, String memo) {
        this.category = category;
        this.title = title;
        this.memo = memo;
        //this.date = date;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("Memo{");
//        sb.append("category=").append(category);
//        sb.append(", title=").append(title);
//        sb.append(", memo=").append(memo);
//        sb.append("}");
//        return sb.toString();
//    }
}
