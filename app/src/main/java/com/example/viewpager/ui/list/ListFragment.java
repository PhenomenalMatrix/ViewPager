package com.example.viewpager.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.viewpager.App;
import com.example.viewpager.OnPostsItemClick;
import com.example.viewpager.R;
import com.example.viewpager.adapters.PostsAdapter;
import com.example.viewpager.data.models.PostModel;
import com.example.viewpager.data.network.mokerService.MokerApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostsAdapter postsAdapter;
    List<PostModel> postModels;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);

        postsAdapter = new PostsAdapter();
        recyclerView.setAdapter(postsAdapter);
        recyclerView.invalidate();


        App.mockerService.getPostApi().getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postModels = new ArrayList<>();
                postModels = response.body();
                postsAdapter.setPostList(postModels);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });

        postsAdapter.setOnPostsItemClick(new OnPostsItemClick() {
            @Override
            public void onPostsItemViewClick(int position) {
                MokerApiClient.getInstance().getPostApi().deletePostById(postModels.get(position).getId()).enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Toast.makeText(getContext(),"delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Toast.makeText(getContext(),"error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}