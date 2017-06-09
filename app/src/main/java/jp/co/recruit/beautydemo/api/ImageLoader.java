package jp.co.recruit.beautydemo.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 01011776 on 2017/06/09.
 */

public class ImageLoader extends Thread {

    public static final int WHAT_ID_IMAGE_LOADED_SUCCESS = 3;
    public static final int WHAT_ID_IMAGE_LOADED_FILED = 4;

    private Handler handler;
    private String imgUrl;

    public ImageLoader(Handler handler, String imgUrl) {
        this.handler = handler;
        this.imgUrl = imgUrl;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(imgUrl);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            if (con.getResponseCode() == 200 && con.getResponseMessage().equals("OK")) {

                InputStream inputStream = con.getInputStream();
                BitmapFactory.Options bmOptions;
                bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 1;
                Bitmap result = BitmapFactory.decodeStream(inputStream, null, bmOptions);

                Message msg = Message.obtain(handler,WHAT_ID_IMAGE_LOADED_SUCCESS, result);
                handler.sendMessage(msg);
            } else {
                Message msg = Message.obtain(handler,WHAT_ID_IMAGE_LOADED_FILED, null);
                handler.sendMessage(msg);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
