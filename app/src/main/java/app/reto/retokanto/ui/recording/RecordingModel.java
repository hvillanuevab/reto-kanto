package app.reto.retokanto.ui.recording;

import java.util.List;

import app.reto.retokanto.beans.Recording;
import app.reto.retokanto.https.KantoApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecordingModel implements RecordingMVP.Model {

    private KantoApi kantoApi;

    public RecordingModel(KantoApi kantoApi) {
        this.kantoApi = kantoApi;
    }

    @Override
    public Observable<List<Recording>> getAllRecording() {
        return kantoApi.getAllRecording()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
