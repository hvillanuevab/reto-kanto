package app.reto.retokanto.ui.profile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

import app.reto.retokanto.beans.User;

public interface ProfileMVP {

    interface  View{
        void showMensaje(String mensaje);
        void showPhoto(Activity activity, File file, Bitmap bitmap);
        void initMainActivity();
    }

    interface Presenter{
        void setView(ProfileMVP.View view, Activity activity);
        void showOptionsPhoto(Activity activity);
        void getPhotoGallery(Uri filePath);
        void getPhotoCamara();
        void validateForm(String name, String userName, String biography, User user);

    }

    interface Model{
        void save( User user);
    }
}
