package com.example.selenskitchenapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

public class CustomGalleryActivity extends AppCompatActivity {

    private ArrayList<String> f = new ArrayList<>(); //List of file paths
    File[] listFile;
    private final String folderName = "MyPhotoDir";
    // Creating object of viewPager
    public ViewPager mViewPager;
    // Creating Object of ViewPagerAdapter
    public ViewPagerAdapter mViewPagerAdapter;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getFromSdcard();
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.viewPagerCamera);
        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(this,f);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    public void getFromSdcard(){
        File file = new File(getExternalFilesDir(folderName),"/");
        if (file.isDirectory()){
            listFile = file.listFiles();
            for (int i = 0 ; i < listFile.length; i++){
                f.add(listFile[i].getAbsolutePath());

            }
        }
    }
}
