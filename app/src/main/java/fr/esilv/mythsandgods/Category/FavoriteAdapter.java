package fr.esilv.mythsandgods.Category;

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
import fr.esilv.mythsandgods.ViewPager.DivinityItem;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private ArrayList<DivinityItem> mFavoriteList;
    private FavoriteAdapter.OnItemClickListener mListener;


    public interface  OnItemClickListener{
        void onItemClick(int position);
        void onFavoriteClick(int position);
    }

    public void setOnItemClickListener(FavoriteAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public static class FavoriteViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public ImageView mImageFavorite;
        public TextView mTextView2;

        public FavoriteViewHolder(@NonNull final View itemView, final FavoriteAdapter.OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mImageFavorite = itemView.findViewById(R.id.image_favorite);

            mImageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onFavoriteClick((position));
                        }
                    }
                }
            });

            mImageView.setOnClickListener(new View.OnClickListener() {
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

            mTextView2.setOnClickListener(new View.OnClickListener() {
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

            mTextView1.setOnClickListener(new View.OnClickListener() {
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


    public FavoriteAdapter(ArrayList<DivinityItem> favoriteList) {
        mFavoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.divinity_item, parent, false);
        FavoriteAdapter.FavoriteViewHolder cvh = new FavoriteAdapter.FavoriteViewHolder(v, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        DivinityItem currentItem = mFavoriteList.get(position);
        Glide.with(holder.itemView.getContext()).load(currentItem.getPicture()).into(holder.mImageView);
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getTitle());
        if(currentItem.isFavorite()) holder.mImageFavorite.setImageResource(R.drawable.ic_favorite_plein);
    }

    @Override
    public int getItemCount() {
        return mFavoriteList.size();
    }
}
