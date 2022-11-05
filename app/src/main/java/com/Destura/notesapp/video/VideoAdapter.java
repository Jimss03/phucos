package com.Destura.notesapp.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Destura.notesapp.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context mContext;
    static ArrayList<VideoFiles> videoFiles;
    View view;
    public VideoAdapter(Context mContext, ArrayList<VideoFiles> videoFiles) {
        this.mContext = mContext;
        this.videoFiles = videoFiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fileName.setText(videoFiles.get(position).getTitle());
        holder.videoDuration.setText(convertToMMSS(videoFiles.get(position).getDuration()));
        Glide.with(mContext).load(new File(videoFiles.get(position).getPath()))
                .into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, videoplayer.class);
                intent.putExtra("position",position);
                intent.putExtra("sender","FilesIsSending");
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail,menuMore;
        TextView fileName,videoDuration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            fileName = itemView.findViewById(R.id.video_file_name);
            videoDuration = itemView.findViewById(R.id.video_duration);

        }
    }
    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

}
