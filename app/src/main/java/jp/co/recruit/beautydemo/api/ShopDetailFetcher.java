package jp.co.recruit.beautydemo.api;

import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.co.recruit.beautydemo.model.ShopDetailEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopDetailFetcher extends BaseFetcher {
    public static final int WHAT_ID_SUCCESS = 1;
    public static final int WHAT_ID_FAILED = 2;

    private Handler handler;
    private String urlStr = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1?key=61db16c80f41a733&format=json&id=";

    public ShopDetailFetcher(Handler handler, String shopId) {
        this.handler = handler;
        this.urlStr = this.urlStr + shopId;
    }

    @Override
    public void run() {
        try {

            URL url = new URL(urlStr);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == FETCHSUCCESSED && con.getResponseMessage().equals("OK")) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null ) {
                    builder.append(line);
                }

                JSONObject obj = new JSONObject(builder.toString());
                JSONObject resultObj = obj.getJSONObject("results");
                JSONArray shopObjs = resultObj.getJSONArray("shop");

                JSONObject item = (JSONObject) shopObjs.get(0);

                ShopDetailEntity result = new ShopDetailEntity();
                result.id = item.getString("id");
                result.name = item.getString("name");
                if (item.get("other_memo") != null) {
                    result.introduction = item.getString("other_memo");
                }
                if (item.get("private_room") != null) {
                    result.introduction = result.introduction + "\n 個室: " + item.get("private_room");
                }
                if (item.get("free_drink") != null) {
                    result.introduction = result.introduction + "\n フリードリンク: " + item.get("free_drink");
                }
                if (item.get("catch") != null) {
                    result.introduction = result.introduction + "\n 注目: " + item.get("catch");
                }

                result.address = item.getString("address");
                result.access = item.getString("mobile_access");

                JSONObject photos = item.getJSONObject("photo");
                JSONObject mobilePhotos = photos.getJSONObject("mobile");
                result.imgUrl = (String) mobilePhotos.get("l");

                Message msg = Message.obtain(handler,WHAT_ID_SUCCESS, result);
                handler.sendMessage(msg);
            } else {
                Message msg = Message.obtain(handler,WHAT_ID_FAILED, null);
                handler.sendMessage(msg);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
