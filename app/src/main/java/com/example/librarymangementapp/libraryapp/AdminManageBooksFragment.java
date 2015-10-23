package com.example.librarymangementapp.libraryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarymangementapp.libraryapp.Adapter.BooksAdapter;
import com.example.librarymangementapp.libraryapp.Manager.WrappingLinearLayoutManager;
import com.example.librarymangementapp.libraryapp.Model.Book;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.dift.ui.SwipeToAction;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class AdminManageBooksFragment extends Fragment {

    @Bind(R.id.fab)
    FloatingActionButton addAlarm;
    @Bind(R.id.main_content)
    CoordinatorLayout coordinatorLayout;

    Realm realm;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    SwipeToAction swipeToAction;

    String mCurrentPhotoPath;
    static int TAKE_PICTURE = 1;
    Bitmap bitMap;
    ImageView showbookimage;
    String fileUri;

    final AdminManageBooksFragment context=this;
    private BooksAdapter booksAdapter;
    RealmResults<Book> bookRealmResults;
    List<Book> bookArrayList = new ArrayList<>();


    public AdminManageBooksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to initialise
        realm = Realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_manage_books, container, false);
        ButterKnife.bind(this, view);
        Fresco.initialize(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        bookRealmResults = realm.where(Book.class).findAll();
        for (Book bookReamResult : bookRealmResults) {
            bookArrayList.add(bookReamResult);
        }
        booksAdapter = new BooksAdapter(getActivity(), bookRealmResults);
        recyclerView.setAdapter(booksAdapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(final Book itemData) {
               // final int pos = itemData.getBookId();
//                displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                       // addBook(pos, itemData);
//                    }
//                });
                displaySnackbar(itemData.getBookTitle() + " removed", null, null);

                removeBook(itemData);

                return true;
            }

            @Override
            public boolean swipeRight(Book itemData) {
                realm.beginTransaction();
//                removeBook(itemData);
//
//                displaySnackbar(itemData.getBookTitle() + " borrowed", null, null);
//
                if (itemData.isBorrowed() == false) {
                    itemData.setIsBorrowed(true);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " borrowed", null, null);
                    booksAdapter.notifyDataSetChanged();

                } else {
                    itemData.setIsBorrowed(false);
                    realm.commitTransaction();
                    displaySnackbar(itemData.getBookTitle() + " Unborrowed", null, null);
                    booksAdapter.notifyDataSetChanged();

                }


                return true;
            }

            @Override
            public void onClick(Book itemData) {
                displaySnackbar(itemData.getBookTitle() + " clickesd", null, null);
            }

            @Override
            public void onLongClick(Book itemData) {
                displaySnackbar(itemData.getBookTitle() + " long he clicked", null, null);
            }
        });

      //  loadBooks();
        return view;
    }
//    public void loadBooks() {
//
//
//
//    }
    @OnClick(R.id.fab)
    public void fabbuttonclicked(){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context.getContext());

          View promptView = layoutInflater.inflate(R.layout.dialog_add_new_book, null);


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context.getContext());

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText titlebookEditText = (EditText) promptView.findViewById(R.id.book_title_edit_text);
        final EditText authorbookEditText=(EditText) promptView.findViewById(R.id.book_author_edit_text);
        final Button dateboughtbookButton=(Button) promptView.findViewById(R.id.book_datebought_button);
        final EditText conditionbookEditText=(EditText) promptView.findViewById(R.id.book_condition_edit_text);
        showbookimage=(ImageView) promptView.findViewById(R.id.book_camera_image_view);

// Show a datepicker when the dateButton is clicked
        dateboughtbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendar now = Calendar.getInstance();
                final Calendar c = Calendar.getInstance();

                DatePickerDialog dpd = new DatePickerDialog(context.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateboughtbookButton.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                        dpd.show();


                }
            }

            );

        showbookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create intent with ACTION_IMAGE_CAPTURE action
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /**
 Here REQUEST_IMAGE is the unique integer value you can pass it any integer
 **/
                // start camera activity
                startActivityForResult(intent, TAKE_PICTURE);
            }

            }

        );



            alertDialogBuilder
                    .setCancelable(false)
                    .

            setPositiveButton("Add Book",new DialogInterface.OnClickListener() {
                @Override
                public void onClick (DialogInterface dialog,int id){
                    String titlebook = titlebookEditText.getText().toString();
                    String authorbook = authorbookEditText.getText().toString();
                    String dateboughtbook = dateboughtbookButton.getText().toString();
                    String conditionbook = conditionbookEditText.getText().toString();
String urlimagebook="for url just incase";

                    if (titlebook.isEmpty()|| authorbook.isEmpty() || conditionbook.isEmpty()) {
                        Toast.makeText(getActivity(), " Amount is required ! \n Alarm not set", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        int nextID;
                        nextID = (int) (realm.where(Book.class).maximumInt("bookId") + 1);

                        Book book = new Book(nextID, titlebook, authorbook, dateboughtbook, conditionbook, urlimagebook,fileUri,false);

                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(book);
                        realm.commitTransaction();
                        booksAdapter.notifyDataSetChanged();
                        bookRealmResults = realm.where(Book.class).findAll();
                        for (Book bookReamResult : bookRealmResults) {
                            bookArrayList.add(bookReamResult);
                        }
                        booksAdapter = new BooksAdapter(getActivity(), bookRealmResults);
                        recyclerView.setAdapter(booksAdapter);

                        dialog.dismiss();
                       // loadBooks();
                        Toast.makeText(getActivity(), " New BOOK added ! \n ", Toast.LENGTH_LONG).show();
                       // booksAdapter.notifyDataSetChanged();

                        //Toast.makeText(getActivity(), "Alarm is Set", Toast.LENGTH_LONG).show();
                    }
                }
            }

            )
                    .

            setNegativeButton("Cancel",
                                      new DialogInterface.OnClickListener() {
                public void onClick (DialogInterface dialog,int id){
                    dialog.cancel();
                }
            });

        // create an alert dialog
        AlertDialog alertD = alertDialogBuilder.create();

        alertD.show();
       // recyclerView.setAdapter(booksAdapter);



    }
    private void createImageFile(Bitmap bitmap) throws IOException {
        // Create an image file name
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        FileOutputStream stream = new FileOutputStream(image);
        stream.write(bytes.toByteArray());
        stream.close();
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        fileUri = image.getAbsolutePath();
        Picasso.with(getActivity()).load(image).into(showbookimage);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== Activity.RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();
            // get
            bitMap = (Bitmap) extras.get("data");
//            showbookimage.setImageBitmap(bitMap);
            try {
                createImageFile(bitMap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {

        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.secondary));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    private int removeBook(Book bookUser) {

        int pos = bookArrayList.indexOf(bookUser);
        bookArrayList.remove(pos);

        //if (pos>1){
        realm.beginTransaction();
        // bookUsers.remove(pos);
        bookUser.removeFromRealm();
        realm.commitTransaction();
        booksAdapter.notifyItemRemoved(pos);
        return pos;

        //}else {

        //}


    }

    private void addBook(int pos, Book bookUser) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bookUser);
        realm.commitTransaction();
        bookArrayList.add(pos, bookUser);
        booksAdapter.notifyItemInserted(pos);
    }

}
