package com.domker.study.androidstudy;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FrescoImageActivity extends AppCompatActivity {
    ViewPager pager = null;
    LayoutInflater layoutInflater = null;
    List<View> pages = new ArrayList<View>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image);
        layoutInflater = getLayoutInflater();
        pager = (ViewPager) findViewById(R.id.view_pager);
        addGifImage("https://pic3.zhimg.com/v2-011bd031a395bf53be027ebdf509002a_b.gif");
        addImage("res:/" + R.drawable.drawableimage);

        Uri sdcardUri= Uri.fromFile(new File("/sdcard/fileimage.jpg"));// For files on device
        addImage(sdcardUri);

        addImage("asset:/assetsimage.jpg");

        addRawImage(R.raw.rawimage);
        addImage("https://p0.ifengimg.com/2019_15/84FE2FA1C3380B7EEC5F6FA2ECC845AE7C5333CB_w1147_h665.png");
        ViewAdapter adapter = new ViewAdapter();
        adapter.setDatas(pages);
        pager.setAdapter(adapter);
    }

    private void addImage(Uri uri) {
        SimpleDraweeView imageView = (SimpleDraweeView) layoutInflater.inflate(R.layout.activity_fresco_item, null);
        imageView.setImageURI(uri);
        imageView
            .getHierarchy()
            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        pages.add(imageView);
    }

    private void addGifImage(String path){
        SimpleDraweeView imageView = (SimpleDraweeView) layoutInflater.inflate(R.layout.activity_fresco_item, null);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse(path))
                .build();
        imageView.setController(draweeController);
        pages.add(imageView);
    }

    private void addImage(String path) {
        addImage(Uri.parse(path));
    }

    private void addRawImage(int resId){
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(resId).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
            .setUri(imageRequest.getSourceUri())
            .setAutoPlayAnimations(true)
            .build();
        SimpleDraweeView imageView = (SimpleDraweeView) layoutInflater.inflate(R.layout.activity_fresco_item, null);
        imageView
            .getHierarchy()
            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        imageView.setController(controller);
        pages.add(imageView);
    }

}
