package pt.dw.room_livedata_viewmodel.listitems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import pt.dw.room_livedata_viewmodel.db.AppDatabase;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    void deleteItem(BorrowModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }
    }
}
