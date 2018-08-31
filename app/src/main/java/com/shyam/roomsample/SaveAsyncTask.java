package com.shyam.roomsample;

import android.os.AsyncTask;
import android.util.Log;

public class SaveAsyncTask extends AsyncTask<String, Void, Integer> {

    private AppDatabase appDatabase;

    SaveAsyncTask(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Integer doInBackground(String... text) {
        CustomDataDao dataDao = appDatabase.customDataDao();
        int id = dataDao.getMaxId() == 0 ? 1 : dataDao.getMaxId() + 1;
        CustomData data = new CustomData(id, text[0]);
        dataDao.insertData(data);
        return id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Saving", "Start");
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.d("Saving", "Done");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
