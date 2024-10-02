package com.example.playmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView play,stop,pause,imgAlbum;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //mediaPlayer = MediaPlayer.create(this, R.raw.comopodetudomudar);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        imgAlbum = findViewById(R.id.album);

        play.setOnClickListener(view -> {
            if (mediaPlayer != null){
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });

        stop.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.comopodetudomudar);
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        if (mediaPlayer != null){
            mediaPlayer.start();
        }else {
            imgAlbum.setImageResource(R.drawable.erromusic);
            Toast.makeText(this, "Sem Música Encontrada", Toast.LENGTH_LONG).show();
        }
        Log.i("LIFE CYCLE", "START");
    }

    @Override
    public void onResume(){
        super.onResume();

        Log.i("LIFE CYCLE", "RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();

        Log.i("LIFE CYCLE", "PAUSE");
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
        Log.i("LIFE CYCLE", "STOP");
    }

    @Override
    public void onRestart(){
        super.onRestart();

        Log.i("LIFE CYCLE", "RESTART");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Log.i("LIFE CYCLE", "DESTROY");
        }
    }
}