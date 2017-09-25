package jp.co.recruit.beautydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SearchEvent;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Logger;

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

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.listView)
    ListView listView;

    private List<ShopListEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = ShopDetailActivity.newIntent(ShopListActivity.this, list.get(i).id);
                startActivity(intent);
            }
        });

        ShopListFetcher fetcher = new ShopListFetcher(new Handler(this));
        fetcher.start();

        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchWord) {
                searched();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(onQueryTextListener);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == ShopListFetcher.WHAT_ID_SUCCESS) {
            list = (List< ShopListEntity>) msg.obj;
            ShopListAdapter listAdapter = new ShopListAdapter(this, list);
            listView.setAdapter(listAdapter);
            return true;
        } else if (msg.what == ShopListFetcher.WHAT_ID_FAILED) {
            Toast.makeText(this, "Shop list fetcher failed!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @OnClick(R.id.keepButton)
    void showKeepActivity() {
        Intent intent = new Intent(getApplication(), KeepListActivity.class);
        startActivity(intent);
    }

    private void searched() {
        searchView.clearFocus();
        Toast.makeText(this, "Searched!", Toast.LENGTH_SHORT).show();
    }
}
