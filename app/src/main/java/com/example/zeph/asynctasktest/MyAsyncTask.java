package com.example.zeph.asynctasktest;


import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void ,Void> {

  @Override
  protected Void doInBackground(Void... params) {
    return null;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
  }

  @Override
  protected void onProgressUpdate(Void... values) {
    super.onProgressUpdate(values);
  }
}
