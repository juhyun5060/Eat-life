package kr.hs.emirim.s2019w27.calender;

public class DayItem {
    private int dayValue;

    DayItem(int dayValue){
        this.dayValue = dayValue;
    }

    public int getDay(){
        return dayValue;
    }
}