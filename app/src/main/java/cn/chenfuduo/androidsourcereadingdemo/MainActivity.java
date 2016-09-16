package cn.chenfuduo.androidsourcereadingdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.chenfuduo.androidsourcereadingdemo.principle.DiskCache;
import cn.chenfuduo.androidsourcereadingdemo.principle.DoubleCache;
import cn.chenfuduo.androidsourcereadingdemo.principle.ImageCache;
import cn.chenfuduo.androidsourcereadingdemo.principle.ImageLoader;
import cn.chenfuduo.androidsourcereadingdemo.principle.MemoryCache;

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
        imageLoader.setImageCache(new DiskCache());
        //使用内存缓存
        imageLoader.setImageCache(new MemoryCache());
        //使用双缓存
        imageLoader.setImageCache(new DoubleCache());
        //使用自定义的图片缓存
        imageLoader.setImageCache(new ImageCache() {
            @Override
            public Bitmap get(String url) {
                return null;
            }

            @Override
            public void put(String url, Bitmap bitmap) {

            }
        });
    }
}
