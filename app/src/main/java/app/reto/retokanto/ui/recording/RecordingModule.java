package app.reto.retokanto.ui.recording;

import app.reto.retokanto.https.KantoApi;
import app.reto.retokanto.https.NetworkStateManager;
import dagger.Module;
import dagger.Provides;

@Module
public class RecordingModule {

    @Provides
    public RecordingMVP.Presenter provideLlamadaPresenter(NetworkStateManager networkStateManager, RecordingMVP.Model model){
        return new RecordingPresenter(networkStateManager,model);
    }

    @Provides
    public RecordingMVP.Model provideLlamadaModel(KantoApi kantoApi){
        return  new RecordingModel(kantoApi);
    }
}
