package com.example.librarymangementapp.libraryapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.librarymangementapp.libraryapp.Model.BookUser;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {
    @Bind(R.id.SpinnerUserLogin) Spinner  staticSpinnerAmount;
    @Bind(R.id.editText2)
    EditText editText;
    Realm realm;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getInstance(getContext());


        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this.getContext(), R.array.string_array_users,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinnerAmount.setAdapter(staticAdapter);
        //setUpDemoData();

return view;
    }
//    private void setUpDemoData(){
//
//
//        //BookUser bookuser1=new BookUser(0,"Einstein: his life and universe", "Walter Isaacson", "http://static.bookstck.com/books/einstein-his-life-and-universe-400.jpg");
//        BookUser bookuser2=new BookUser(1,"Zero to One: Notes on Startups, or How to Build the Future", "Peter Thiel, Blake Masters", "http://static.bookstck.com/books/zero-to-one-400.jpg",true);
//        BookUser bookuser3=new BookUser(2,"Tesla: Inventor of the Electrical Age", "W. Bernard Carlson", "http://static.bookstck.com/books/tesla-inventor-of-the-electrical-age-400.jpg",false);
//        BookUser bookuser4=new BookUser(3,"Orwell's Revenge: The \"1984\" Palimpsest", "Peter Huber", "http://static.bookstck.com/books/orwells-revenge-400.jpg",true);
//        BookUser bookuser5=new BookUser(4,"How to Lie with Statistics", "Darrell Huff", "http://static.bookstck.com/books/how-to-lie-with-statistics-400.jpg",false);
////        BookUser bookuser6=new BookUser(5,"Abundance: The Future Is Better Than You Think", "Peter H. Diamandis, Steven Kotler", "http://static.bookstck.com/books/abundance-400.jpg");
////        BookUser bookuser7=new BookUser(6,"Where Good Ideas Come From", "Steven Johnson", "http://static.bookstck.com/books/where-good-ideas-come-from-400.jpg");
////        BookUser bookuser8=new BookUser(7,"The Information: A History, A Theory, A Flood", "James Gleick", "http://static.bookstck.com/books/the-information-history-theory-flood-400.jpg");
////        BookUser bookuser9=new BookUser(8,"Turing's Cathedral: The Origins of the Digital Universe", "George Dyson", "http://static.bookstck.com/books/turing-s-cathedral-400.jpg");
//        realm.beginTransaction();
//
//      //  realm.copyToRealmOrUpdate(bookuser1);
//        realm.copyToRealmOrUpdate(bookuser2);
//        realm.copyToRealmOrUpdate(bookuser3);
//        realm.copyToRealmOrUpdate(bookuser4);
//        realm.copyToRealmOrUpdate(bookuser5);
////        realm.copyToRealmOrUpdate(bookuser6);
////        realm.copyToRealmOrUpdate(bookuser7);
////        realm.copyToRealmOrUpdate(bookuser8);
////        realm.copyToRealmOrUpdate(bookuser9);
//
//        realm.commitTransaction();
//
//    }
@OnClick(R.id.btn_login)
public void login(){
    checkuser();
}
    public void checkuser(){
if (staticSpinnerAmount.getSelectedItemPosition()==0){
    Toast.makeText(getActivity(), "please select usery ", Toast.LENGTH_LONG).show();
}else if (staticSpinnerAmount.getSelectedItemPosition()==1  ){
    Toast.makeText(getActivity(), "Welcome Admin ", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(getActivity(), AdminManageBooks.class);
    startActivity(intent);
}else if(staticSpinnerAmount.getSelectedItemPosition()==2 ){

    Toast.makeText(getActivity(), "Welcome User ", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(getActivity(), UserManageBooks.class);
    startActivity(intent);
}else{
    Toast.makeText(getActivity(), "wrong pass word ", Toast.LENGTH_LONG).show();
}

    }
}
