package com.ago.movieapp.data.cache.storage;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.ago.movieapp.network.EndPoints;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class ImageStorageRepo {

    private Context context;

    public final static String PATH_IMAGES = "images";


    public ImageStorageRepo(Context context) {
        this.context = context;
    }

    public void saveImage(Bitmap bitmap, String fileName){

        try {
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir(PATH_IMAGES, Context.MODE_PRIVATE);
            File mFile=new File(directory,fileName);

            FileOutputStream out = new FileOutputStream(mFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();


        }
        catch (Exception e){

        }
    }

    public void getImage(final String fileName, final ImageView imageView){

        if (fileName == null || imageView == null)
            return;

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(PATH_IMAGES, Context.MODE_PRIVATE);
        File mFile=new File(directory,fileName);

        if (mFile.exists())
            imageView.setImageURI(Uri.fromFile(mFile));
        else {

            Picasso.get()
                    .load(EndPoints.IMAGE_BASE_URL + fileName)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                            imageView.setImageBitmap(bitmap);

                            saveImage(bitmap,fileName);
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        }

    }
}
