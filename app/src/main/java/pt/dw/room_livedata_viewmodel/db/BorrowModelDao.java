package pt.dw.room_livedata_viewmodel.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {

    @Query("SELECT * FROM BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    @Query("SELECT * FROM BorrowModel WHERE id = :id")
    LiveData<BorrowModel> getItemById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);

}