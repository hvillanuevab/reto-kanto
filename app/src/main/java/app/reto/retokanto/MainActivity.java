package app.reto.retokanto;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import javax.inject.Inject;

import app.reto.retokanto.beans.User;
import app.reto.retokanto.root.DaggerApplicationComponent;
import app.reto.retokanto.ui.profile.ProfileActivity;
import app.reto.retokanto.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    @Inject
    User user;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.cvFoto) CircleImageView cvFoto;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvUser) TextView tvUser;
    @BindView(R.id.tvBiography) TextView tvBiography;
    @BindView(R.id.tvFollowers) TextView tvFollowers;
    @BindView(R.id.tvFollowed) TextView tvFollowed;
    @BindView(R.id.tvViews) TextView tvViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(user.getName());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorTransparent_v2));
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));

        init();
        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();*/

     /*   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/

    }

    public void  init() {
        tvName.setText(user.getName());
        tvUser.setText(user.getUserName());
        tvBiography.setText(user.getBiography());
        tvFollowers.setText(user.getFollowers().toString());
        tvFollowed.setText(user.getFollowed().toString());
        tvViews.setText(user.getViews().toString());

        Utils.glideImagenLocal(this,user.getProfilePicture(), cvFoto);
    }


    @OnClick({R.id.btnProfile})
    public void onClick(Button view) {
        switch (view.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}
