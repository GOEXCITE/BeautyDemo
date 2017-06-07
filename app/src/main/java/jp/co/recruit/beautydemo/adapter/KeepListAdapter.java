package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import jp.co.recruit.beautydemo.activity.R;
import jp.co.recruit.beautydemo.model.ShopKeptEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListAdapter extends ArrayAdapter<ShopKeptEntity> {

    private List<ShopKeptEntity> list;
    private Context context;
    static private int ID = R.layout.cell_keep_list;

    public KeepListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ShopKeptEntity> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cell = convertView ;
        if(cell == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = vi.inflate(ID, null);
        }

        ShopKeptEntity item = list.get(position);
        TextView shopTitleTextView = (TextView) cell.findViewById(R.id.keepShopTitleTextView);
        shopTitleTextView.setText(item.name);

        Button closeButton = (Button) cell.findViewById(R.id.keepCloseButton);

        return cell;
    }
}
