package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Memo extends ArrayList<Memo> {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String date;
    private int category;
    private String title;
    private String memo;
    private String imgUri;

    public Memo(String date, int category, String title, String memo, String imgUri) {
        this.date = date;
        this.category = category;
        this.title = title;
        this.memo = memo;
        this.imgUri = imgUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

}

