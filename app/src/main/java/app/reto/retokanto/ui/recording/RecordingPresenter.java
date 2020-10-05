package app.reto.retokanto.ui.recording;

import android.util.Log;

import java.util.Collection;
import java.util.List;

import app.reto.retokanto.beans.Recording;
import app.reto.retokanto.https.NetworkStateManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecordingPresenter implements RecordingMVP.Presenter{

    NetworkStateManager networkStateManager;

    @Nullable
    private RecordingMVP.View view=null;

    @Nullable
    private RecordingMVP.Model model;

    private Disposable subscripcion;

    @Override
    public void setView(RecordingMVP.View view) {
        this.view = view;
    }

    public RecordingPresenter(NetworkStateManager networkStateManager, RecordingMVP.Model model) {
        this.networkStateManager = networkStateManager;
        this.model = model;
    }

    @Override
    public void getAllRecording() {
        if (view!=null && networkStateManager!=null)
            if (networkStateManager.isConnectedOrConnecting()) {
                view.showDialog();
                subscripcion  =model.getAllRecording()
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableObserver<List<Recording>>() {
                            @Override
                            public void onNext(List<Recording> listRecording) {

                                if (listRecording.size()>0) {
                                    view.getAllRecording(listRecording);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideDialog();
                            }

                            @Override
                            public void onComplete() {
                                view.hideDialog();
                            }
                        });
            } else {
                //view.showMensaje("sin internet");

                Log.e("error", "sin internet");
            }
    }
}
