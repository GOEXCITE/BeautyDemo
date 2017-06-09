package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.KeepListAdapter;
import jp.co.recruit.beautydemo.db.ShopKeepHandler;
import jp.co.recruit.beautydemo.model.ShopKeptEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListActivity extends Activity {

    static final int KEEP_LIST_ACTIVITY_BACK = 10;

    @BindView(R.id.keepListView)
    ListView listView;

    private List<ShopKeptEntity> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keep_list);

        ButterKnife.bind(this);

        reloadKeptList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KeepListActivity.this, ShopDetailActivity.class);
                String id = list.get(i).id;
                intent.putExtra(ShopDetailActivity.EXTRA_DETAIL_SHOP_ID,id);
                startActivityForResult(intent, KEEP_LIST_ACTIVITY_BACK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEEP_LIST_ACTIVITY_BACK) {
            reloadKeptList();
        }
    }

    @OnClick(R.id.keepBackButton)
    void back() {
        finish();
    }

    void reloadKeptList() {
        ShopKeepHandler handler = new ShopKeepHandler(this);
        List<ShopKeptEntity> rawValues = handler.keptShops();
        if (rawValues == null) {
            list = new ArrayList<ShopKeptEntity>();
        } else {
            list = rawValues;
        }
        KeepListAdapter listAdapter = new KeepListAdapter(this, list);
        listView.setAdapter(listAdapter);
    }
}
