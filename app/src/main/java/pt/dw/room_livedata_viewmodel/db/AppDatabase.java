package pt.dw.room_livedata_viewmodel.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BorrowModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    private static final String DATABASE_NAME = "borrow-db";

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract BorrowModelDao itemAndPersonModel();
}
