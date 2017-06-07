package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.co.recruit.beautydemo.activity.R;
import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListAdapter extends ArrayAdapter<ShopListEntity> {

    private List<ShopListEntity> list;
    private Context context;
    static private int ID = R.layout.cell_shop_list;

    public ShopListAdapter(@NonNull Context context, @NonNull List<ShopListEntity> objects) {
        super(context, ID, objects);
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

        ShopListEntity item = list.get(position);
        TextView shopTitleTextView = (TextView) cell.findViewById(R.id.shopTitleTextView);
        shopTitleTextView.setText(item.name);
        TextView addressTextView = (TextView) cell.findViewById(R.id.addressTextView);
        addressTextView.setText(item.address);
        TextView accessTextView = (TextView) cell.findViewById(R.id.accessTextView);
        accessTextView.setText(item.access);

        return cell;
    }
}
