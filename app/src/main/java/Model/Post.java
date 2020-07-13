package Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id ;
    private int userid;
    private String title ;
    @SerializedName("body")
    private String  text;

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
