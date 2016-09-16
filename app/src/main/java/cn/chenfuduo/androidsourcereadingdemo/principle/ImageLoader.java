package cn.chenfuduo.androidsourcereadingdemo.principle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenfuduo on 2016/9/15.
 */
public class ImageLoader {
    //内存缓存
    ImageCache mImageCache = new ImageCache();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();
    //是否使用SD卡内存
    boolean isUseDiskCache = false;
    //是否使用双缓存
    boolean isUseDoubleCache = false;
    //线程池，线程数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView imageView){
        //判断使用哪种缓存
        Bitmap bitmap = null;
        if (isUseDoubleCache){
            bitmap = mDoubleCache.get(url);
        }else if (isUseDiskCache){
            bitmap = mDiskCache.get(url);
        }else{
            bitmap = mImageCache.get(url);
        }
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadBitmap(url);
                if (bitmap == null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }
    public Bitmap downloadBitmap(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public void useDiskCache(boolean useDiskCache){
        isUseDiskCache = useDiskCache;
    }
    public void useDoubleCache(boolean useDoubleCache){
        isUseDoubleCache = useDoubleCache;
    }
}
