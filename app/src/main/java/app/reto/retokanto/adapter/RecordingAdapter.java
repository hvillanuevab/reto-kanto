package app.reto.retokanto.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.reto.retokanto.R;
import app.reto.retokanto.beans.Recording;
import app.reto.retokanto.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.RecordingHolder> {

    private List<Recording> recordingList;
    private Activity activity;

    public RecordingAdapter (Activity activity , List<Recording> recordingList) {
        this.recordingList = recordingList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecordingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardrecording,viewGroup,false);
        return new RecordingHolder(view);
    }

    @Override
    public int getItemCount() {
        return recordingList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull RecordingHolder holder, int i) {
        Recording recording = recordingList.get(i);
        Utils.glideImagen(activity,recording.getProfile().getImg(), holder.cvFoto);
        holder.tvName.setText(recording.getProfile().getName()+ " sang "+ recording.getDescription());
        Utils.glideImagen(activity,recording.getPreviewImg(), holder.ivFoto);
        holder.vVideo.setVideoURI(Uri.parse(recording.getRecordVideo()));
        holder.vVideo.requestFocus();
    }

    public class RecordingHolder extends RecyclerView.ViewHolder{
        CircleImageView cvFoto;
        TextView tvName;
        VideoView vVideo;
        ImageView ivFoto;
        public RecordingHolder(@NonNull View itemView){
            super(itemView);
            cvFoto = itemView.findViewById(R.id.cvFoto);
            tvName = itemView.findViewById(R.id.tvName);
            vVideo = itemView.findViewById(R.id.vVideo);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
