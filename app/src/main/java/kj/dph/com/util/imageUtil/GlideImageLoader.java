package kj.dph.com.util.imageUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;

import kj.dph.com.R;
import kj.dph.com.common.Constants;
import kj.dph.com.common.MainApplication;

/**
 * Description: GlideImageLoader
 * Data: 2017/4/1 17:32
 *
 * @author: wxw
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager mRequestManager;

    @Override
    public void init(Context context) {
//        if (mRequestManager==null) {
            if(context==null){
                context= MainApplication.getInstance();
            }
            mRequestManager= Glide.with(context);
//        }
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView, int defaultImage, int errorImage) {
//        imageUrl = handerImageUrl(imageUrl);
        if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        mRequestManager.load(imageUrl).asBitmap().centerCrop().placeholder(defaultImage).error(errorImage).dontAnimate().into(imageView);
    }

    @NonNull
    private String handerImageUrl(String imageUrl) {
        imageUrl+="";
        if(imageUrl.startsWith("http")){
            imageUrl=imageUrl.replace(Constants.DPH_PIC_PATH_,"").replace(Constants.DPH_PIC_PATH,"");
    }
        if(TextUtils.isEmpty(imageUrl)||!imageUrl.startsWith("http")){
            imageUrl= Constants.DPH_PIC_PATH+imageUrl;
        }
        return imageUrl;
    }

    @Override
    public void displayImage(File file, ImageView imageView, int defaultImage, int errorImage) {
        if(file==null){
            displayImage(defaultImage,imageView);
            return;
        }
        if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        mRequestManager.load(file).asBitmap().centerCrop().dontAnimate().placeholder(defaultImage).error(errorImage).into(imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView) {
        if(file==null){
//            mRequestManager.load(defaultImage).into(imageView);
            return;
        }
            if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }

//                    .memoryPolicy(NO_CACHE, NO_STORE)
//                    .config(Bitmap.Config.RGB_565)
        mRequestManager.load(file).asBitmap().centerCrop().dontAnimate().placeholder(R.mipmap.default_pic).error(R.mipmap.default_pic).into(imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView,boolean isNeedDefault) {
        if(file==null){
//            mRequestManager.load(defaultImage).into(imageView);
            return;
        }
            if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        if(isNeedDefault){
            mRequestManager.load(file).asBitmap().centerCrop().dontAnimate().placeholder(R.mipmap.default_pic).error(R.mipmap.default_pic).into(imageView);
        }else{
            mRequestManager.load(file).asBitmap().centerCrop().dontAnimate().into(imageView);
        }

    }


    @Override
    public void displayImage(String imageUrl, ImageView imageView) {
        imageUrl = handerImageUrl(imageUrl);
        if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        mRequestManager.load(imageUrl).asBitmap().centerCrop().dontAnimate().placeholder(R.mipmap.default_pic).error(R.mipmap.default_pic).into(imageView);
    }

    @Override
    public void displayImage(String imageUrl, ImageView imageView,boolean isNeedDefault) {
        imageUrl = handerImageUrl(imageUrl);
        if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        if(isNeedDefault){
            mRequestManager.load(imageUrl).asBitmap().centerCrop().dontAnimate().placeholder(R.mipmap.default_pic).error(R.mipmap.default_pic).into(imageView);
        }else{
            mRequestManager.load(imageUrl).asBitmap().centerCrop().dontAnimate().into(imageView);
        }

    }

    @Override
    public void displayImage(Integer resId, ImageView imageView) {
        if(mRequestManager==null){
            mRequestManager=Glide.with(MainApplication.getInstance());
        }
        mRequestManager.load(resId).asBitmap().centerCrop().dontAnimate().into(imageView);
    }


}