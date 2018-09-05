package com.shyam.roomsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private LiveData<List<CustomData>> liveDataList;
    private AppDatabase appDatabase;

    DataViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(application.getApplicationContext());
        CustomDataDao dataDao = this.appDatabase.customDataDao();
        liveDataList = dataDao.getAll();
    }

    public LiveData<List<CustomData>> getCustomDataObservable() {
        return liveDataList;
    }

    public void addData(final CustomData customData) {
        new AddAsyncTask(appDatabase).execute(customData);
    }


    private class AddAsyncTask extends AsyncTask<CustomData, Void, Void> {

        private AppDatabase appDatabase;

        AddAsyncTask(AppDatabase appDatabase) {
            this.appDatabase= appDatabase;
        }


        @Override
        protected Void doInBackground(final CustomData... data) {
            CustomDataDao dataDao = appDatabase.customDataDao();
            dataDao.insertData(data[0]);
            return null;
        }
    }
}
