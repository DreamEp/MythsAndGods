package fr.esilv.mythsandgods.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import fr.esilv.mythsandgods.R;
import fr.esilv.mythsandgods.ViewPagerDivinity.ViewPagerActivityDivinity;

public class VideoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private VideoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    DatabaseReference reff_videoList;
    int id;
    String category;

    public String getRef(int id)
    {
        if (id == 1)
        {
            category = "Nordic";
            return category;

        }
        else if (id == 2)
        {
            category ="Greek";
            return category;
        }
        else return category;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_video, container, false);
        final View view2 = inflater.inflate(R.layout.video_item, container, false);





        final ArrayList<VideoItem> videoList = new ArrayList<>();


        id = getArguments().getInt("key_id");

        category = getRef(id);

        reff_videoList = FirebaseDatabase.getInstance().getReference().child("CATEGORY").child(category).child("video_list");

        reff_videoList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot item:dataSnapshot.getChildren()){
                    VideoItem video = item.getValue(VideoItem.class);
                    videoList.add(video);
                }

                final VideoAdapter mAdapter;
                mAdapter = new VideoAdapter(videoList);
                mAdapter.notifyDataSetChanged();
                mAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), YouTube_Player_Activity.class);
                        Bundle b = new Bundle();
                        b.putString("videoURL", videoList.get(position).getUrl());
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        return view;
    }
}
