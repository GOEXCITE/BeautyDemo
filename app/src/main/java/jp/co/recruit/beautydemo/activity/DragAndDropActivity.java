package jp.co.recruit.beautydemo.activity;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 01011776 on 2017/09/25.
 */

public class DragAndDropActivity extends Activity {

    @BindView(R.id.drag_drop_view)
    View dragdropView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drag_and_drop);

        ButterKnife.bind(this);

        dragdropView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
        dragdropView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(Color.BLUE);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(Color.RED);
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundColor(Color.CYAN);
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
