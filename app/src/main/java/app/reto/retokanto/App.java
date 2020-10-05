package app.reto.retokanto;

import android.app.Application;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import app.reto.retokanto.https.ApiModule;
import app.reto.retokanto.https.SystemServicesModule;
import app.reto.retokanto.root.AplicationModule;
import app.reto.retokanto.root.ApplicationComponent;
import app.reto.retokanto.root.DaggerApplicationComponent;
import app.reto.retokanto.utils.ConnectivityReceiver;

public class App extends Application {
    private static  App app;
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        SugarContext.init(getApplicationContext());
        component= DaggerApplicationComponent
                .builder()
                .aplicationModule(new AplicationModule(this))
                .apiModule(new ApiModule())
                .systemServicesModule(new SystemServicesModule())
                .build();
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());

    }

    public static App getApp(){return  app;}

    public  ApplicationComponent getComponent(){
        return  component;
    }

    public static synchronized App getInstance() {
        return app;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
