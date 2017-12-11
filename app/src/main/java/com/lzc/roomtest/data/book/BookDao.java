package com.lzc.roomtest.data.book;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * 名称 roomtest
 *
 * @author 描述
 */

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBook(Book book);

    @Query("select * from books")
    List<Book> queryBooks();

    @Query("select * from books where book_id=:id")
    Book queryBookById(String id);

    @Query("delete from books where book_id=:id")
    int deleteBookById(String id);

    @Query("select user.user_name as userName,books.book_name as bookName,books.price as price" +
            " from books,user where books.book_id=user.user_id")
    List<UserBook> queryBookFromUser();

    static class UserBook{
        String userName;
        String bookName;
        String price;

        @Override
        public String toString() {
            return "UserBook{" +
                    "userName='" + userName + '\'' +
                    ", bookName='" + bookName + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }
}
