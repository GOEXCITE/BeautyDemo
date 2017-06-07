package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.activity.R;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopDetailActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.detailBackButton)
    void back() {
        finish();
    }
}
