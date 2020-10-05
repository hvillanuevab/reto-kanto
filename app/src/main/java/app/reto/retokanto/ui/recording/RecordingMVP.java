package app.reto.retokanto.ui.recording;

import java.util.List;

import io.reactivex.Observable;

import app.reto.retokanto.beans.Recording;

public interface RecordingMVP {

    interface  View{
        void showDialog();
        void hideDialog();
        void showMensaje(String mensaje);
        void getAllRecording(List<Recording> list);
    }

    interface Presenter{
        void setView(RecordingMVP.View view);
        void getAllRecording();
    }

    interface Model{
        Observable<List<Recording>> getAllRecording();
    }
}
