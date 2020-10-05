package app.reto.retokanto.ui.recording;

import app.reto.retokanto.root.ApplicationComponent;
import dagger.Component;

@Component( modules ={RecordingModule.class}, dependencies = {ApplicationComponent.class})
public interface RecordingComponent  {
    void inject(RecordingFragment recordingFragment);
}
