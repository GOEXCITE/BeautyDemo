package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.ShopListAdapter;
import jp.co.recruit.beautydemo.api.ShopDetailFetcher;
import jp.co.recruit.beautydemo.api.ShopListFetcher;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        String shopId = getIntent().getStringExtra(EXTRA_DETAIL_SHOP_ID);
        ShopDetailFetcher fetcher = new ShopDetailFetcher(new Handler(this), shopId);
        fetcher.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == ShopDetailFetcher.WHAT_ID_SUCCESS) {
            ShopDetailEntity detail = (ShopDetailEntity) msg.obj;
            detailShopNameTextView.setText(detail.name);
            detailShopIntroductionTextView.setText(detail.introduction);
            detailAddressTextView.setText(detail.address);
            detailAccessTextView.setText(detail.access);
            return true;
        } else if (msg.what == ShopListFetcher.WHAT_ID_FILED) {

        }
        return false;
    }

    @OnClick(R.id.detailKeepButton)
    void keepButtonPressed() {

    }

    @OnClick(R.id.detailBackButton)
    void back() {
        finish();
    }
}
