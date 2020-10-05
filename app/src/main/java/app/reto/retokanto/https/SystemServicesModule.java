package app.reto.retokanto.https;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SystemServicesModule {


    @Provides
    SharedPreferences providePreferenceManager(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    NetworkStateManager provideNetworkStateManager(ConnectivityManager connectivityManagerCompat) {
        return new NetworkStateManager(connectivityManagerCompat);
    }
}
