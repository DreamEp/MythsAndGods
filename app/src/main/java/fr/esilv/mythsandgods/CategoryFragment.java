package fr.esilv.mythsandgods;

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

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        final ArrayList<CategoryItem> categoryList = new ArrayList<>();
        categoryList.add(new CategoryItem(R.drawable.nordic, "Mythologie Nordique", "Froid !"));
        categoryList.add(new CategoryItem(R.drawable.greek, "Mythologie Grecque", "Nue !"));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CategoryAdapter(categoryList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TabLayoutFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_category);
                openWebViewActivity();
                //categoryList.get(position).changeText1("Clicked");
                //mAdapter.notifyItemChanged(position);
            }
        });
        return view;
    }

    public void openWebViewActivity(){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        startActivity(intent);
    }
}
