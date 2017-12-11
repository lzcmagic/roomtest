package com.lzc.roomtest.data.book;

import com.lzc.roomtest.util.AppExecutors;

import java.util.List;

/**
 * 名称 roomtest
 *
 * @author 描述
 */

public class BookLocalDataSource implements BookDataSource {


    private static BookLocalDataSource mInstance;
    private AppExecutors appExecutors;
    private BookDao bookDao;

    private BookLocalDataSource(AppExecutors appExecutors, BookDao userDao) {
        this.appExecutors = appExecutors;
        this.bookDao = userDao;
    }

    public static BookLocalDataSource getInstance(AppExecutors appExecutors, BookDao bookDao) {

        if (mInstance == null) {
            synchronized (BookLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BookLocalDataSource(appExecutors, bookDao);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void loadBooks(final LoadBookCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Book> books = bookDao.queryBooks();
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (books != null) {
                            callback.onBookLoaded(books);
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
    public void getBook(final String id, final GetBookCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Book book = bookDao.queryBookById(id);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (book != null) {
                            callback.onBookLoaded(book);
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
    public void getBookFromUser( final LoadUserBookCallback callback) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                List<BookDao.UserBook> userBooks = bookDao.queryBookFromUser();
                if (userBooks!=null){
                    callback.onUserBookLoaded(userBooks);
                }else{
                    callback.onDataNotAvailable();
                }
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveBook(final Book book, final InsetBookCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long b = bookDao.insertBook(book);
                    callback.onInsertRow(b);
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void removeBook(final String id, final DeleteBookCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int b = bookDao.deleteBookById(id);
                callback.onDeleteRow(b);
            }
        };
        appExecutors.diskIO().execute(runnable);
    }
}
