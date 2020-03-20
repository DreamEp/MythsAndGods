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

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /*
    private TextView moduleNameTextView;
    private TextView teacherTextView;
    private TextView semesterNameTextView;*/



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        final ArrayList<CategoryItem> categoryList = new ArrayList<>();
        //categoryList.add(new CategoryItem(R.drawable.nordic, "Mythologie Nordique"));
        //categoryList.add(new CategoryItem(R.drawable.greek, "Mythologie Grecque"));
        categoryList.add(initializeData());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CategoryAdapter(categoryList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
                //intent.putExtra("key_category1", categoryList.get(position).);
                startActivity(intent);
                //OpenViewPagerActivity();
                //categoryList.get(position).changeText1("Clicked");
                //mAdapter.notifyItemChanged(position);
            }
        });
        return view;
    }


    private CategoryItem initializeData() {
        //get the raw Json as A Stream
        InputStream categoryAsInputStream = getResources().openRawResource(R.raw.mythsandgods_category);

        //get a String from the Stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = categoryAsInputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = categoryAsInputStream.read();
            }
            categoryAsInputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        String categoryAsString = byteArrayOutputStream.toString();

        //parse the String as an Object using Gson
        Gson gson = new Gson();
        CategoryItem categoryList = gson.fromJson(categoryAsString, CategoryItem.class);

        return categoryList;
    }
}
