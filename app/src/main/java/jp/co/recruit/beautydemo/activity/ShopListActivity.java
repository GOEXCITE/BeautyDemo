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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit.beautydemo.adapter.ShopListAdapter;
import jp.co.recruit.beautydemo.api.ShopListFetcher;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListActivity extends AppCompatActivity implements Handler.Callback {

    @BindView(R.id.listView)
    ListView listView;

    @BindView(R.id.httpTextView)
    TextView textView;

    private Handler handler;

    {
        handler = new Handler(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        textView = (TextView) findViewById(R.id.httpTextView);
//        ButterKnife.bind(this);
//
//        ShopListAdapter listAdapter = new ShopListAdapter(this, ShopListFetcher.fetchShopList());
//        listView.setAdapter(listAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplication(), ShopDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        ShopListFetcher fetcher = new ShopListFetcher(handler);
        fetcher.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == ShopListFetcher.WHAT_ID) {
            String content = (String) msg.obj;
            textView.setText(content);
            return true;
        }
        return false;
    }


    @OnClick(R.id.keepButton)
    void showKeepActivity() {
        System.out.print("show keep activity here!");
        Intent intent = new Intent(getApplication(), KeepListActivity.class);
        startActivity(intent);
    }
}
