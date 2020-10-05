package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MemoDAO {

    @Query("SELECT title FROM Memo") //date 수정
    String getTitle();

    @Query("SELECT memo FROM Memo")
    String getMemo();

    @Query("SELECT category FROM Memo")
    int getCategory();

    @Query("SELECT date FROM Memo")
    String getDate();

    @Insert
    void insert(Memo memo);

    @Update
    void update(Memo memo);

    @Query("DELETE FROM memo")
    void deleteAll();

    //    @Update
//    void update(Memo memo);
}
