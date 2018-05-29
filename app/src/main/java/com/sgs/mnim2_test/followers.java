package com.sgs.mnim2_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class followers extends AppCompatActivity {

    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Intent i = this.getIntent();
        username = i.getStringExtra("user");
        TextView username_tv = (TextView) findViewById(R.id.username_text);
        username_tv.setText(username);
        TextView followers = (TextView)findViewById(R.id.followers_txt);
        followers.setText("Followers: " + i.getIntExtra("followers",0));
        TextView following = (TextView)findViewById(R.id.following_txt);
        following.setText("Following: "+i.getIntExtra("following",0));
        Picasso.with(this).load(i.getStringExtra("avatarURL")).into((ImageView) findViewById(R.id.avatar));

        API.getInstance().api.getFollowers(username).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> userList = response.body();
                    ListView lv = (ListView) findViewById(R.id.followers_list);
                    UserArrayAdapter uaa = new UserArrayAdapter(getApplicationContext(), userList);
                    lv.setAdapter(uaa);
                    ProgressBar pb = (ProgressBar)findViewById(R.id.loading);
                    pb.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_LONG).show();
            }
        });



    }


}
