package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keep_list);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.backButton)
    void back() {
        finish();
    }
}