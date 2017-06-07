package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.KeepListAdapter;
import jp.co.recruit.beautydemo.db.ShopKeepHandler;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListActivity extends Activity {

    @BindView(R.id.keepListView)
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keep_list);

        ButterKnife.bind(this);

        KeepListAdapter listAdapter = new KeepListAdapter(this, R.layout.cell_keep_list, ShopKeepHandler.fetchKeptList());
        listView.setAdapter(listAdapter);
    }

    @OnClick(R.id.keepCloseButton)
    void back() {
        finish();
    }
}
