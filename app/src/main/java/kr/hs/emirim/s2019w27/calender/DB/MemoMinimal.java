package kr.hs.emirim.s2019w27.calender.DB;

public class MemoMinimal {
    String title;
    String date;

    public MemoMinimal(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
