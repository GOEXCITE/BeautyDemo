package jp.co.recruit.beautydemo.model;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.IOException;

/**
 * Created by 01011776 on 2017/09/25.
 */

@JsonObject
public class User {
    @JsonField
    public long id;

    @JsonField
    String name;

    @JsonField
    private String email;

    private String company;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void parseFromString() throws IOException {
        String jsonStr = "{\"id\": 12345,\"name\": \"kazutoyo\",\"email\": \"kazutoyo@email.address\",\"age\": 25,\"company\": \"(๑´ڡ｀๑)ぺろり\"}";
        User User = LoganSquare.parse(jsonStr, User.class);
    }
}
