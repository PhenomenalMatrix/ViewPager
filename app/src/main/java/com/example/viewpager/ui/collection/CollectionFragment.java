package com.example.viewpager.ui.collection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.viewpager.R;
import com.example.viewpager.data.models.PostModel;
import com.example.viewpager.data.network.mokerService.MokerApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionFragment extends Fragment {

    private ImageView buttonAdd;
    private EditText edTitle,edContent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonAdd = view.findViewById(R.id.image_add);
        edTitle = view.findViewById(R.id.ed_title);
        edContent = view.findViewById(R.id.ed_content);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostModel postModel = new PostModel();
                postModel.setContent(edContent.getText().toString().trim());
                postModel.setTitle(edTitle.getText().toString().trim());
                MokerApiClient.getInstance().getPostApi().addPost(postModel).enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}