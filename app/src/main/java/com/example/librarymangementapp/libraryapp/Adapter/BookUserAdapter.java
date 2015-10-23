package com.example.librarymangementapp.libraryapp.Adapter;

/**
 * Created by 001557 on 10/8/2015.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.librarymangementapp.libraryapp.Model.BookUser;
import com.example.librarymangementapp.libraryapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import co.dift.ui.SwipeToAction;
import io.realm.RealmResults;


public class BookUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    //private List<BookUser> items;
    List<BookUser> items;


    /** References to the views for each data item **/
    public class BookViewHolder extends SwipeToAction.ViewHolder<BookUser> {
        public TextView titleView;
        public TextView authorView;
        public SimpleDraweeView imageView;
        public ImageView imageborrowed;

        public BookViewHolder(View v) {
            super(v);
            titleView = (TextView) v.findViewById(R.id.title);
            authorView = (TextView) v.findViewById(R.id.author);
            imageView = (SimpleDraweeView) v.findViewById(R.id.image);
            imageborrowed=(ImageView) v.findViewById(R.id.imageviewborrowed);

        }
    }

    /** Constructor **/
    public BookUserAdapter(Context context,RealmResults<BookUser> items) {
        this.context=context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookUser item = items.get(position);
        BookViewHolder vh = (BookViewHolder) holder;
        vh.titleView.setText(item.getTitle());
        vh.authorView.setText(item.getAuthor());
        if (item.getIsBorrowed()==false){
            vh.imageborrowed.setImageResource(R.drawable.ic_bookmark_outline_blue_900_24dp);

        }else{
            vh.imageborrowed.setImageResource(R.drawable.ic_book_blue_900_24dp);

        }
        //vh.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        Picasso.with(context).load(item.getImageUrl()).fit().centerCrop().into(vh.imageView);

        //Picasso.with(context).load(new File(carParks.get(i).getCarParkImageFileName())).into(viewHolderone.carParkImageView);

        vh.data = item;
    }
}