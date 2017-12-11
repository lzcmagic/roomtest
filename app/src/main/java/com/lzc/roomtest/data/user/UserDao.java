package com.lzc.roomtest.data.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 名称 MyApplication1
 *
 * @author 描述
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Update
    void updateUser(User... users);


    @Query("update user set age=:age where user_id=:userId")
    void updataUserAge(String userId, int age);

    @Query("delete from user where user_id=:userId")
    void deleteUser(String userId);

    @Query("select * from USER")
    List<User> getUsers();

    @Query("select * from user where user_id=:userId")
    Flowable<User> getUserById(String userId);
}
