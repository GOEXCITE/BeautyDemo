package jp.co.recruit.beautydemo.api;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListFetcher extends Thread {

    public static final int WHAT_ID = 1;

    private Handler handler;

    public ShopListFetcher(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            // 大阪の天気予報XMLデータ
            URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int bbb = con.getResponseCode();
            String aaa = con.getResponseMessage();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null ) {
                builder.append(line);
            }

            final String content = builder.toString();

            Message msg = Message.obtain(handler,WHAT_ID, content);
            handler.sendMessage(msg);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static private List<ShopListEntity> fetchShopList() {
        List<ShopListEntity> result = new ArrayList<ShopListEntity>();

        ShopListEntity one = new ShopListEntity();
        one.name = "アミスバイエアー(amis by air)";
        one.address = "東京都中央区銀座７ー１０ー５　デュープレックアレックス";
        one.access = "東京メトロ銀座駅徒歩３分　東銀座駅徒歩３分「銀座 Respla」";
        one.imgUrl = "https://thumb1.shutterstock.com/display_pic_with_logo/105328/364773500/stock-photo-flirting-hispanic-couple-with-smartphone-in-square-composition-with-selective-focus-364773500.jpg";
        one.kept = false;
        result.add(one);

        ShopListEntity two = new ShopListEntity();
        two.name = "レスピア(Respia)";
        two.address = "東京都中央区銀座１ー１４ー１０　松下ビル１F";
        two.access = "東京メトロ銀座駅徒歩5分　銀座一丁目駅徒歩１分";
        two.imgUrl = "https://thumb1.shutterstock.com/display_pic_with_logo/105328/364773500/stock-photo-flirting-hispanic-couple-with-smartphone-in-square-composition-with-selective-focus-364773500.jpg";
        two.kept = true;
        result.add(two);

        ShopListEntity three = new ShopListEntity();
        three.name = "Claude MONET 【クロード・モネ】 汐留店";
        three.address = "東京都港区東新橋１-5-2汐留シティセンタービルＢ２Ｆ";
        three.access = "地下鉄大江戸線汐留駅徒歩1分/ゆりかもめ新橋駅徒歩1分/JR新橋駅徒歩3分";
        three.imgUrl = "https://thumb1.shutterstock.com/display_pic_with_logo/105328/364773500/stock-photo-flirting-hispanic-couple-with-smartphone-in-square-composition-with-selective-focus-364773500.jpg";
        three.kept = true;
        result.add(three);

        return result;
    }

    static private String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
