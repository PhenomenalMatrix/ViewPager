package com.example.viewpager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager.OnPostsItemClick;
import com.example.viewpager.R;
import com.example.viewpager.data.models.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsAdapterVH> {

    public List<PostModel> postModelList = new ArrayList<>();
    public OnPostsItemClick onPostsItemClick;

    public void setOnPostsItemClick(OnPostsItemClick onPostsItemClick) {
        this.onPostsItemClick = onPostsItemClick;
    }

    public PostsAdapter() {

    }

    public void setPostList (List<PostModel> postModelList){
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new PostsAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapterVH holder, int position) {
        holder.onBind(postModelList.get(position));
    }

    @Override
    public int getItemCount() {return postModelList.size();}

    public class PostsAdapterVH extends RecyclerView.ViewHolder{

        TextView recyclerTitle;
        TextView recyclerContent;


        public PostsAdapterVH(@NonNull View itemView) {
            super(itemView);

            recyclerContent = itemView.findViewById(R.id.recycler_content);
            recyclerTitle = itemView.findViewById(R.id.recycler_title);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onPostsItemClick.onPostsItemViewClick(getAdapterPosition());


                    return true;
                }
            });
        }


        public void onBind(PostModel postModel) {
            recyclerTitle.setText(postModel.getTitle());
            recyclerContent.setText(postModel.getContent());

        }
    }
}
