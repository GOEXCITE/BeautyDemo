package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HollyTian on 2017/09/23.
 */

public class TopActivity extends Activity {

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.showNameTextView)
    TextView showNameTextView;

    @BindView(R.id.okButton)
    Button okButton;

    @BindView(R.id.showDialogButton)
    Button showDialogButton;

    @BindView(R.id.showDragDropViewButton)
    Button showDragDropViewButton;

    @BindView(R.id.databaseDemoButton)
    Button databaseDemoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_top);

        ButterKnife.bind(this);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameTextView.setText(nameEditText.getText().toString());
            }
        });

        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        databaseDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showWebViewItem:
                Uri uri = Uri.parse("https://developer.android.com/guide/topics/ui/menus.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
