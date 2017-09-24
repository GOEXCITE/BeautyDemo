package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiantian on 2017/09/24.
 */

public class DialogActivity extends Activity implements View.OnClickListener{

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");

        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(DialogActivity.this, "You clicked yes button",Toast.LENGTH_LONG).show();
                    }
                });

//        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });

//        alertDialogBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener(){
//
//        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
