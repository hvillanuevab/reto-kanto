package app.reto.retokanto.ui.recording;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import app.reto.retokanto.App;
import app.reto.retokanto.adapter.RecordingAdapter;
import app.reto.retokanto.beans.Recording;
import app.reto.retokanto.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import  app.reto.retokanto.R;

public class RecordingFragment extends Fragment implements RecordingMVP.View {

     @Inject
     RecordingMVP.Presenter presenter;

     Dialog dialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @BindView(R.id.rvList) RecyclerView rvRecording;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recording, container, false);

        DaggerRecordingComponent.builder()
                .applicationComponent(App.getApp().getComponent())
                .recordingModule(new RecordingModule())
                .build().inject(this);

        ButterKnife.bind(this, view);
        presenter.setView(this);
        init();
        presenter.getAllRecording(); ;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    private void init(){
        rvRecording.setHasFixedSize(true);
        rvRecording.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRecording.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void showDialog() {
        dialog= Utils.createDialog(getActivity());
        dialog.show();
    }

    @Override
    public void hideDialog() {
        if (dialog.isShowing()){
            dialog.hide();
        }
    }

    @Override
    public void showMensaje(String mensaje) {
        Utils.snackBar(getActivity(),getActivity().findViewById(android.R.id.content),getActivity().getString(R.string.internet));
    }

    @Override
    public void getAllRecording(List<Recording> list) {
        rvRecording.setAdapter(new RecordingAdapter(getActivity(),list));
    }
}
