package Model;

/*
* to make this an entity
* we have to declare it as an Entity
* annotation
* */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    /*
    * it will have four fields
    * such as id , title , text ,priority
    * */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title ;
    private String text ;
    @ColumnInfo(name = "priority_col")
    private double priority;

    public Note(String title, String text, double priority) {
        this.title = title;
        this.text = text;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public double getPriority() {
        return priority;
    }
}
