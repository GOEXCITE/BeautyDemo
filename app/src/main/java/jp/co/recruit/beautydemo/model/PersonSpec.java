package jp.co.recruit.beautydemo.model;

import com.yahoo.squidb.annotations.ColumnSpec;
import com.yahoo.squidb.annotations.TableModelSpec;

/**
 * Created by 01011776 on 2017/09/25.
 */

@TableModelSpec(className = "Person", tableName = "People")
public class PersonSpec {

    String firstName;

    String lastName;

    @ColumnSpec(name = "creationDate")
    long birthday;

    public PersonSpec setFirstName(String firstName) {
        this.firstName = firstName;
        return null;
    }
}
