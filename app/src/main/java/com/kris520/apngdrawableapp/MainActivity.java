package com.kris520.apngdrawableapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kris520.apngdrawable.ApngImageUtils;
import com.kris520.apngdrawable.ApngLoader;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView apng1;
    ImageView apng2;
    ImageView apng3;
    ImageView apng4;
    ImageView apng5;
    ImageView apngView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ApngLoader.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apng1 = (ImageView) findViewById(R.id.image1);
        apng2 = (ImageView) findViewById(R.id.image2);
        apng3 = (ImageView) findViewById(R.id.image3);
        apng4 = (ImageView) findViewById(R.id.image4);
        apng5 = (ImageView) findViewById(R.id.image5);
        apngView = (ImageView) findViewById(R.id.apng_view);

        apng1.setTag("blued.png");
        apng2.setTag("BladeRunner.png");
        apng3.setTag("Contact.png");
        apng4.setTag("SteamEngine.png");
        apng5.setTag("world-cup-2014-42.png");

        apng1.setOnClickListener(this);
        apng2.setOnClickListener(this);
        apng3.setOnClickListener(this);
        apng4.setOnClickListener(this);
        apng5.setOnClickListener(this);
        apngView.setOnClickListener(this);

        loadAssetPng(apng1);
        loadAssetPng(apng2);
        loadAssetPng(apng3);
        loadAssetPng(apng4);
        loadAssetPng(apng5);
    }

    private void loadAssetPng(ImageView image) {
        // load image
        try {
            // get input stream
            InputStream ims = getAssets().open((String) image.getTag());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            image.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
    }

    @Override
    public void onClick(View view) {
        String assetName = (String) view.getTag();
        apngView.setTag(assetName);
        ApngLoader.loadImage(ApngImageUtils.Scheme.ASSETS.wrap(assetName), apngView, null);
    }
}
