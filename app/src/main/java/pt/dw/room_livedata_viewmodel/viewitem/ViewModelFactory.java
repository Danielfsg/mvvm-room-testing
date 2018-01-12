package pt.dw.room_livedata_viewmodel.viewitem;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private Application application;
    private String mParam;


    ViewModelFactory(Application application, String mParam) {
        this.application = application;
        this.mParam = mParam;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewItemViewModel(application, mParam);
    }
}
