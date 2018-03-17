package com.example.android.zahra_1202150246_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.InputStream;

public class CariGambar extends AppCompatActivity {

    //deklarasi semua variabel yang akan digunakan
    EditText link_gambar;
    ImageView tampil_gambar;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        //inisiasi masing-masing variabel yang telah di deklarasikan
        link_gambar = findViewById(R.id.search_gambar);
        tampil_gambar = findViewById(R.id.gambar);

    }

    public void onClick(View view) {
        String URLGambar = link_gambar.getText().toString();
        if(URLGambar.isEmpty()){
            //Menampilkan toast ketika button diklik namun edit text url kosong
            Toast.makeText(CariGambar.this,"Masukkan URL gambar terlebih dahulu",Toast.LENGTH_SHORT).show();
        }else {
            // Execute DownloadImage AsyncTask
        new DownloadImage().execute(URLGambar);
        }
    }

    class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() { //method yang digunakan sebelum AsyncTask dimulai
            super.onPreExecute();
            //membuat progress dialog
            progress = new ProgressDialog(CariGambar.this);
            //Set judul progress dialog
            progress.setTitle("Search Image");
            //set pesan pada proses dialog
            progress.setMessage("Loading Image");
            progress.setIndeterminate(false);
            // tampilkan progress dialog
            progress.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) { //menjalankan method ketika AsyncTask dilakukan

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url yang diinputkan
                InputStream input = new java.net.URL(imageURL).openStream();
                // merubah input dari url menjadi bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) { //method ketika Asyntask selesai dilakukan
            // mengeset bitmap ke dalam imageView yang sudah di sediakan
            tampil_gambar.setImageBitmap(result);
            progress.dismiss();//menutup progress bar
        }
    }
}