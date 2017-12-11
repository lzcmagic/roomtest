package com.lzc.roomtest.data.user;


import com.lzc.roomtest.util.AppExecutors;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 名称 MyApplication1
 *
 * @author 描述
 */

public class UserLocalDataSource implements UserDataSource {


    private UserDao userDao;
    private AppExecutors appExecutors;

    private static UserLocalDataSource mInstance;

    private UserLocalDataSource(AppExecutors appExecutors, UserDao userDao) {
        this.userDao = userDao;
        this.appExecutors = appExecutors;
    }

    public static UserLocalDataSource getInstance(AppExecutors appExecutors, UserDao userDao) {
        if (mInstance == null) {
            synchronized (UserLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new UserLocalDataSource(appExecutors, userDao);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getUsers(final LoadUserCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<User> users = userDao.getUsers();
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (users != null && users.size() > 0) {
                            callback.onUsersLoaded(users);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUser(final GetUserCallback callback, String userId) {
        Flowable<User> userById = userDao.getUserById(userId);
        userById.subscribeOn(Schedulers.from(appExecutors.diskIO()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        if (user != null) {
                            callback.onUserLoaded(user);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });

    }

    @Override
    public void saveUser(final User user) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                userDao.insertUsers(user);
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteUser(final String userId) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(userId);
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateCompleteUser() {


    }

    @Override
    public void updateUserById(final String id, final int age) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                userDao.updataUserAge(id,age);
            }
        };
        appExecutors.diskIO().execute(runnable);
    }


}
