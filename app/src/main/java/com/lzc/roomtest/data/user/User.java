package com.lzc.roomtest.data.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * 名称 MyApplication1
 *
 * @author 描述
 */
@Entity(tableName = "user")
public class User {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "user_name")
    @NonNull
    private String userName;
    @ColumnInfo(name = "age")
    private int age;


    @Ignore
    public User(@NonNull String userName, @NonNull int age) {
        this.userId= UUID.randomUUID().toString();
        this.userName = userName;
        this.age = age;
    }

    public User(@NonNull String userId, @NonNull String userName, @NonNull int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
