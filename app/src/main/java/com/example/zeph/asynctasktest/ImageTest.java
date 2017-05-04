package com.example.zeph.asynctasktest;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class ImageTest extends Activity {

  private ImageView mImageView;
  private ProgressBar mProgressBar;
  private static String URL = "http://www.narutom.com/imgd/naruto.jpg";
  private MyAsyncTAsk myAsyncTAsk;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.image);

    mImageView = (ImageView) findViewById(R.id.image_view);
    mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

    myAsyncTAsk = new MyAsyncTAsk();
    myAsyncTAsk.execute(URL);

  }

  @Override
  protected void onPause() {
    super.onPause();
    if (myAsyncTAsk != null && myAsyncTAsk.getStatus() == Status.RUNNING);
    myAsyncTAsk.cancel(true);
  }

  class MyAsyncTAsk extends AsyncTask<String,Void,Bitmap>{


    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      if (myAsyncTAsk.isCancelled()){
        return;
      }
      mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      super.onPostExecute(bitmap);
      mProgressBar.setVisibility(View.GONE);
      mImageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {

      String url = params[0];
      Bitmap bitmap = null;
      URLConnection connection;
      InputStream is;
      try {
        connection = new URL(url).openConnection();
        is = connection.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        Thread.sleep(3000);
        bitmap = BitmapFactory.decodeStream(bis);
        is.close();
        bis.close();
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      return bitmap;
    }
  }
}
