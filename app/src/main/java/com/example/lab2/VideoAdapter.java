package com.example.lab2;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter to handle video streaming so it can show into the screen
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    //Tracking video with its title and description together
    private List<VideoItem> videoItem;
    public VideoAdapter(List<VideoItem> videoItem) {
        this.videoItem = videoItem;
    }

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent, false)
        );
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItem.get(position));
    }

    @Override
    public int getItemCount() {
        return videoItem.size();
    }

    /**
     * Class that prepares the next video for swiping
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textVideoTitle, textVideoId, textVideoDesc;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * Method to get each video item with its own title and description
         * along with initializing the progress bar
         * @param itemView
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle = itemView.findViewById(R.id.textVideoTitle);
            textVideoId = itemView.findViewById(R.id.videoId);
            textVideoDesc = itemView.findViewById(R.id.textVideoDescription);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        void setVideoData(VideoItem videoItem) {
            textVideoTitle.setText(videoItem.videoTitle);
            textVideoId.setText(videoItem.videoId);
            textVideoDesc.setText(videoItem.videoDesc);
            videoView.setVideoPath(videoItem.videoURL);
            //Prepare the video before the user swiped to that video, prevents lots of loading
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * Method that prepares the video for playing.
                 * Gets rid of progress bar once video has loaded
                 * and scales it for the phone screen
                 * @param mp
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //Remove progress bar after video starts
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth();
                    float screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >=1f){
                        videoView.setScaleX(scale);
                    } else {
                        videoView.setScaleY(1f / scale);
                    }
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * Starts the video when everything has been prepared
                 * @param mp
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });

        }
    }
}
