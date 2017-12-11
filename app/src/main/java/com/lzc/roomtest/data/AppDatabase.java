package com.lzc.roomtest.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lzc.roomtest.data.book.Book;
import com.lzc.roomtest.data.book.BookDao;
import com.lzc.roomtest.data.user.User;
import com.lzc.roomtest.data.user.UserDao;

/**
 * 名称 MyApplication1
 *
 * @author 描述
 */

@Database(entities = {User.class, Book.class},version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract BookDao bookDao();

    private static AppDatabase mInstance;

    //数据迁移 版本升级
    static final Migration MIGRATION_1_3=new Migration(1,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE books (book_id TEXT NOT NULL,book_name TEXT, price TEXT,  PRIMARY KEY(book_id))");
        }
    };

    static final Migration MIGRATION_3_4=new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE books "
                    + " ADD constraint user_id TEXT FOREIGN KEY(user_id) references user(user_id)");
        }
    };

    public static AppDatabase getInstance(Context context){
        if (mInstance==null){
            synchronized (AppDatabase.class){
                if (mInstance==null){
                    return Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"Room.db")
//                            .addMigrations(MIGRATION_1_3)
                            .build();
                }
            }
        }
        return mInstance;
    }

}
