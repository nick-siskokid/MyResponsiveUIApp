package edu.temple.bookcase;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookClickedInterface{

    boolean landscape;
    FragmentManager fm;
    BookListFragment bookListFragment;
    BookDetailFragment bookDetailFragment;
    ViewPagerFragment viewPagerFragment;
    ArrayList<String> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = new ArrayList<String>();
        bookList.add("Cat in the Hat");
        bookList.add("Green Eggs and Ham");
        bookList.add("Fox in Socks");
        bookList.add("Horton Hears a Who");
        bookList.add("The Foot Book");
        bookList.add("The Lorax");
        bookList.add("One Fish Two Fish");

        //landscape tells us if we are in landscape mode or portrait mode
        landscape = findViewById(R.id.container2) == null;

        bookDetailFragment = new BookDetailFragment();

        bookListFragment = bookListFragment.newInstance(bookList);

        fm = getSupportFragmentManager();



        if(!landscape){
            fm.beginTransaction()
                    .add(R.id.container1, bookListFragment)
                    .commit();

            fm.beginTransaction()
                    .replace(R.id.container2, bookDetailFragment)
                    .commit();
        }
        else{
            fm.beginTransaction()
                    .replace(R.id.container1, viewPagerFragment.newInstance(bookList))
                    .commit();
        }

    }

    public void bookClicked(String title){
        if(!landscape){
            bookDetailFragment.displayBook(title);
        }
    }
}
