package fr.esilv.mythsandgods.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.esilv.mythsandgods.R;
import fr.esilv.mythsandgods.ViewPagerDivinity.ViewPagerActivityDivinity;

public class DivinityFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DivinityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    DatabaseReference reff_divinity;
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
        final View view = inflater.inflate(R.layout.fragment_list_divinity, container, false);
        final View view2 = inflater.inflate(R.layout.divinity_item, container, false);

        final ArrayList<DivinityItem> divinityList = new ArrayList<>();


        id = getArguments().getInt("key_id");

        category = getRef(id);
        reff_divinity = FirebaseDatabase.getInstance().getReference().child("CATEGORY").child(category).child("divinity_list");

        reff_divinity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final ImageView mfavorite = (ImageView) view2.findViewById(R.id.image_favorite);

                for(DataSnapshot item:dataSnapshot.getChildren()){
                    DivinityItem divinity = item.getValue(DivinityItem.class);
                    divinityList.add(divinity);
                }

                final DivinityAdapter mAdapter;
                mAdapter = new DivinityAdapter(divinityList);
                mAdapter.setOnItemClickListener(new DivinityAdapter.OnItemClickListener() {
                    @Override
                    public void onFavoriteClick(int position){
                        if(divinityList.get(position).isFavorite() == false)
                        {
                            divinityList.get(position).setFavorite(true);

                            String user_id = auth.getCurrentUser().getUid();
                            String name = divinityList.get(position).getName();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("USERS").child(user_id).child("divinity_list").child(name);
                            String title = divinityList.get(position).getTitle();
                            String picture = divinityList.get(position).getPicture();
                            String website = divinityList.get(position).getWebsite();
                            boolean favorite = divinityList.get(position).isFavorite();

                            Map newPost = new HashMap();
                            newPost.put("name", name);
                            newPost.put("title", title);
                            newPost.put("picture",picture);
                            newPost.put("website", website);
                            newPost.put("favorite", favorite);

                            current_user_db.setValue(newPost);

                            mfavorite.setImageResource(R.drawable.ic_favorite_plein);
                            mAdapter.notifyItemChanged(position);
                            Toast.makeText(getActivity(),"Ajouté aux favoris!",Toast.LENGTH_SHORT).show();
                        }
                        else if(divinityList.get(position).isFavorite() == true)
                        {
                            divinityList.get(position).setFavorite(false);

                            String user_id = auth.getCurrentUser().getUid();
                            String name = divinityList.get(position).getName();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("USERS").child(user_id).child("divinity_list").child(name);
                            current_user_db.removeValue();

                            mfavorite.setImageResource(R.drawable.ic_favorite_border);
                            mAdapter.notifyItemChanged(position);
                            Toast.makeText(getActivity(),"Supprimé des favoris!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ViewPagerActivityDivinity.class);
                        intent.putExtra("key_website",divinityList.get(position).getWebsite());
                        intent.putExtra("key_name", divinityList.get(position).getName());
                        intent.putExtra("key_title", divinityList.get(position).getTitle());
                        startActivity(intent);
                    }
                });
                mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new GridLayoutManager(getActivity(),3);
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
