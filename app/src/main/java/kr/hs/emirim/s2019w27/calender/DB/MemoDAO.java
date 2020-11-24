package kr.hs.emirim.s2019w27.calender.DB;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MemoDAO {

    @Query("SELECT id FROM Memo WHERE date = :date")
    int getId(String date);

    @Query("SELECT category FROM Memo WHERE date = :date")
    int getCategory(String date);

    @Query("SELECT title FROM Memo WHERE date = :date")
    String getTitle(String date);

    @Query("SELECT memo FROM Memo WHERE date = :date")
    String getMemo(String date);

    @Query("SELECT date FROM Memo WHERE date = :date")
    String getDate(String date);

    @Query("SELECT imgUri FROM Memo WHERE date = :date")
    String getUri(String date);

    @Query("SELECT * FROM Memo")
    List<Memo> getAll();

//    @Query("SELECT title, date, imgUri FROM Memo")
//    List<MemoMinimal> getListItem();

    @Query("SELECT title, date, imgUri FROM Memo WHERE date BETWEEN :startDate and :endDate")
    List<MemoMinimal> getListItem(String startDate, String endDate);

    @Insert
    void insert(Memo memo);

    @Query("UPDATE Memo SET category =  :category, title = :title, memo = :memo")
    void updateModify(int category, String title, String memo);

    @Query("UPDATE Memo SET imgUri =  :imgUri")
    void updateUri(String imgUri);

    @Query("DELETE FROM memo WHERE date = :date")
    void delete(String date);

}
