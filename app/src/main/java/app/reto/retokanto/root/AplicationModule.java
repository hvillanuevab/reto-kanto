package app.reto.retokanto.root;

import android.app.Application;
import android.content.Context;

import com.orm.query.Select;

import javax.inject.Singleton;

import app.reto.retokanto.beans.User;
import dagger.Module;
import dagger.Provides;

@Module
public class AplicationModule {

    private Application application;

    public AplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context providesContext() {
        return application;
    }

    @Provides
    public User provideUser() {
        User user = Select.from(User.class).first();
        if (user == null) {
            user = new User("TestAndroide","@testotesto","Esta es mi biografia",0,0,0,"");
            user.save();
        }
        return user;
    }
}