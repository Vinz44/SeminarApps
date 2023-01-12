package com.project.seminar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.seminar.Config;
import com.project.seminar.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvJudul, tvTgl, tvDeskripsi;
    private ImageView imgView;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvJudul = (TextView) findViewById(R.id.tv_judul_detail);
        tvTgl = (TextView) findViewById(R.id.tv_tgl_detail);
        tvDeskripsi = (TextView) findViewById(R.id.tv_desc_detail);
        imgView = (ImageView) findViewById(R.id.imageView5);

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("Id");
        tvJudul.setText(mIntent.getStringExtra("Name"));
        tvTgl.setText(mIntent.getStringExtra("Date"));
        tvDeskripsi.setText(mIntent.getStringExtra("Description"));

        // Masukan Gambar Ke Image View Gunakan Glide
        Glide.with(DetailActivity.this)
                .load(Config.IMAGES_URL + mIntent.getStringExtra("Image"))
                .apply(new RequestOptions().override(350, 550))
                .into(imgView);
    }
}