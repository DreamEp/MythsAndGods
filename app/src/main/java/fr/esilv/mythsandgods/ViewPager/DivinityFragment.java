package fr.esilv.mythsandgods.ViewPager;

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

public class DivinityFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DivinityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        final ArrayList<DivinityItem> divinityList = new ArrayList<>();



        id = getArguments().getInt("key_id");

        category = getRef(id);
        reff_divinity = FirebaseDatabase.getInstance().getReference().child("CATEGORY").child(category).child("divinity_list");

        reff_divinity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot item:dataSnapshot.getChildren()){
                    DivinityItem divinity = item.getValue(DivinityItem.class);
                    divinityList.add(divinity);
                }
                DivinityAdapter mAdapter;
                mAdapter = new DivinityAdapter(divinityList);
                /*mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                        intent.putExtra("key_category", categoryList.get(position).getCategory_name());
                        startActivity(intent);
                        //OpenViewPagerActivity();
                        //categoryList.get(position).changeText1("Clicked");
                        //mAdapter.notifyItemChanged(position);
                    }
                });*/
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
