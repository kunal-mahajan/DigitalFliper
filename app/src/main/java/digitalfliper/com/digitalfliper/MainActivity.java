package digitalfliper.com.digitalfliper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout relativeLayout = findViewById(R.id.digital_fliper);
        new Timer().schedule(new TimerTask() {
            public int i = 5;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.flip(relativeLayout, MainActivity.this, i, i - 1);
                        i++;
                    }
                });
            }
        }, 1000, 1000);
    }
}
