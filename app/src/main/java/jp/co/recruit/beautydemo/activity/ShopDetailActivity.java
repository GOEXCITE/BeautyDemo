package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.KeepListAdapter;
import jp.co.recruit.beautydemo.adapter.ShopListAdapter;
import jp.co.recruit.beautydemo.api.ImageLoader;
import jp.co.recruit.beautydemo.api.ShopDetailFetcher;
import jp.co.recruit.beautydemo.api.ShopListFetcher;
import jp.co.recruit.beautydemo.db.ShopKeepHandler;
import jp.co.recruit.beautydemo.model.ShopDetailEntity;
import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopDetailActivity extends Activity implements Handler.Callback {

    final static String EXTRA_DETAIL_SHOP_ID = "EXTRA_DETAIL_SHOP_ID";

    @BindView(R.id.detailImageView)
    ImageView detailImageView;

    @BindView(R.id.detailShopNameTextView)
    TextView detailShopNameTextView;

    @BindView(R.id.detailShopIntroductionTextView)
    TextView detailShopIntroductionTextView;

    @BindView(R.id.detailAddressTextView)
    TextView detailAddressTextView;

    @BindView(R.id.detailAccessTextView)
    TextView detailAccessTextView;

    @BindView(R.id.detailKeepButton)
    ImageButton keepButton;

    private ShopKeepHandler keepHandler;
    private ShopDetailEntity shop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        keepHandler = new ShopKeepHandler(this);

        String shopId = getIntent().getStringExtra(EXTRA_DETAIL_SHOP_ID);
        keepButton.setVisibility(View.INVISIBLE);
        ShopDetailFetcher fetcher = new ShopDetailFetcher(new Handler(this), shopId);
        fetcher.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == ShopDetailFetcher.WHAT_ID_SUCCESS) {
            shop = (ShopDetailEntity) msg.obj;
            detailShopNameTextView.setText(shop.name);
            detailShopIntroductionTextView.setText(shop.introduction);
            detailAddressTextView.setText(shop.address);
            detailAccessTextView.setText(shop.access);
            setShopKept(keepHandler.isKept(shop.id));
            keepButton.setVisibility(View.VISIBLE);

            if (shop.imgUrl != null) {
                ImageLoader loader = new ImageLoader(new Handler(this), shop.imgUrl);
                loader.start();
            }

            return true;
        } else if (msg.what == ShopListFetcher.WHAT_ID_FILED) {

        } else if (msg.what == ImageLoader.WHAT_ID_IMAGE_LOADED_SUCCESS) {
            Bitmap img = (Bitmap) msg.obj;
            detailImageView.setImageBitmap(img);
        } else if (msg.what == ImageLoader.WHAT_ID_IMAGE_LOADED_FILED) {

        }
        return false;
    }

    @OnClick(R.id.detailKeepButton)
    void keepButtonPressed() {
        if (shop.kept && keepHandler.unkeep(shop.id)) {
            setShopKept(false);
            Toast.makeText(this, "unkept shop", Toast.LENGTH_SHORT).show();
        } else if (!shop.kept && keepHandler.keep(shop)){
            setShopKept(true);
            Toast.makeText(this, "kept shop", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.detailBackButton)
    void back() {
        Intent intent = new Intent();
        setResult(KeepListActivity.KEEP_LIST_ACTIVITY_BACK);
        finish();
    }

    private void setShopKept(boolean kept) {
        if (kept) {
            keepButton.setImageResource(R.drawable.ic_heart_filled);
        } else {
            keepButton.setImageResource(R.drawable.ic_heart);
        }
        shop.kept = kept;
    }
}
