package taro.rikkeisoft.com.demonetworking;

import java.io.Serializable;

/**
 * Created by VjrutNAT on 11/4/2017.
 */

public class Object implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Object(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
