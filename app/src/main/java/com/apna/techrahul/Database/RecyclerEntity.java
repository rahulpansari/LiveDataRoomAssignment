package com.apna.techrahul.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recycler_entity")
public class RecyclerEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userid;

    private String title;

    private String body;

    private long updatedDate;


    public RecyclerEntity(int id,int userid, String title, String body, long updatedDate) {
        this.id=id;
        this.userid = userid;
        this.title = title;
        this.body = body;
        this.updatedDate = updatedDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }



}
