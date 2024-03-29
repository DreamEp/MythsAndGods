package fr.esilv.mythsandgods.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import fr.esilv.mythsandgods.ViewPager.DivinityItem;
import fr.esilv.mythsandgods.ViewPagerDivinity.ViewPagerActivityDivinity;

public class FavoriteFragment extends Fragment {

    private TextView text;
    private DatabaseReference reff_divinity;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_list_divinity, container, false);
        final View view2 = inflater.inflate(R.layout.divinity_item, container, false);

        final ArrayList<DivinityItem> favoriteList = new ArrayList<>();


        final String user_id = auth.getCurrentUser().getUid();
        reff_divinity = FirebaseDatabase.getInstance().getReference().child("USERS").child(user_id).child("divinity_list");

        reff_divinity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final ImageView mfavorite = (ImageView) view2.findViewById(R.id.image_favorite);

                for(DataSnapshot item:dataSnapshot.getChildren()){
                    DivinityItem divinity = item.getValue(DivinityItem.class);
                    favoriteList.add(divinity);
                }

                final FavoriteAdapter mAdapter;
                mAdapter = new FavoriteAdapter(favoriteList);
                mAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
                    @Override
                    public void onFavoriteClick(int position){
                        if(favoriteList.get(position).isFavorite() == false)
                        {
                            favoriteList.get(position).setFavorite(true);

                            String name = favoriteList.get(position).getName();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("USERS").child(user_id).child("divinity_list").child(name);
                            String title = favoriteList.get(position).getTitle();
                            String picture = favoriteList.get(position).getPicture();
                            String website = favoriteList.get(position).getWebsite();
                            boolean favorite = favoriteList.get(position).isFavorite();

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
                        else if(favoriteList.get(position).isFavorite() == true)
                        {
                            favoriteList.get(position).setFavorite(false);

                            String user_id = auth.getCurrentUser().getUid();
                            String name = favoriteList.get(position).getName();
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
                        intent.putExtra("key_website",favoriteList.get(position).getWebsite());
                        intent.putExtra("key_name", favoriteList.get(position).getName());
                        intent.putExtra("key_title", favoriteList.get(position).getTitle());
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
