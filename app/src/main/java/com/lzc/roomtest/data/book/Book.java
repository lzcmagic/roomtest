package com.lzc.roomtest.data.book;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.lzc.roomtest.data.user.User;

import java.util.UUID;

/**
 * 名称 roomtest
 *
 * @author 描述
 */

@Entity(tableName = "books", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "user_id", childColumns = "user_id"))
public class Book {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "book_id")
    private String bookId;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "user_id")
    private String userId;

    @Ignore
    public Book(String bookName, String price, String userId) {
        this.bookId = UUID.randomUUID().toString();
        this.bookName = bookName;
        this.price = price;
        this.userId = userId;
    }

    public Book(@NonNull String bookId, String bookName, String price, String userId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(@NonNull String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price='" + price + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
