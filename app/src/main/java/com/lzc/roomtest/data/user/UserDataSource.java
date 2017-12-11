package com.lzc.roomtest.data.user;

import java.util.List;

/**
 * 名称 MyApplication1
 *
 * @author 描述
 */

public interface UserDataSource {

    interface LoadUserCallback{
        void onUsersLoaded(List<User> users);
        void onDataNotAvailable();
    }
    interface GetUserCallback{
        void onUserLoaded(User user);
        void onDataNotAvailable();
    }

    void getUsers(LoadUserCallback callback);

    void getUser(GetUserCallback callback, String userId);


    void saveUser(User user);

    void deleteUser(String userId);

    void updateCompleteUser();

    void updateUserById(String id,int age);


}
