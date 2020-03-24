package fr.esilv.mythsandgods.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fr.esilv.mythsandgods.R;

public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.MonsterViewHodler> {
    private ArrayList<MonsterItem> mMonsterList;
    private MonsterAdapter.OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MonsterAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class MonsterViewHodler extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public ImageView mImageFavorite;
        public TextView mTextView2;

        public MonsterViewHodler(@NonNull View itemView, final MonsterAdapter.OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mImageFavorite = itemView.findViewById(R.id.image_favorite);

            itemView.setOnClickListener(new View.OnClickListener() {
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


    public MonsterAdapter(ArrayList<MonsterItem> monsterList) {
        mMonsterList = monsterList;
    }

    @NonNull
    @Override
    public MonsterViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.monster_item, parent, false);
        MonsterViewHodler cvh = new MonsterViewHodler(v, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MonsterViewHodler holder, int position) {
        MonsterItem currentItem = mMonsterList.get(position);
        Glide.with(holder.itemView.getContext()).load(currentItem.getPicture()).into(holder.mImageView);
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMonsterList.size();
    }
}
