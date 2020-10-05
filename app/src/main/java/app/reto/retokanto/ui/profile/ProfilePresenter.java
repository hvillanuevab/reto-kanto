package app.reto.retokanto.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

import app.reto.retokanto.beans.User;
import app.reto.retokanto.utils.Camara;
import io.reactivex.annotations.Nullable;

public class ProfilePresenter implements ProfileMVP.Presenter {
    private static final String CARPETA_PRINCIPAL = "kanto/";//directorio principal
    private static final String CARPETA_IMAGEN = "image";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private static final int CAMERA_REQUEST = 1888;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri filePath; //almacena la ruta de la imagen que se obtiene de la galeria
    private Bitmap bitmap;  // almacena la foto para mostrar en el dialog ya sea por camara o galeria
    private File fileImagen;// guarda la ruta de la imagen que obntiene cuando se toma foto
    private String path;//almacena la ruta de la imagen que se obtiene de la camara.

    @Nullable
    private ProfileMVP.View view=null;

    @Nullable
    private ProfileMVP.Model model;

    Activity activity;


    @Override
    public void setView(ProfileMVP.View view, Activity activity) {
        this.view = view;
        this.activity=activity;
    }

    public ProfilePresenter(ProfileMVP.Model model) {
        this.model = model;
    }

    @Override
    public void showOptionsPhoto(Activity activity) {
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    showCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        showGalery();
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    @Override
    public void getPhotoGallery(Uri path) {
        filePath = path;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), filePath);
            if(bitmap!=null) {
                File file = Camara.saveTempBitmap(bitmap);
                String aa= file.getAbsolutePath();
                view.showPhoto(activity,file,bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPhotoCamara() {
        bitmap= BitmapFactory.decodeFile(path);
        if (bitmap!=null){
            File file = Camara.saveTempBitmap(bitmap);
            String aa= file.getAbsolutePath();
            view.showPhoto(activity,file,bitmap);
        }
    }

    @Override
    public void validateForm(String name, String userName, String biography, User user) {
        if (view != null) {
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(biography)) {
                view.showMensaje("Fill in all the fields");
            }else {
                user.setName(name);
                user.setUserName(userName);
                user.setBiography(biography);
                model.save(user);
                view.initMainActivity();
            }
        }
    }

    public void showCamara() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED&& activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_APN_SETTINGS,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAMERA_REQUEST);
            }
            else {
                activate();
            }
        } else {
            activate();
        }
    }

    public void showGalery() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/");
        activity.startActivityForResult(pickIntent, PICK_IMAGE_REQUEST);
    }

    private void activate(){
        try {
            activateCamara();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void activateCamara() throws IOException {
        File miFile = new File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();

        if (isCreada == false) {
            isCreada = miFile.mkdirs();
        }

        if (isCreada == true) {
            Long consecutivo = System.currentTimeMillis() / 1000;
            String nombre = consecutivo.toString() + ".jpg";
            path = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO_IMAGEN
                    + File.separator + nombre;
            fileImagen = new File(path);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String authorities = activity.getPackageName() + ".provider";
                Uri imageUri = FileProvider.getUriForFile(activity, authorities, fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            activity.startActivityForResult(intent, CAMERA_REQUEST);
        }
    }
}
