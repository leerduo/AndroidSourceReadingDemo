package cn.chenfuduo.androidsourcereadingdemo.principle;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by chenfuduo on 2016/9/16.
 */
public class MemoryCache implements ImageCache{
    private LruCache<String,Bitmap> mMemoryCache;

    public MemoryCache() {
        initMemoryCache();
    }

    private void initMemoryCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
    }
    public Bitmap get(String url){
        return mMemoryCache.get(url);
    }
}
