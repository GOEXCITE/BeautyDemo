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

    @BindView(R.id.keepListView)
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keep_list);

        ButterKnife.bind(this);

        ShopKeepHandler handler = new ShopKeepHandler(this);
        List<ShopKeptEntity> keptShops = handler.keptShops();
        KeepListAdapter listAdapter = new KeepListAdapter(this, R.layout.cell_keep_list, keptShops == null ? new ArrayList<ShopKeptEntity>() : keptShops);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), ShopDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.keepBackButton)
    void back() {
        finish();
    }
}
