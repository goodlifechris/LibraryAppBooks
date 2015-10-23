package com.example.librarymangementapp.libraryapp;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.librarymangementapp.libraryapp.Adapter.BooksAdapterUser;
import com.example.librarymangementapp.libraryapp.Adapter.BooksAdapterUser;
import com.example.librarymangementapp.libraryapp.Model.Book;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserManageBooksFragment extends Fragment {

    RecyclerView recyclerView;
    BooksAdapterUser adapter;
    SwipeToAction swipeToAction;
    RealmResults<Book> bookUsers;
    List<Book> books = new ArrayList<>();


    public UserManageBooksFragment() {
    }
    public Realm realm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getInstance(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_manage_books, container, false);
        Fresco.initialize(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        bookUsers=realm.where(Book.class).findAll();
        for(Book bookUser: bookUsers){
            books.add(bookUser);
        }

        adapter = new BooksAdapterUser(getContext(),bookUsers);
        recyclerView.setAdapter(adapter);

        //imageborrowed=(ImageView) v.findViewById(R.id.imageviewborrowed);


        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(final Book itemData) {
               // final int pos=itemData.getBookId();
//                displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                       // addBook(pos, itemData);
//                    }
//                });
             //   displaySnackbar(itemData.getBookTitle() + " removed", null, null);

//                removeBook(itemData);
                realm.beginTransaction();

                if (itemData.isBorrowed() == false) {
                    itemData.setIsBorrowed(true);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " borrowed", null, null);
                    adapter.notifyDataSetChanged();

                } else {
                    itemData.setIsBorrowed(false);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " Unborrowed", null, null);
                    adapter.notifyDataSetChanged();

                }



                return true;
            }

            @Override
            public boolean swipeRight(Book itemData) {
                realm.beginTransaction();

                if (itemData.isBorrowed() == false) {
                    itemData.setIsBorrowed(true);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " borrowed", null, null);
                    adapter.notifyDataSetChanged();

                } else {
                    itemData.setIsBorrowed(false);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " Unborrowed", null, null);
                    adapter.notifyDataSetChanged();

                }

                        return true;
            }

            @Override
            public void onClick(Book itemData) {
                displaySnackbar(itemData.getBookTitle() + " clicked", null, null);
            }

            @Override
            public void onLongClick(Book itemData) {
                displaySnackbar(itemData.getBookTitle() + " long clicked", null, null);
            }
        });


        //populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeToAction.swipeRight(2);
//            }
//        }, 3000);

        return view;

    }

//    private void populate() {
//        this.books.add(new BookUser("Einstein: his life and universe", "Walter Isaacson", "http://static.bookstck.com/books/einstein-his-life-and-universe-400.jpg"));
//        this.books.add(new BookUser("Zero to One: Notes on Startups, or How to Build the Future", "Peter Thiel, Blake Masters", "http://static.bookstck.com/books/zero-to-one-400.jpg"));
//        this.books.add(new BookUser("Tesla: Inventor of the Electrical Age", "W. Bernard Carlson", "http://static.bookstck.com/books/tesla-inventor-of-the-electrical-age-400.jpg"));
//        this.books.add(new BookUser("Orwell's Revenge: The \"1984\" Palimpsest", "Peter Huber", "http://static.bookstck.com/books/orwells-revenge-400.jpg"));
//        this.books.add(new BookUser("How to Lie with Statistics", "Darrell Huff", "http://static.bookstck.com/books/how-to-lie-with-statistics-400.jpg"));
//        this.books.add(new BookUser("Abundance: The Future Is Better Than You Think", "Peter H. Diamandis, Steven Kotler", "http://static.bookstck.com/books/abundance-400.jpg"));
//        this.books.add(new BookUser("Where Good Ideas Come From", "Steven Johnson", "http://static.bookstck.com/books/where-good-ideas-come-from-400.jpg"));
//        this.books.add(new BookUser("The Information: A History, A Theory, A Flood", "James Gleick", "http://static.bookstck.com/books/the-information-history-theory-flood-400.jpg"));
//        this.books.add(new BookUser("Turing's Cathedral: The Origins of the Digital Universe", "George Dyson", "http://static.bookstck.com/books/turing-s-cathedral-400.jpg"));
//    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {

        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.secondary));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

//    private int removeBook(Book bookUser) {
//
//        int pos = books.indexOf(bookUser);
//        books.remove(pos);
//
//    //if (pos>1){
//        realm.beginTransaction();
//       // bookUsers.remove(pos);
//        bookUser.removeFromRealm();
//        realm.commitTransaction();
//        adapter.notifyItemRemoved(pos);
//        return pos;
//
//    //}else {
//
//    //}
//
//
//    }

    private void addBook(int pos, Book bookUser) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bookUser);
        realm.commitTransaction();
        books.add(pos, bookUser);
        adapter.notifyItemInserted(pos);
    }
}


