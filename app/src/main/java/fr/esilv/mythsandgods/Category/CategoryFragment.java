package fr.esilv.mythsandgods.Category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.esilv.mythsandgods.R;
import fr.esilv.mythsandgods.ViewPager.ViewPagerActivity;


public class CategoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;



    DatabaseReference reff_category;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_category, container, false);

        final ArrayList<CategoryItem> categoryList = new ArrayList<>();

        reff_category = FirebaseDatabase.getInstance().getReference().child("CATEGORY");

        reff_category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    CategoryItem category = item.getValue(CategoryItem.class);
                    categoryList.add(category);
                }
                CategoryAdapter mAdapter;
                mAdapter = new CategoryAdapter(categoryList);
                mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                        intent.putExtra("key_id", categoryList.get(position).getId());
                        intent.putExtra("key_website",categoryList.get(position).getWebsite());
                        intent.putExtra("key_summary", categoryList.get(position).getCategory_summary());
                        intent.putExtra("key_category_name", categoryList.get(position).getCategory_name());
                        startActivity(intent);
                        //OpenViewPagerActivity();
                        //categoryList.get(position).changeText1("Clicked");
                        //mAdapter.notifyItemChanged(position);
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
