package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kr.hs.emirim.s2019w27.calender.AddActivity;
import kr.hs.emirim.s2019w27.calender.MainActivity;

@Database(entities = {Memo.class}, version = 4, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();

}