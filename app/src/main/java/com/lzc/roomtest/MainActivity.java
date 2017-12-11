package com.lzc.roomtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lzc.roomtest.data.AppDatabase;
import com.lzc.roomtest.data.book.Book;
import com.lzc.roomtest.data.book.BookDao;
import com.lzc.roomtest.data.book.BookDataSource;
import com.lzc.roomtest.data.book.BookLocalDataSource;
import com.lzc.roomtest.data.user.User;
import com.lzc.roomtest.data.user.UserDataSource;
import com.lzc.roomtest.data.user.UserLocalDataSource;
import com.lzc.roomtest.util.AppExecutors;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private UserLocalDataSource userLocalDataSource;
    private BookLocalDataSource bookLocalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase database = AppDatabase.getInstance(this);
        AppExecutors appExecutors = new AppExecutors();
        userLocalDataSource = UserLocalDataSource.getInstance(appExecutors, database.userDao());
        bookLocalDataSource = BookLocalDataSource.getInstance(appExecutors, database.bookDao());
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2: {
                userLocalDataSource.saveUser(new User("001", "lzc1", 24));
                userLocalDataSource.saveUser(new User("002", "lzc2", 25));
                userLocalDataSource.saveUser(new User("003", "lzc3", 26));
                break;
            }
            case R.id.button3: {
                userLocalDataSource.deleteUser("001");
                break;
            }
            case R.id.button4: {
                userLocalDataSource.updateUserById("002", (int) (Math.random() * 100));
                break;
            }
            case R.id.button5: {
                v.setClickable(false);
                v.setEnabled(false);
                userLocalDataSource.getUser(new UserDataSource.GetUserCallback() {
                    @Override
                    public void onUserLoaded(User user) {
                        Log.d(TAG, "onUserLoaded: " + user.toString() + "\t1111111");
                    }

                    @Override
                    public void onDataNotAvailable() {
                        Log.d(TAG, "onDataNotAvailable: failed");
                    }
                }, "002");

                break;
            }
            case R.id.button6: {
                userLocalDataSource.getUsers(new UserDataSource.LoadUserCallback() {
                    @Override
                    public void onUsersLoaded(List<User> users) {
                        for (User user :
                                users) {
                            Log.d(TAG, "onUsersLoaded: " + user.toString());
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        Log.d(TAG, "onDataNotAvailable: failed");
                    }
                });
                break;
            }

            case R.id.button7:

                bookLocalDataSource.saveBook(new Book("001", "老人与海", "10","001"),
                        new BookDataSource.InsetBookCallback() {
                            @Override
                            public void onInsertRow(Long value) {
                                Log.d(TAG, "onInsertRow: " + value);
                            }
                        });
                bookLocalDataSource.saveBook(new Book("002", "三国演义", "20","001"),
                        new BookDataSource.InsetBookCallback() {
                            @Override
                            public void onInsertRow(Long value) {
                                Log.d(TAG, "onInsertRow: " + value);
                            }
                        });
                bookLocalDataSource.saveBook(new Book("003", "时间简史", "30","001"),
                        new BookDataSource.InsetBookCallback() {
                            @Override
                            public void onInsertRow(Long value) {
                                Log.d(TAG, "onInsertRow: " + value);
                            }
                        });
                bookLocalDataSource.saveBook(new Book("004", "百年孤独", "40","001"),
                        new BookDataSource.InsetBookCallback() {
                            @Override
                            public void onInsertRow(Long value) {
                                Log.d(TAG, "onInsertRow: " + value);
                            }
                        });
                bookLocalDataSource.saveBook(new Book("005", "丰乳肥臀", "50","002"),
                        new BookDataSource.InsetBookCallback() {
                            @Override
                            public void onInsertRow(Long value) {
                                Log.d(TAG, "onInsertRow: " + value);
                            }
                        });

                break;
            case R.id.button8:
                bookLocalDataSource.removeBook("001", new BookDataSource.DeleteBookCallback() {
                    @Override
                    public void onDeleteRow(int row) {
                        Log.d(TAG, "onDeleteRow: " + row);
                    }
                });
                break;
            case R.id.button9:
                bookLocalDataSource.loadBooks(new BookDataSource.LoadBookCallback() {
                    @Override
                    public void onBookLoaded(List<Book> books) {
                        for (Book book :
                                books) {
                            Log.d(TAG, "onBookLoaded: " + book.toString());
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {

                        Log.d(TAG, "onDataNotAvailable: load");
                    }
                });
                break;
            case R.id.button10:
                bookLocalDataSource.getBook("003", new BookDataSource.GetBookCallback() {
                    @Override
                    public void onBookLoaded(Book book) {
                        Log.d(TAG, "onBookLoaded: " + book.toString());
                    }

                    @Override
                    public void onDataNotAvailable() {

                        Log.d(TAG, "onDataNotAvailable: get");
                    }
                });
                break;
            case R.id.button11:
                bookLocalDataSource.getBookFromUser(new BookDataSource.LoadUserBookCallback() {
                    @Override
                    public void onUserBookLoaded(List<BookDao.UserBook> books) {
                        for (BookDao.UserBook userBook:
                             books) {
                            Log.d(TAG, "onUserBookLoaded: "+userBook.toString());
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {

                        Log.d(TAG, "onDataNotAvailable: fromUser");
                    }
                });


                break;
            default:
                break;
        }
    }
}
