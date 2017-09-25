package jp.co.recruit.beautydemo.db;

import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

import jp.co.recruit.beautydemo.model.PersonSpec;

/**
 * Created by 01011776 on 2017/09/25.
 */

public class DemoSquidDB extends SquidDatabase {
    @Override
    public String getName() {
        return "FirstDb.db";
    }

    @Override
    protected int getVersion() {
        return 0;
    }

    @Override
    protected Table[] getTables() {
        return new Table[0];
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return false;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return null;
    }

    public void persist(PersonSpec newPerson) {
    }
}
