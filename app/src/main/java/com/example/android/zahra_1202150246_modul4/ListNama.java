package com.example.android.zahra_1202150246_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {

    //deklarasi variabel yang digunakan
    Button sync;
    ListView daftarNama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        //inisiasi masing-masing variabel yang sudah di deklarasikan
        sync = findViewById(R.id.asynctask_btn);
        daftarNama = findViewById(R.id.listMahasiswa);
    }

    //method ketika button Progress di klik
    public void Progress(View view) {
        new getData(daftarNama).execute(); //memulai proses AsyncTask
    }

    class getData extends AsyncTask<String, Integer, String> { //membuat sub-class AsyncTask

        //deklarasi semua variabel yang digunakan
        ListView daftarNama;
        ArrayAdapter adapter;
        ArrayList<String> list;
        ProgressDialog progress;

        public getData(ListView daftar) { //contructor ketika AsyncTask diinisiasi

            //inisiasi variabel yang digunakan
            this.daftarNama = daftar;
            progress = new ProgressDialog(ListNama.this);
            list = new ArrayList<>();
        }

        @Override
        protected void onPreExecute() { //method yang digunakan sebelum AsyncTask dimulai
            super.onPreExecute();

            progress.setTitle("Loading Data"); //Pesan pada AsyncTask
            progress.setIndeterminate(false); //mengatur tampilan progress
            progress.setProgress(0); //mengatur ukuran minimal dari progres: 0
            progress.setMax(100); //mengatur ukuran full atau maksimal progress: 100%
            //progress akan loading dimulai dari 0 hingga 100%

            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); //mengatur arah loading progress
            progress.setCancelable(true);
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progress.dismiss();
                    getData.this.cancel(true);
                }
            });
            progress.show(); //method untuk menampilkan progress
        }
        @Override
        protected String doInBackground(String... strings){ //method ketika AsyncTask dilakukan
            //membuat Arrayadapter
            adapter = new ArrayAdapter<>(ListNama.this, android.R.layout.simple_list_item_1, list);

            //membuat dan menyimpan array kedalam variabel mahasiswa pada strings.xml
            String [] mahasiswa = getResources().getStringArray(R.array.mahasiswa);

            //membuat kondisi untuk menyimpan array ke dalam variabel a
            for (int a=0; a<mahasiswa.length; a++){ //inisiasi kondisi
                final long persen = 100L*a/mahasiswa.length; //membuat formula untuk lama persenan/nama yang telah dibuat pada array
                final String nama = mahasiswa[a];
                //mengatur ketika meload satu nama berapa persen yang akan ditampilkan, dan disesuaikan dengan progress bar
                try{
                    Runnable tampilpesan = new Runnable() {

                        @Override
                        public void run() {
                            progress.setMessage((int)persen+"% - Adding : "+nama);
                        }
                    };
                    runOnUiThread(tampilpesan);
                    Thread.sleep(200); //mengatur waktu loading data dalam array
                    list.add(mahasiswa[a]); //menambahkan item ke dalam variabel yang memuat arraylist
                } catch (InterruptedException e){ //ketika eksekusi gagal lakukan method ini
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute (String b){ //method ketika AsyncTask telah dilakukan
            super.onPostExecute(b);
            daftarNama.setAdapter (adapter); //mengatur adapter
            progress.dismiss();//menutup progress bar
        }
    }
}