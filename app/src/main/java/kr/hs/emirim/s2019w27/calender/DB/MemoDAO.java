package kr.hs.emirim.s2019w27.calender.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MemoDAO {

    @Query("SELECT category FROM Memo WHERE date = :date")
    int getCategory(String date);

    @Query("SELECT title FROM Memo WHERE date = :date")
    String getTitle(String date);

    @Query("SELECT memo FROM Memo WHERE date = :date")
    String getMemo(String date);

    @Query("SELECT imgUri FROM Memo WHERE date = :date")
    String getUri(String date);

    @Query("SELECT title, date, imgUri FROM Memo WHERE date BETWEEN :startDate and :endDate")
    List<MemoMinimal> getListItem(String startDate, String endDate);

    @Query("SELECT imgUri FROM Memo")
    List<SliderItem> getimages();

    @Insert
    void insert(Memo memo);

    @Query("UPDATE Memo SET category =  :category, title = :title, memo = :memo WHERE date = :date")
    void updateModify(int category, String title, String memo, String date);

    @Query("UPDATE Memo SET imgUri =  :imgUri WHERE date = :date")
    void updateUri(String imgUri, String date);

    @Query("DELETE FROM memo WHERE date = :date")
    void delete(String date);

}
