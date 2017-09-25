package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import jp.co.recruit.beautydemo.fragment.DemoDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiantian on 2017/09/24.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.activity_dialog_show_dialog_button)
    Button showDialogButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog);

        ButterKnife.bind(this);

        showDialogButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DemoDialogFragment demo = new DemoDialogFragment();
        demo.show(getFragmentManager(), "first dialog");
    }
}
