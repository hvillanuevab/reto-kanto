package app.reto.retokanto.ui.profile;

import app.reto.retokanto.beans.User;
import app.reto.retokanto.https.KantoApi;

public class ProfileModel implements ProfileMVP.Model {

    private KantoApi kantoApi;

    public ProfileModel(KantoApi kantoApi) {
        this.kantoApi = kantoApi;
    }

    @Override
    public void save(User user) {
        user.save();
    }
}
