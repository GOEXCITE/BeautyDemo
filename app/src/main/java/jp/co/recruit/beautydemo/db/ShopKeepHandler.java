package jp.co.recruit.beautydemo.db;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit.beautydemo.model.ShopKeptEntity;
import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopKeepHandler {

    static public List<ShopKeptEntity> fetchKeptList() {
        List<ShopKeptEntity> result = new ArrayList<ShopKeptEntity>();

        ShopKeptEntity one = new ShopKeptEntity();
        one.name = "アミスバイエアー(amis by air)";
        one.imgUrl = "https://thumb1.shutterstock.com/display_pic_with_logo/105328/364773500/stock-photo-flirting-hispanic-couple-with-smartphone-in-square-composition-with-selective-focus-364773500.jpg";
        result.add(one);

        ShopKeptEntity two = new ShopKeptEntity();
        two.name = "レスピア(Respia)";
        two.imgUrl = "https://thumb1.shutterstock.com/display_pic_with_logo/105328/364773500/stock-photo-flirting-hispanic-couple-with-smartphone-in-square-composition-with-selective-focus-364773500.jpg";
        result.add(two);

        return result;
    }
}
