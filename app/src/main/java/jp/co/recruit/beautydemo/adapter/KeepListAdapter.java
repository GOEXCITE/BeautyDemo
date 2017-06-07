package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import jp.co.recruit.beautydemo.model.ShopKeptEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class KeepListAdapter extends ArrayAdapter<ShopKeptEntity> {

    private List<ShopKeptEntity> list;

    public KeepListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ShopKeptEntity> objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
