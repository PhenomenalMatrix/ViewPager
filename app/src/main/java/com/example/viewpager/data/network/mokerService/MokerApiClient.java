package com.example.viewpager.data.network.mokerService;

import com.example.viewpager.data.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MokerApiClient {

    private static MokerApiClient mokerApiClient;

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://android-3-mocker.herokuapp.com/")
            .build();


    public static MokerApiClient getInstance() {
        if (mokerApiClient == null) {
            mokerApiClient = new MokerApiClient();
        }
        return mokerApiClient;
    }

    public MokerApi getPostApi() {
        return retrofit.create(MokerApi.class);

    }

    public interface MokerApi {

        @GET("posts")
        Call<List<PostModel>> getPosts();

        @DELETE("posts/{id}")
        Call<PostModel> deletePostById(
                @Path("id") Integer itemId);

        @POST("posts")
        Call<PostModel> addPost
                (@Body PostModel post);

    }
}


