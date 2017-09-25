package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiantian on 2017/09/24.
 */

public class DatabaseActivity extends Activity {

    @BindView(R.id.show_value_text_view)
    TextView showValueTextView;

    @BindView(R.id.save_button)
    Button saveButton;

    @BindView(R.id.show_button)
    Button showButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database);

        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", 0);
        String name = sharedPreferences.getString("name", "tt");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "new value"); //putBoolean("silentMode", mSilentMode);
                editor.apply();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", 0);
                String name = sharedPreferences.getString("name", null);
                showValueTextView.setText(name);
            }
        });
    }
}
