package pt.dw.room_livedata_viewmodel.additem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import pt.dw.room_livedata_viewmodel.db.AppDatabase;
import pt.dw.room_livedata_viewmodel.db.BorrowModel;

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addItem(BorrowModel borrowModel) {
        new addAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class addAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(BorrowModel... params) {
            db.itemAndPersonModel().addBorrow(params[0]);
            return null;
        }


    }
}
