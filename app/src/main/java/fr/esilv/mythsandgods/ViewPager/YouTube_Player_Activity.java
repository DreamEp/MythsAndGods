package fr.esilv.mythsandgods.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import fr.esilv.mythsandgods.R;

public class YouTube_Player_Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener{

    YouTubePlayerView playerView;

    public static final String API_KEY="AIzaSyC0EoPM59osI4HEvctZCQzfNEhe3CxOm6o";

    private String video_url;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(b != null)
            video_url = b.getString("videoURL");
        setContentView(R.layout.activity_yt_player);
        playerView = findViewById(R.id.playerview);
        playerView.initialize(API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
    {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if(!b)
        {
            youTubePlayer.cueVideo(video_url);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
    {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
