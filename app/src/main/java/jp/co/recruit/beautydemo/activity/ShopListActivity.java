package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.ShopListAdapter;
import jp.co.recruit.beautydemo.api.ShopListFetcher;
import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListActivity extends AppCompatActivity implements Handler.Callback {

    @BindView(R.id.listView)
    ListView listView;

    private Handler handler;
    {
        handler = new Handler(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), ShopDetailActivity.class);
                startActivity(intent);
            }
        });

        ShopListFetcher fetcher = new ShopListFetcher(handler);
        fetcher.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == ShopListFetcher.WHAT_ID_SUCCESS) {
            List< ShopListEntity> content = (List< ShopListEntity>) msg.obj;
            ShopListAdapter listAdapter = new ShopListAdapter(this, content);
            listView.setAdapter(listAdapter);
            return true;
        } else if (msg.what == ShopListFetcher.WHAT_ID_FILED) {

        }
        return false;
    }

    @OnClick(R.id.keepButton)
    void showKeepActivity() {
        Intent intent = new Intent(getApplication(), KeepListActivity.class);
        startActivity(intent);
    }
}
