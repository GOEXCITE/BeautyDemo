package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.ShopListAdapter;
import jp.co.recruit.beautydemo.api.ShopListFetcher;

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

        ShopListAdapter listAdapter = new ShopListAdapter(this, ShopListFetcher.fetchShopList());
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), ShopDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.keepButton)
    void showKeepActivity() {
        System.out.print("show keep activity here!");
        Intent intent = new Intent(getApplication(), KeepListActivity.class);
        startActivity(intent);
    }
}
