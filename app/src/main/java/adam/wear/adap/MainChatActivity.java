package adam.wear.adap;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    // private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private Button mGoodButton;
    private Button mBadButton;
    private DatabaseReference mDatabaseReference;
    // private FirebaseFirestore mDR;
    // private ChatListAdapter mAdapter;
    private String mSenderEmail;
    ArrayList<String> commands;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupSenderEmail();
        setupDisplayName();
        commands = new ArrayList<String>();
        Log.d("FlashChat", "senderEmail saved");
        // getInstance method creates an FirebaseDatabase object.
        // The getReference method created a DatabaseReference object
        // DatabaseReference is needed becasue it points to particular location in the FirebaseDatabase
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        // mDR = FirebaseFirestore.getInstance();


        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mGoodButton = findViewById(R.id.sendgood);
        mBadButton = findViewById(R.id.sendbad);
       // mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                sendMessage();
                return true;
            }
        });

        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        Log.d("FlashChat", "send button pressed");

    }

    // TODO: Retrieve the display name from the Shared Preferences
    private void setupDisplayName() {

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);
        if (mDisplayName == null) mDisplayName = "Anonymous";
        Log.d("FlashChat", "set up display name" + mDisplayName);

    }

    private void setupSenderEmail() {
        SharedPreferences emailprefs = getSharedPreferences(LoginActivity.EMAIL_PREFS, MODE_PRIVATE);
        mSenderEmail = emailprefs.getString(LoginActivity.SENDER_EMAIL, null);
        if (mSenderEmail == null) mSenderEmail = "N/A";
        Log.d("FlashChat", "set up sender email " + mSenderEmail);

    }


    private void sendMessage() {

        Log.d("FlashChat", "I sent something");
        //Toast notification for message confirmation
        Context context = getApplicationContext();
        CharSequence text = "Custom message sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // TODO: Grab the text the user typed in and push the message to Firebase
        String input = mInputText.getText().toString();
         Date time = Calendar.getInstance().getTime();
        // String time = DateFormat.getDateTimeInstance().format(new Date());
        if (!input.equals("")) {
            InstantMessage chat = new InstantMessage(input, time, mSenderEmail);
            mDatabaseReference.child("messages").push().setValue(chat);
//            mDR.collection("messages")
//                    .add(chat);
            mInputText.setText("");
        }

    }

    private void sendGoodMessage() {

        Log.d("FlashChat", "I sent something positive");
        // TODO: Grab the text the user typed in and push the message to Firebase
        String input = "Positive";
        Date time = Calendar.getInstance().getTime();
        // String time = DateFormat.getDateTimeInstance().format(new Date());
        InstantMessage chat = new InstantMessage(input, time, mSenderEmail);
        mDatabaseReference.child("messages").push().setValue(chat);
//      mDR.collection("messages")
//              .add(chat);
    }
    private void sendBadMessage() {

        Log.d("FlashChat", "I sent something corrective");
        // TODO: Grab the text the user typed in and push the message to Firebase
        String input = "Corrective";
        Date time = Calendar.getInstance().getTime();
        // String time = DateFormat.getDateTimeInstance().format(new Date());
        InstantMessage chat = new InstantMessage(input, time, mSenderEmail);
        mDatabaseReference.child("messages").push().setValue(chat);
//      mDR.collection("messages")
//              .add(chat);
    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAdapter = new ChatListAdapter(this, mDatabaseReference, mDisplayName);
//        mChatListView.setAdapter(mAdapter);
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // TODO: Remove the Firebase event listener on the adapter.
//        mAdapter.cleanup();
//
//    }

    public void goodjobnotify(View view) {
        //Notification code for positive
        //building the notification
        Notification notification = new NotificationCompat.Builder(getApplication())
                //Title
                .setContentTitle("ADAP2")
                //Message
                .setContentText("Good Job!")
                //create default background
                .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                //priority of notification on watch is HIGHEST
                .setPriority(NotificationCompat.PRIORITY_MAX)
                //putting the small icon in notification
                .setSmallIcon(R.drawable.ic_stat_name)
                //putting the main, large icon in the notification
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.smile)) //smiling bitmap image
                //vibration pattern, format is {pause, vibrate, pause, vibrate...} number values in ms
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                //finish building notification
                .build();

        //get default notification style
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        //notification id which is specific to application
        int notificationId = 1;
        //send built notification over
        notificationManager.notify(notificationId, notification);

        //Toast notification for message confirmation
        Context context = getApplicationContext();
        //Toast message content
        CharSequence text = "Positive message sent!";
        //Toast message length
        int duration = Toast.LENGTH_SHORT;
        //build toast message
        Toast toast = Toast.makeText(context, text, duration);
        //show toast message
        toast.show();
        Log.d("FlashChat", "positive message sent");
        sendGoodMessage(); // send to Firebase


        try {
            //open file Data.txt or create one if does not exist
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);
            //for each line of commands, write to Data.txt plus a new line
            for (int i = 0; i < commands.size(); i++) {
                outputFile.write(commands.get(i) + "\n");
            }
            //clean file
            outputFile.flush();
            //close file
            outputFile.close();
        } catch (IOException e) {

        }
    }

    //same comments as goodjobnotify method, except for negative commands
    public void badjobnotify(View view) {
        //Notification code for negative reinforcement
        Notification notification = new NotificationCompat.Builder(getApplication())
                .setContentTitle("ADAP2")
                .setContentText("Don't do that!")
                .extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.frown)) //frowning bitmap image
                .setVibrate(new long[]{100, 300, 100, 300, 100, 300, 100, 300, 100, 300, 100})
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplication());
        int notificationId = 1;
        notificationManager.notify(notificationId, notification);

        //Toast notification for message confirmation
        Context context = getApplicationContext();
        CharSequence text = "Corrective message sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Log.d("FlashChat", "corrective message sent");

        sendBadMessage(); // to Firebase


        try {
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for (int i = 0; i < commands.size(); i++) {
                outputFile.write(commands.get(i) + "\n");
            }
            outputFile.flush();
            outputFile.close();
        } catch (IOException e) {

        }


    }
}