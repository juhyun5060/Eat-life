package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.ColumnInfo;

public class MemoMinimal {
    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "imgUri")
    String imgUri;

    public MemoMinimal(String title, String date, String imgUri) {
        this.title = title;
        this.date = date;
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImgUri() {
        return imgUri;
    }
}
