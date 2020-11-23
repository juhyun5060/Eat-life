package kr.hs.emirim.s2019w27.calender.decorators;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.*;

import kr.hs.emirim.s2019w27.calender.R;

public class BackgroundDecorator implements DayViewDecorator {
    private final Drawable drawable;

    public BackgroundDecorator(Activity context){
        drawable = context.getResources().getDrawable(R.drawable.bg_rect_border);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}