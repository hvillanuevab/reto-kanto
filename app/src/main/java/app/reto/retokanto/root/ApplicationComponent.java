package app.reto.retokanto.root;

import android.content.Context;

import app.reto.retokanto.MainActivity;
import app.reto.retokanto.beans.User;
import app.reto.retokanto.https.ApiModule;
import app.reto.retokanto.https.KantoApi;
import app.reto.retokanto.https.NetworkStateManager;
import app.reto.retokanto.https.SystemServicesModule;
import app.reto.retokanto.ui.profile.ProfileActivity;
import dagger.Component;

@Component(modules = {AplicationModule.class, ApiModule.class, SystemServicesModule.class})
public interface ApplicationComponent {

    Context providesContext();
    KantoApi kantoApi();
    User provideUser();
    NetworkStateManager provideNetworkStateManager();

    void inject(MainActivity mainActivity);
}
