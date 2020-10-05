package app.reto.retokanto.ui.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import javax.inject.Inject;

import app.reto.retokanto.App;
import app.reto.retokanto.MainActivity;
import app.reto.retokanto.R;
import app.reto.retokanto.beans.User;
import app.reto.retokanto.ui.recording.DaggerRecordingComponent;
import app.reto.retokanto.utils.TouchImageViewHelper;
import app.reto.retokanto.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  implements ProfileMVP.View{

    @Inject
    User user;

    @Inject
    ProfileMVP.Presenter presenter;

    private static final int CAMERA_REQUEST = 1888;
    private static final int PICK_IMAGE_REQUEST = 1;

    @BindView(R.id.cvFoto) CircleImageView cvFoto;
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etUserName) EditText etUserName;
    @BindView(R.id.etBiography) EditText etBiography;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaggerProfileComponent.builder()
                .applicationComponent(App.getApp().getComponent())
                .profileModule(new ProfileModule())
                .build().inject(this);

        ButterKnife.bind(this);
        init();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this, this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void  init() {
        etName.setText(user.getName());
        etUserName.setText(user.getUserName());
        etBiography.setText(user.getBiography());

        Utils.glideImagenLocal(this,user.getProfilePicture(), cvFoto);
    }

    @OnClick({R.id.btnProfile})
    public void onClick(Button view) {
        switch (view.getId()) {
            case R.id.btnProfile:
                presenter.showOptionsPhoto(this);
                break;
        }
    }

    @OnClick({R.id.ivCancel, R.id.ivSave})
    public void onClick(ImageView view) {
        switch (view.getId()) {
            case R.id.ivCancel:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ivSave:
                presenter.validateForm(
                        etName.getText().toString().trim(),
                        etUserName.getText().toString().trim(),
                        etBiography.getText().toString().trim(),
                        user
                );
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                this.presenter.getPhotoGallery(data.getData());
            case CAMERA_REQUEST:
                this.presenter.getPhotoCamara();
        }

    }

    @Override
    public void showMensaje(String mensaje) {
        Utils.snackBar(this,findViewById(android.R.id.content),mensaje);
    }

    @Override
    public void showPhoto(final Activity activity, final File file, Bitmap bitmap) {
        final Dialog dialogPhoto;
        dialogPhoto = new Dialog(this);
        dialogPhoto.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPhoto.setContentView(R.layout.dialog_photo);

        TouchImageViewHelper ivFoto= dialogPhoto.findViewById(R.id.ivFoto);
        Button btnSubir= dialogPhoto.findViewById(R.id.btnSubir);
        TextView tvTitulo = dialogPhoto.findViewById(R.id.tvTitulo);
        ivFoto.setImageBitmap(bitmap);
        tvTitulo.setText("PHOTO PROFILE");
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setProfilePicture(file.getAbsolutePath());
                Utils.glideImagenLocal(activity,user.getProfilePicture(), cvFoto);
                dialogPhoto.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialogPhoto.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialogPhoto.show();
    }

    @Override
    public void initMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
