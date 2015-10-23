package com.example.librarymangementapp.libraryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.librarymangementapp.libraryapp.Model.Book;
import com.example.librarymangementapp.libraryapp.Model.BookUser;
import com.example.librarymangementapp.libraryapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import co.dift.ui.SwipeToAction;
import io.realm.RealmResults;

import static android.view.Gravity.TOP;


/**
 * Created by 001557 on 10/1/2015.
 */
public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Context context;
   // RealmResults<Book> books;
    List<Book> bookUserListItems;



    public static class BookViewHolder extends SwipeToAction.ViewHolder<Book> {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView bookTitleTextView;
        public TextView bookAuthorTextView;
        public TextView bookDateBoughtTextView;
        public TextView bookconditionTextView;
        public ImageView mImageViewBorrowed;
        public TextView bookBorrowedTextView;


        public BookViewHolder(View v) {
            super(v);
            mImageViewBorrowed = (ImageView) v.findViewById(R.id.imageviewborrowed);
            mImageView = (ImageView) v.findViewById(R.id.bookimage);
            bookTitleTextView = (TextView) v.findViewById(R.id.booktitle);
            bookAuthorTextView = (TextView) v.findViewById(R.id.bookauthor);
            bookDateBoughtTextView = (TextView) v.findViewById(R.id.bookdatebought);
            bookconditionTextView=(TextView)v.findViewById(R.id.bookcondition);
            bookBorrowedTextView=(TextView)v.findViewById(R.id.bookborrowedtextview);
        }
    }
    public BooksAdapter(Context context, RealmResults<Book> bookUserListItems){
        this.context = context;
        this.bookUserListItems = bookUserListItems;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_display_books, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        BookViewHolder vh = new BookViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
Book item=bookUserListItems.get(i);
        BookViewHolder viewHolder=(BookViewHolder) holder;
        viewHolder.bookTitleTextView.setText(item.getBookTitle());
        viewHolder.bookAuthorTextView.setText(item.getBookAuthor());
        viewHolder.bookDateBoughtTextView.setText(item.getBookDateBought());
        viewHolder.bookconditionTextView.setText(item.getBookCondition());

//Set Image
        if (item.isBorrowed()==false){
            viewHolder.mImageViewBorrowed.setImageResource(R.drawable.ic_bookmark_outline_blue_900_48dp);
            viewHolder.bookBorrowedTextView.setText("available");


        }else{
            viewHolder.mImageViewBorrowed.setImageResource(R.drawable.ic_book_blue_900_48dp);
            viewHolder.bookBorrowedTextView.setText("not available");

        }

if (item.getBookImageFileName() ==null) {
    Picasso.with(context).load(item.getBookURL()).into(viewHolder.mImageView);
}else{
    Picasso.with(context).load(new File(item.getBookImageFileName())).into(viewHolder.mImageView);

}

//        viewHolder.linearLayoutevent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MyCalendarEventsPressedActivity.class);
//                intent.putExtra(ARG_SafEvent_ID, safEvents.get(i).getEventId());
//                context.startActivity(intent);
//            }
//        });
        viewHolder.data=item;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return bookUserListItems.size();
    }
}
