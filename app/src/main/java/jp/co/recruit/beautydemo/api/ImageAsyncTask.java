package jp.co.recruit.beautydemo.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.compat.BuildConfig;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 01011776 on 2017/06/14.
 */

public class ImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private static String TAG = "ImageAsyncTask";

    public static final int FETCHSUCCESSED = 200;

    private ImageView imageView;

    public ImageAsyncTask(ImageView imageView) {
        super();
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap result = null;

        try {
            URL url = new URL(params[0]);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            if (con.getResponseCode() == FETCHSUCCESSED && con.getResponseMessage().equals("OK")) {

                InputStream inputStream = con.getInputStream();
                BitmapFactory.Options bmOptions;
                bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 1;
                result = BitmapFactory.decodeStream(inputStream, null, bmOptions);
            } else {
                // TODO: set imageView "NoImage"
            }
        } catch(Exception ex) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, ex.getMessage(), ex);
            }
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        System.out.print(values[0].toString());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

    @Override
    protected void onCancelled() {
        System.out.print("キャンセルされました。");
    }
}
