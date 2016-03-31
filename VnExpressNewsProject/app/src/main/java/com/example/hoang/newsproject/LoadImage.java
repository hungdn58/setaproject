package com.example.hoang.newsproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.Random;

/**
 * Created by hoang on 3/12/2016.
 */
public class LoadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public LoadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public int randomColor(int rd){
        int color[] = {Color.argb(255 , 255, 28, 255 - rd*2), Color.argb(255 , 106 + rd, 90 , 255 ),
                Color.argb(255 , 253 - rd*5 , 255 , 111), Color.argb(255 , 245 - rd, 255 - rd, 255 - rd),
                Color.argb(255 , 255 , 90 + rd*2 , 84 )};
        Random random = new Random();
        int ran = random.nextInt(color.length);
        return color[ran];
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            bmImage.setImageBitmap(result);
        }else {
           bmImage.setImageResource(R.drawable.noimage);
        }
    }
}
