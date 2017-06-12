package jp.co.recruit.beautydemo.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.compat.BuildConfig;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 01011776 on 2017/06/12.
 */

public class ImageFetcher extends BaseFetcher {

    protected static String TAG = "";

    private ImageView imageView;
    private String imgUrl;

    public ImageFetcher(ImageView imageView, String imgUrl) {
        this.imageView = imageView;
        this.imgUrl = imgUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(imgUrl);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            if (con.getResponseCode() == FETCHSUCCESSED && con.getResponseMessage().equals("OK")) {

                InputStream inputStream = con.getInputStream();
                BitmapFactory.Options bmOptions;
                bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 1;
                Bitmap result = BitmapFactory.decodeStream(inputStream, null, bmOptions);

                imageView.setImageBitmap(result);
            } else {
                // TODO: set imageView "NoImage"
            }
        } catch(Exception ex) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, ex.getMessage(), ex);
            }
        }
    }
}
