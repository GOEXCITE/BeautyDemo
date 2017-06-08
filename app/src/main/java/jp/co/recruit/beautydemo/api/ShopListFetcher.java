package jp.co.recruit.beautydemo.api;

import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListFetcher extends Thread {

    public static final int WHAT_ID_SUCCESS = 1;
    public static final int WHAT_ID_FILED = 2;

    private Handler handler;
    private String urlStr = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1?key=61db16c80f41a733&address=%E9%8A%80%E5%BA%A7&order=3&format=json";

    public ShopListFetcher(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            
            URL url = new URL(urlStr);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == 200 && con.getResponseMessage().equals("OK")) {

                List<ShopListEntity> resultList = new LinkedList<>();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null ) {
                    builder.append(line);
                }

                JSONObject obj = new JSONObject(builder.toString());
                JSONObject resultObj = obj.getJSONObject("results");
                JSONArray shopObjs = resultObj.getJSONArray("shop");

                for (int i = 0; i < shopObjs.length() - 1; i++) {
                    JSONObject item = (JSONObject) shopObjs.get(i);

                    ShopListEntity addItem = new ShopListEntity();
                    addItem.name = item.getString("name");
                    addItem.address = item.getString("address");
                    addItem.access = item.getString("mobile_access");

                    JSONObject photos = item.getJSONObject("photo");
                    JSONObject mobilePhotos = photos.getJSONObject("mobile");
                    addItem.imgUrl = (String) mobilePhotos.get("l");

                    resultList.add(addItem);
                    if (resultList.size() > 10) {
                        break;
                    }
                }

                Message msg = Message.obtain(handler,WHAT_ID_SUCCESS, resultList);
                handler.sendMessage(msg);
            } else {
                Message msg = Message.obtain(handler,WHAT_ID_FILED, null);
                handler.sendMessage(msg);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
