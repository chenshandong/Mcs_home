package com.txy.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2016/1/19.
 */
@Table(name = "Channel")
public class Channel extends Model{
    @Column(name = "name", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String name;
    @Column
    private String channel;

    public Channel(){}

    public Channel(String name, String channel) {
        this.name = name;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }
}
