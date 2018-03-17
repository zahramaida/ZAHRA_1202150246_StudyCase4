package com.example.android.zahra_1202150246_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listNama(View view) {
        Intent intent = new Intent(MainActivity.this, ListNama.class);
        startActivity(intent);
    }

    public void cariGambar(View view) {
        Intent intent = new Intent(MainActivity.this, CariGambar.class);
        startActivity(intent);
    }
}
