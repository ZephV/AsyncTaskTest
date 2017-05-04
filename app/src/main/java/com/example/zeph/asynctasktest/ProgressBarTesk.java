package com.example.zeph.asynctasktest;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarTesk extends Activity {

  private ProgressBar mProgressBar;
  private MyAsyncTask mAsync;

  @Override
  protected void onCreate( Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pg);

    mProgressBar = (ProgressBar) findViewById(R.id.pgb);

    mAsync = new MyAsyncTask();
    mAsync.execute();

  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mAsync != null && mAsync.getStatus() == AsyncTask.Status.RUNNING){
      mAsync.cancel(true);
    }
  }

  class MyAsyncTask extends AsyncTask<Void,Integer,Void>{

    @Override
    protected Void doInBackground(Void... params) {
      for (int i = 0; i < 100; i++) {
        if (mAsync.isCancelled()){
          break;
        }
        publishProgress(i);
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
      super.onProgressUpdate(values);
      if (mAsync.isCancelled()){
        return;
      }
      mProgressBar.setProgress(values[0]);
    }
  }
}
