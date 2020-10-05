package app.reto.retokanto.https;

import java.util.List;

import app.reto.retokanto.beans.Recording;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KantoApi {

    @GET("5e669952310000d2fc23a20e")
    Observable<List<Recording>> getAllRecording();
}
