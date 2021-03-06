package jp.co.recruit.beautydemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit.beautydemo.activity.BuildConfig;
import jp.co.recruit.beautydemo.model.ShopDetailEntity;
import jp.co.recruit.beautydemo.model.KeepListEntity;

/**
 * Created by 01011776 on 2017/06/07.
 */

public class ShopKeepHelper extends SQLiteOpenHelper {

    public static final String KEEP_TABLE = "SHOP";
    public static final String KEEP_TABLE_KEY_ID = "id";
    public static final String KEEP_TABLE_KEY_IMGURL = "imgUrl";
    public static final String KEEP_TABLE_KEY_NAME = "name";

    static final private String DB_NAME = "shop.sqlite";
    static final private int DB_VERSION = 1;
    static final private String TAG = "ShopKeepHelper";

    public ShopKeepHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE shop (id TEXT PRIMARY KEY, name TEXT, imgUrl TEXT)
        String command = "CREATE TABLE " + KEEP_TABLE
                + " (" + KEEP_TABLE_KEY_ID + " TEXT PRIMARY KEY, " + KEEP_TABLE_KEY_NAME + " TEXT, " + KEEP_TABLE_KEY_IMGURL + " TEXT)";
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {
        db.execSQL("DROP TABLE IF EXISTS shop");
    }

    public boolean keep(ShopDetailEntity shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(ShopKeepHelper.KEEP_TABLE_KEY_ID, shop.id);
            cv.put(ShopKeepHelper.KEEP_TABLE_KEY_NAME, shop.name);
            cv.put(ShopKeepHelper.KEEP_TABLE_KEY_IMGURL, shop.imgUrl);
            db.insert(ShopKeepHelper.KEEP_TABLE, null, cv);
            return true;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, e.getMessage(), e);
            }
        } finally {
            db.close();
        }
        return false;
    }

    public boolean unkeep(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = ShopKeepHelper.KEEP_TABLE_KEY_ID + " = ?";
            String[] params = {id};
            db.delete(ShopKeepHelper.KEEP_TABLE, query, params);
            return true;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, e.getMessage(), e);
            }
        } finally {
            db.close();
        }
        return false;
    }

    public boolean isKept(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = null;
        try {
            String query = ShopKeepHelper.KEEP_TABLE_KEY_ID + " = ?";
            String[] cols = {ShopKeepHelper.KEEP_TABLE_KEY_ID};
            String[] params = {id};
            cs = db.query(ShopKeepHelper.KEEP_TABLE, cols, query, params, null, null,null, null);
            if (cs.moveToFirst()) {
                return true;
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, e.getMessage(), e);
            }
        } finally {
            db.close();
        }
        return false;
    }

    @NonNull
    public List<KeepListEntity> keptShops() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = null;
        List<KeepListEntity> results = new ArrayList<>();
        try {
            // select id, name, imgUrl from shop
            String query = "select "
                    + ShopKeepHelper.KEEP_TABLE_KEY_ID + ", "
                    + ShopKeepHelper.KEEP_TABLE_KEY_NAME + ", "
                    + ShopKeepHelper.KEEP_TABLE_KEY_IMGURL + " from "
                    + ShopKeepHelper.KEEP_TABLE;

            cs = db.rawQuery(query, null);
            if (cs.getCount() == 0) {
                return results;
            }

            int count = cs.getCount();
            for (int i = 0; i < count; i++ ) {
                cs.moveToPosition(i);
                KeepListEntity addKeptShop = new KeepListEntity();
                addKeptShop.id = cs.getString(0);
                addKeptShop.name = cs.getString(1);
                addKeptShop.imgUrl = cs.getString(2);
                results.add(addKeptShop);
            }
            return results;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, e.getMessage(), e);
            }
        } finally {
            db.close();
        }
        return results;
    }
}
