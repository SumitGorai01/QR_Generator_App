package com.example.dell.qrgenerator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class splashscreen extends AppCompatActivity {

    VideoView splash_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        splash_video=(VideoView)findViewById(R.id.splash_video);
        Uri video=Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.sps);
        splash_video.setVideoURI(video);
        splash_video.start();
        splash_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(isFinishing())
                    return;
                startActivity(new Intent(splashscreen.this,HomeActivity.class));
                finish();
            }
        });
        getSupportActionBar().hide();
    }
}
