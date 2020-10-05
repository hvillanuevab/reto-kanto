package app.reto.retokanto.ui.profile;

import app.reto.retokanto.root.ApplicationComponent;
import dagger.Component;

@Component( modules ={ProfileModule.class}, dependencies = {ApplicationComponent.class})
public interface ProfileComponent {
    void inject(ProfileActivity profileActivity);
}
