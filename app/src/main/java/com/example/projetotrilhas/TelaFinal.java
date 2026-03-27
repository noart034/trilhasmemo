package com.example.projetotrilhas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class TelaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);

        // Espera 2 segundos e volta pro início
        new Handler().postDelayed(() -> {
            Intent i = new Intent(TelaFinal.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 2000);
    }
}