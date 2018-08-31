package com.shyam.roomsample;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class DataBaseAsyncTask extends AsyncTask<AppDatabase, Void, List<CustomData>> {
    @Override
    protected List<CustomData> doInBackground(AppDatabase... appDatabases) {
        AppDatabase appDatabase = appDatabases[0];
        CustomDataDao dataDao = appDatabase.customDataDao();
        List<CustomData> customDataList = dataDao.getAll();
        return customDataList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Fetching", "Start");
    }

    @Override
    protected void onPostExecute(List<CustomData> list) {
        super.onPostExecute(list);
        Log.d("Fetching", "Done");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
