package com.zhuyux.waterbubble;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhuyux.waterbubble.utils.Buble;
import com.zhuyux.waterbubble.utils.BubleManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Buble> continueBubles;
    private List<Buble> twinkleBubles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continueBubles = BubleManager.createContinueBubles(new Point(200, 200), this);
        twinkleBubles = BubleManager.createTwinkleBubles(new Point(200, 200), this);
        System.out.println(continueBubles);
        System.out.println("=============================");
        System.out.println(twinkleBubles);
    }
}
