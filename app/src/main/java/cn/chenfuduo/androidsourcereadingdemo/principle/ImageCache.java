package cn.chenfuduo.androidsourcereadingdemo.principle;

import android.graphics.Bitmap;

/**
 * Created by chenfuduo on 2016/9/16.
 */
public interface ImageCache {
    Bitmap get(String url);
    void put(String url,Bitmap bitmap);
}
