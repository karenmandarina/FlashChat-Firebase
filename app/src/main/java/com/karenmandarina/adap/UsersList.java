package com.karenmandarina.adap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by karenmandarina on 6/19/18.
 */

public class UsersList extends AppCompatActivity {

    private static ListView list_view;
    private static String[] colors = new String[] {"Student A", "Student B", "Student C", "Student D", "Student E"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Log.d("ListView", "onto main activity");
        listView();

    }
    public void listView(){
        list_view = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1, colors);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String value = (String ) list_view.getItemAtPosition(position);
                        Toast.makeText(UsersList.this, "Message " + value, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UsersList.this, MainChatActivity.class);
                        finish();
                        startActivity(intent);
                        Log.d("FlashChat", "moved on to main activity");
                    }
                }
        );
    }
}



//
//    private String mDisplayName;
//    private ListView mChatListView;
//    private DatabaseReference mDatabaseReference;
//    private ChatListAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users);
//        Log.d("FlashChat", "Users list page opened");
//    }
//
//    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
//    @Override
//    public void onStart() {
//        super.onStart();
//        mDisplayName = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        mAdapter = new ChatListAdapter(this, mDatabaseReference, mDisplayName);
//        mChatListView.setAdapter(mAdapter);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        // TODO: Remove the Firebase event listener on the adapter.
//        mAdapter.cleanup();
//
//    }
//}