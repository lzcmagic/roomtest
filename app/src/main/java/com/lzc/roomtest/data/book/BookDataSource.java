package com.lzc.roomtest.data.book;

import java.util.List;

/**
 * 名称 roomtest
 *
 * @author 描述
 */

public interface BookDataSource {

    interface LoadBookCallback{
        void onBookLoaded(List<Book> books);

        void onDataNotAvailable();
    }

    interface LoadUserBookCallback{
        void onUserBookLoaded(List<BookDao.UserBook> books);

        void onDataNotAvailable();
    }

    interface GetBookCallback{
        void onBookLoaded(Book book);
        void onDataNotAvailable();
    }

    interface InsetBookCallback{
        void onInsertRow(Long value);
    }

    interface DeleteBookCallback{
        void onDeleteRow(int row);
    }
    void loadBooks(LoadBookCallback callback);

    void getBook(String id,GetBookCallback callback);
    void getBookFromUser(LoadUserBookCallback callback);

    void saveBook(Book book,InsetBookCallback callback);

    void removeBook(String id,DeleteBookCallback callback);
}
