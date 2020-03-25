package fr.esilv.mythsandgods.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.esilv.mythsandgods.R;
import fr.esilv.mythsandgods.ViewPagerDivinity.ViewPagerActivityDivinity;
import fr.esilv.mythsandgods.ViewPagerMonster.ViewPagerActivityMonster;
import fr.esilv.mythsandgods.ViewPagerMonster.ViewPagerAdapterMonster;

public class MonsterFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MonsterAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DatabaseReference reff_monster;
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
        final View view = inflater.inflate(R.layout.fragment_list_monster, container, false);

        final ArrayList<MonsterItem> monsterList = new ArrayList<>();



        id = getArguments().getInt("key_id");

        category = getRef(id);
        reff_monster = FirebaseDatabase.getInstance().getReference().child("CATEGORY").child(category).child("monster_list");

        reff_monster.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot item:dataSnapshot.getChildren()){
                    MonsterItem monster = item.getValue(MonsterItem.class);
                    monsterList.add(monster);
                }
                MonsterAdapter mAdapter;
                mAdapter = new MonsterAdapter(monsterList);
                mAdapter.setOnItemClickListener(new MonsterAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ViewPagerActivityMonster.class);
                        intent.putExtra("key_website",monsterList.get(position).getWebsite());
                        intent.putExtra("key_name", monsterList.get(position).getName());
                        intent.putExtra("key_title", monsterList.get(position).getTitle());
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
