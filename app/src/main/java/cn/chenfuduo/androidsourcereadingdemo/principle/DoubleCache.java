package cn.chenfuduo.androidsourcereadingdemo.principle;

import android.graphics.Bitmap;

/**
 * Created by chenfuduo on 2016/9/16.
 */
public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();
    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
    //将图片缓存到内存和SD卡中
    public void put(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}
