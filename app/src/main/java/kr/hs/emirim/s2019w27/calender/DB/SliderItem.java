package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.ColumnInfo;

public class SliderItem {

    @ColumnInfo(name = "imgUri")
    String images;

    SliderItem(String images) {
        this.images = images;
    }

    public String getimages() {
        return images;
    }
}