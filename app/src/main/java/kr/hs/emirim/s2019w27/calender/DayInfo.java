package kr.hs.emirim.s2019w27.calender;

public class DayInfo {
    private String day;
    private boolean inMonth;

    public String getDay(){ return day;}

    public void setDay(String day) { this.day = day; }

    /* 이번 달에 있는 날짜인지 확인하기 위한 메소드 (true|false)*/
    public boolean isInMonth() { return inMonth; }

    public void setInMonth(boolean inMonth){ this.inMonth = inMonth; }
}