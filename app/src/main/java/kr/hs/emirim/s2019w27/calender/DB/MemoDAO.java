package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MemoDAO {
    //date 수정
    @Query("SELECT id FROM Memo")
    int getId();

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

    @Query("SELECT * FROM Memo")
    List<Memo> getAll();

    @Query("SELECT id, category, title, date FROM Memo")
    List<Memo> getTD();

    @Insert
    void insert(Memo memo);

    @Query("UPDATE Memo SET category =  :category, title = :title, memo = :memo")
    void updateModify(int category, String title, String memo);

    @Query("UPDATE Memo SET imgUri =  :imgUri")
    void updateUri(String imgUri);

    @Query("DELETE FROM memo")
    void deleteAll();

}
