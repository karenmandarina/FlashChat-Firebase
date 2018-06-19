package adam.example.adap;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.karenmandarina.flashchatnewfirebase.R.layout.activity_main);
        mTextView = (TextView) findViewById(com.karenmandarina.flashchatnewfirebase.R.id.text);
        // Enables Always-on
        // setAmbientEnabled();
    }
}
