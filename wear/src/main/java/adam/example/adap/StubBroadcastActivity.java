package adam.example.adap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by karenmandarina on 6/18/18.
 */

public class StubBroadcastActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent();
        i.setAction("com.adaptwo.adap.SHOW_NOTIFICATION");
        i.putExtra(PostNotificationReceiver.CONTENT_KEY, getString(com.karenmandarina.flashchatnewfirebase.R.string.title));
        sendBroadcast(i);
        finish();
    }
}