package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.recruit.beautydemo.db.DemoSquidDB;
import jp.co.recruit.beautydemo.model.PersonSpec;

/**
 * Created by HollyTian on 2017/09/23.
 */

public class TopActivity extends Activity {

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.showNameTextView)
    TextView showNameTextView;

    @BindView(R.id.okButton)
    Button okButton;

    @BindView(R.id.showDialogButton)
    Button showDialogButton;

    @BindView(R.id.preferenceDemoButton)
    Button preferenceDemoButton;

    @BindView(R.id.showDragDropViewButton)
    Button showDragDropViewButton;

    @BindView(R.id.databaseDemoButton)
    Button databaseDemoButton;

    @BindView(R.id.notificationButton)
    Button notificationButton;

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

        preferenceDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        showDragDropViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, DragAndDropActivity.class);
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

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
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
            case R.id.showBeautyDemoItem:
                Intent intent1 = new Intent(TopActivity.this, ShopListActivity.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

        Intent resultIntent = new Intent(this, NotifiedActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotifiedActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, mBuilder.build());
    }

    private void sampleDBCode() {
        DemoSquidDB db = new DemoSquidDB(); // Important: db instances should always be singletons

        PersonSpec newPerson = new PersonSpec()
                .setFirstName("Sam");
        db.persist(newPerson);
    }
}
