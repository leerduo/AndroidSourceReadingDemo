package cn.chenfuduo.androidsourcereadingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int nThreads = Runtime.getRuntime().availableProcessors();
        Log.e(TAG, "onCreate: " + nThreads);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.e(TAG, "onCreate: " + maxMemory );
    }
}
