package app.reto.retokanto.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import app.reto.retokanto.R;

public class Utils {

    public static  void glideImagen(Activity activity, String urlfoto, ImageView imageView) {
        Glide.with(activity).load(urlfoto)
                .asBitmap()
                .thumbnail(0.5f)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.not_found)
                .dontAnimate()
                .placeholder(R.drawable.not_found)
                .into(imageView);
    }

    public static  void glideImagenLocal(Activity activity, String urlfoto, ImageView imageView) {

        File file = new File(urlfoto);
        Uri imageUri = Uri.fromFile(file);

        Glide.with(activity).load(imageUri)
                .asBitmap()
                .thumbnail(0.5f)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.not_found)
                .dontAnimate()
                .placeholder(R.drawable.not_found)
                .into(imageView);
    }

    public static void snackBar(Activity activity, View view, String mensaje){
        Snackbar snackbar= Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG);

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorRed));
        TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(activity, R.color.colorWhite));
        snackbar.show();
    }

    public static android.app.Dialog createDialog(Activity activity) {
        android.app.Dialog dialog = new android.app.Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);//==================== CAMBIOS = FALSE ====================
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progress);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        return dialog;
    }

}
