package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.recruit.beautydemo.activity.R;
import jp.co.recruit.beautydemo.model.ShopListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopListAdapter extends ArrayAdapter<ShopListEntity> {

    private List<ShopListEntity> list;
    private Context context;
    static private int ID = R.layout.cell_shop_list;

    @BindView(R.id.shopTitleTextView)
    TextView shopTitleTextView;

    @BindView(R.id.imageView)
    TextView imageView;

    @BindView(R.id.addressTextView)
    TextView addressTextView;

    @BindView(R.id.accessTextView)
    TextView accessTextView;

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

        ButterKnife.bind(cell);

        ShopListEntity item = list.get(position);
        shopTitleTextView = (TextView) cell.findViewById(R.id.shopTitleTextView);
        shopTitleTextView.setText(item.name);
        addressTextView = (TextView) cell.findViewById(R.id.addressTextView);
        addressTextView.setText(item.address);
        accessTextView = (TextView) cell.findViewById(R.id.accessTextView);
        accessTextView.setText(item.access);

        return cell;
    }
}