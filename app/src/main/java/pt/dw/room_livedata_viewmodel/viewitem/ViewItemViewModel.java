package pt.dw.room_livedata_viewmodel.viewitem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import pt.dw.room_livedata_viewmodel.db.AppDatabase;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;

/**
 * Created by daniel on 12/01/2018.
 */

class ViewItemViewModel extends AndroidViewModel {

    private LiveData<BorrowModel> item;

    ViewItemViewModel(@NonNull Application application, String id) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getDatabase(this.getApplication());
        item = appDatabase.itemAndPersonModel().getItemById(id);
    }

    LiveData<BorrowModel> getItem() {
        return item;
    }

}
