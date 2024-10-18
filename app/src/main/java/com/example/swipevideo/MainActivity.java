package com.example.swipevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);

        List<VideoItem> videoItems = new ArrayList<>();

        VideoItem videoCatWalk = new VideoItem();
        videoCatWalk.videoURL = "https://firebasestorage.googleapis.com/v0/b/lab2-11fe7.appspot.com/o/IMG_5070.mov?alt=media&token=41280a4c-8e08-45c5-a979-ef184a8fe9ae";
        videoCatWalk.videoTitle = "Cat Walking";
        videoCatWalk.videoId = "ID:102848039";
        videoCatWalk.videoDesc = "Adorable cat happy to see owner!";
        videoItems.add(videoCatWalk);

        VideoItem videoCatChirping = new VideoItem();
        videoCatChirping.videoURL = "https://firebasestorage.googleapis.com/v0/b/lab2-11fe7.appspot.com/o/IMG_5791.mov?alt=media&token=d3f48338-fe8e-4f10-842e-292edcaa6044";
        videoCatChirping.videoTitle = "Cat Chirping";
        videoCatChirping.videoId = "ID:34039940349";
        videoCatChirping.videoDesc = "Another adorable cat chirping at a bird!";
        videoItems.add(videoCatChirping);

        VideoItem videoCatsWatching = new VideoItem();
        videoCatsWatching.videoURL = "https://firebasestorage.googleapis.com/v0/b/lab2-11fe7.appspot.com/o/IMG_8345.mov?alt=media&token=2ec13942-1738-40c2-b68c-b20a75bfe8dc";
        videoCatsWatching.videoTitle = "Cats Watching TV";
        videoCatsWatching.videoId = "ID:92817482749";
        videoCatsWatching.videoDesc = "Two adorable cats watching birds on catTV!";
        videoItems.add(videoCatsWatching);

        videoViewPager.setAdapter(new VideoAdapter(videoItems));
    }
}