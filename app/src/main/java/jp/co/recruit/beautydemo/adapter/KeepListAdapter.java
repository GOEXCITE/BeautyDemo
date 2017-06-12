package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jp.co.recruit.beautydemo.activity.R;
import jp.co.recruit.beautydemo.api.ImageFetcher;
import jp.co.recruit.beautydemo.db.ShopKeepHandler;
import jp.co.recruit.beautydemo.model.KeepListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListAdapter extends ArrayAdapter<KeepListEntity> {

    private List<KeepListEntity> list;
    private Context context;
    static private int ID = R.layout.cell_keep_list;

    private Handler handler = new Handler();
    private ShopKeepHandler keepHandler;

    public KeepListAdapter(@NonNull Context context, @NonNull List<KeepListEntity> objects) {
        super(context, ID, objects);
        this.list = objects;
        this.context = context;

        keepHandler = new ShopKeepHandler(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View cell = setupCell(position, convertView);
        return cell;
    }

    @NonNull
    private View setupCell(final int position, View convertView) {
        View cell = convertView ;
        if(cell == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = vi.inflate(ID, null);
        }

        final KeepListEntity item = list.get(position);
        TextView shopTitleTextView = (TextView) cell.findViewById(R.id.keepShopTitleTextView);
        shopTitleTextView.setText(item.name);

        final ImageView listImageView = (ImageView) cell.findViewById(R.id.keepImageView);
        ImageFetcher imgFetcher = new ImageFetcher(listImageView, item.imgUrl);
        imgFetcher.start();

        final Button keepCloseButton = (Button) cell.findViewById(R.id.keepCloseButton);
        keepCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepHandler.unkeep(item.id);
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return cell;
    }

}
