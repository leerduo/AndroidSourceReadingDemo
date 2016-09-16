package cn.chenfuduo.androidsourcereadingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.chenfuduo.androidsourcereadingdemo.principle.ImageLoader;

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

        ImageLoader imageLoader = new ImageLoader();
        //使用SD卡缓存
        imageLoader.useDiskCache(true);
        //使用内存缓存
        //imageLoader.useDiskCache(false);
        //使用双缓存
        imageLoader.useDoubleCache(true);
    }
}
