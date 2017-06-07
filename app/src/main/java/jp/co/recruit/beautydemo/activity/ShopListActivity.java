package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.activity.KeepListActivity;
import jp.co.recruit.beautydemo.activity.R;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListActivity extends Activity {
    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.keepButton)
    void showKeepActivity() {
        System.out.print("show keep activity here!");
        Intent intent = new Intent(getApplication(), KeepListActivity.class);
        startActivity(intent);
    }
}
