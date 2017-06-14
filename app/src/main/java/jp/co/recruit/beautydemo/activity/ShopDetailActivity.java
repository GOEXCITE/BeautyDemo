package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.api.ImageFetcher;
import jp.co.recruit.beautydemo.api.ShopDetailFetcher;
import jp.co.recruit.beautydemo.db.ShopKeepHelper;
import jp.co.recruit.beautydemo.model.ShopDetailEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopDetailActivity extends Activity implements Handler.Callback {

    private final static String EXTRA_DETAIL_SHOP_ID = "EXTRA_DETAIL_SHOP_ID";

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

    private ShopKeepHelper keepHandler;
    private ShopDetailEntity shop;

    public static Intent newIntent(Context context, String shopId) {
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra(ShopDetailActivity.EXTRA_DETAIL_SHOP_ID, shopId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        keepHandler = new ShopKeepHelper(this);

        String shopId = getIntent().getStringExtra(EXTRA_DETAIL_SHOP_ID);
        keepButton.setVisibility(View.INVISIBLE);
        ShopDetailFetcher fetcher = new ShopDetailFetcher(new Handler(this), shopId);
        fetcher.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case ShopDetailFetcher.WHAT_ID_SUCCESS:
                shop = (ShopDetailEntity) msg.obj;
                detailShopNameTextView.setText(shop.name);
                detailShopIntroductionTextView.setText(shop.introduction);
                detailAddressTextView.setText(shop.address);
                detailAccessTextView.setText(shop.access);
                setShopKept(keepHandler.isKept(shop.id));

                if (shop.imgUrl != null) {
                    ImageFetcher imgFetcher = new ImageFetcher(detailImageView, shop.imgUrl);
                    imgFetcher.start();
                }
                return true;
            case ShopDetailFetcher.WHAT_ID_FAILED:
                break;
        }
        return false;
    }

    @OnClick(R.id.detailKeepButton)
    void keepButtonPressed() {
        if (shop.kept && keepHandler.unkeep(shop.id)) {
            setShopKept(false);
            Toast.makeText(this, "Unkept shop", Toast.LENGTH_SHORT).show();
        } else if (!shop.kept && keepHandler.keep(shop)){
            setShopKept(true);
            Toast.makeText(this, "Kept shop", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Keep/Unkeep failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.detailBackButton)
    void back() {
        Intent intent = new Intent();
        setResult(KeepListActivity.KEEP_LIST_ACTIVITY_BACK);
        finish();
    }

    private void setShopKept(boolean kept) {
        keepButton.setImageResource(kept ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        shop.kept = kept;
    }
}
