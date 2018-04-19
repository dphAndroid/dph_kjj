package kj.dph.com.util.imageUtil;

import android.content.Context;
import android.widget.ImageView;


import java.io.File;

import kj.dph.com.common.MainApplication;

/**
 * 图片加载代理类，所有的图片操作都通过该代理类去实现；
 * 如果要改变图片加载框架，只需要在该类里替换相应的图片加载框架即可，客户端所有引用的图片操作都不需要修改
 *
 * 注：1.ImageLoaderProxy与传入的context生命周期一致，例如传入activity，则使用activity的生命周期，activity销毁，ImageLoaderProxy也将销毁；
 * 2.若不传，默认和Application生命周期一致。
 */
public class ImageLoaderProxy implements ImageLoader {
    private ImageLoader imageLoader;//代理对象
    private static ImageLoaderProxy imageLoaderProxy;

    public static ImageLoaderProxy getInstance() {
        if (imageLoaderProxy == null) {
            imageLoaderProxy = new ImageLoaderProxy();
        }
        return imageLoaderProxy;
    }

    private ImageLoaderProxy() {
        imageLoader = new GlideImageLoader();
    }

    @Override
    public void init(Context context) {
        imageLoader.init(context);
    }

    /**
     * 展示图片-默认生命周期与Application相同
     *
     * @param imageUrl 图片地址
     * @param imageView 展示图片的控件
     * @param defaultImage 图片未加载时给用户展示的默认图片
     * @param errorImage 图片加载失败时给用户展示的图片
     */
    @Override
    public void displayImage(String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
        displayImage(MainApplication.getInstance(),imageUrl, imageView, defaultImage, errorImage);
    }

    @Override
    public void displayImage(File file, ImageView imageView, int defaultImage, int errorImage) {
        displayImage(MainApplication.getInstance(),file, imageView, defaultImage, errorImage);
    }

    @Override
    public void displayImage(File file, ImageView imageView) {
       displayImage(MainApplication.getInstance(),file, imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView, boolean isNeedDefault) {
        displayImage(MainApplication.getInstance(),file,imageView,isNeedDefault);
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView) {
        displayImage(MainApplication.getInstance(),imageUrl, imageView);
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView, boolean isNeedDefault) {
        displayImage(MainApplication.getInstance(),imageUrl, imageView,isNeedDefault);
    }

    @Override
    public void displayImage(Integer resId, ImageView imageView) {
        displayImage(MainApplication.getInstance(),resId, imageView);
    }

    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param imageUrl 图片地址
     * @param imageView 展示图片的控件
     * @param defaultImage 图片未加载时给用户展示的默认图片
     * @param errorImage 图片加载失败时给用户展示的图片
     */
    public void displayImage(Context context, String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
        init(context);
        imageLoader.displayImage(imageUrl, imageView, defaultImage, errorImage);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param file 图片地址
     * @param imageView 展示图片的控件
     * @param defaultImage 图片未加载时给用户展示的默认图片
     * @param errorImage 图片加载失败时给用户展示的图片
     */
    public void displayImage(Context context, File file, ImageView imageView, int defaultImage, int errorImage) {
        init(context);
        imageLoader.displayImage(file, imageView, defaultImage, errorImage);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param file 图片地址
     */
    public void displayImage(Context context, File file, ImageView imageView) {
        init(context);
        imageLoader.displayImage(file, imageView);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param file 图片地址
     */
    public void displayImage(Context context, File file, ImageView imageView,boolean isNeedDefault) {
        init(context);
        imageLoader.displayImage(file, imageView,isNeedDefault);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param imageUrl 图片地址
     */
    public void displayImage(Context context, String imageUrl, ImageView imageView) {
        init(context);
        imageLoader.displayImage(imageUrl, imageView);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param imageUrl 图片地址
     */
    public void displayImage(Context context, String imageUrl, ImageView imageView,boolean isNeedDefault) {
        init(context);
        imageLoader.displayImage(imageUrl, imageView,isNeedDefault);
    }
    /**
     * 展示图片-默认生命周期与传入的context相同（推荐使用此种方式）
     * @param context 上下文，代理类生命周期与此相同
     * @param resId 图片地址
     */
    public void displayImage(Context context,Integer resId, ImageView imageView) {
        init(context);
        imageLoader.displayImage(resId, imageView);
    }
}