package pt.dw.room_livedata_viewmodel.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by daniel on 12/01/2018.
 */

public class DateConverter {

    @TypeConverter
    static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
