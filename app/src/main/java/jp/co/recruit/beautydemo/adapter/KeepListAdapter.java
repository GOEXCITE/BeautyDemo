package jp.co.recruit.beautydemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    private Handler handler;

    {
        handler = new Handler();
    }

    public KeepListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ShopKeptEntity> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View cell = convertView ;
        if(cell == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = vi.inflate(ID, null);
        }

        final ShopKeptEntity item = list.get(position);
        TextView shopTitleTextView = (TextView) cell.findViewById(R.id.keepShopTitleTextView);
        shopTitleTextView.setText(item.name);

        final ImageView listImageView = (ImageView) cell.findViewById(R.id.keepImageView);
        Thread loadImageThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(item.imgUrl);
                    final HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();

                    if (con.getResponseCode() == 200 && con.getResponseMessage().equals("OK")) {
                        InputStream inputStream = con.getInputStream();
                        BitmapFactory.Options bmOptions;
                        bmOptions = new BitmapFactory.Options();
                        bmOptions.inSampleSize = 1;
                        final Bitmap result = BitmapFactory.decodeStream(inputStream, null, bmOptions);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listImageView.setImageBitmap(result);
                            }
                        });
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        loadImageThread.start();

        final Button keepCloseButton = (Button) cell.findViewById(R.id.keepCloseButton);
//        keepCloseButton.setTag(0, Integer.valueOf(position));
//        keepCloseButton.setTag(1, list);
        keepCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer) v.getTag(0);
                List<ShopKeptEntity> items = (List<ShopKeptEntity>) v.getTag(1);
                items.remove(position);
            }
        });

        return cell;
    }
}
