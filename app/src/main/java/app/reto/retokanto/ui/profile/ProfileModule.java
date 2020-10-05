package app.reto.retokanto.ui.profile;

import app.reto.retokanto.https.KantoApi;
import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    @Provides
    public ProfileMVP.Presenter provideLlamadaPresenter(ProfileMVP.Model model){
        return new ProfilePresenter(model);
    }

    @Provides
    public ProfileMVP.Model provideLlamadaModel(KantoApi kantoApi){
        return  new ProfileModel(kantoApi);
    }
}
