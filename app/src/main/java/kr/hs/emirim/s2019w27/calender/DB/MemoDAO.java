package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MemoDAO {
    //date 수정
    @Query("SELECT category FROM Memo")
    int getCategory();

    @Query("SELECT title FROM Memo")
    String getTitle();

    @Query("SELECT memo FROM Memo")
    String getMemo();

    @Query("SELECT date FROM Memo")
    String getDate();

    @Query("SELECT imgUri FROM Memo")
    String getUri();

    @Insert
    void insert(Memo memo);

//    @Query("UPDATE Memo SET category = '?', title = '?', memo = '?'")
//    void updateModify(Memo memo);

    @Update
    void updateAll(Memo memo);

    @Query("DELETE FROM memo")
    void deleteAll();

}
