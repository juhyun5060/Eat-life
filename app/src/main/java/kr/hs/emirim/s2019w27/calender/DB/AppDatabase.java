package kr.hs.emirim.s2019w27.calender.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 4, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();
    private static AppDatabase instance = null;

    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "memo_Database").allowMainThreadQueries().build();
        }
        return instance;
    }
}