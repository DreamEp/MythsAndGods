package fr.esilv.mythsandgods.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

import fr.esilv.mythsandgods.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private ArrayList<VideoItem> mVideoList;
    private VideoAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        //void onFavoriteClick(int position);
        //Je mets cette méthode en commentaire car pas de favoris pour les vidéos pour l'instant
    }

    public void setOnItemClickListener(VideoAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{

        public YouTubeThumbnailView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public VideoViewHolder(@NonNull final View itemView, final VideoAdapter.OnItemClickListener listener) {
            super(itemView);
            //Définir éléments vidéo layout comme étant égaux à itemView.findViewById(R.id.element);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);

            //Déclarer et définir méthodes setOnClickListener
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick((position));
                        }
                    }
                }
            });

            mTextView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick((position));
                        }
                    }
                }
            });

            mTextView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick((position));
                        }
                    }
                }
            });

        }

    }

    public VideoAdapter(ArrayList<VideoItem> videoList) {
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        VideoAdapter.VideoViewHolder vvh = new VideoAdapter.VideoViewHolder(v, mListener);
        return vvh;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        final VideoItem currentItem = mVideoList.get(position);

        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getDescription());

        holder.mImageView.initialize(YouTube_Player_Activity.API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(currentItem.getUrl());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed

            }
        });

    }


    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

}
