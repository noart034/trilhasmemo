package com.example.projetotrilhas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Tela03 extends AppCompatActivity implements View.OnClickListener {

    private ImageView img1, img2, img3, img4;
    private ImageView imgPrimeiroToque, imgSegundoToque;

    private int imagemPrimeiroToque, imagemSegundoToque;
    private int contaToque = 0;
    private int paresEncontrados = 0;

    private boolean bloqueado = false;

    private ArrayList<Integer> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela03);

        TextView texto = findViewById(R.id.textView3);

        // Recebe nome
        Bundle caixa = getIntent().getExtras();
        if (caixa != null) {
            String nome = caixa.getString("nome");
            texto.setText(nome);
        }

        img1 = findViewById(R.id.imageView2);
        img2 = findViewById(R.id.imageView3);
        img3 = findViewById(R.id.imageView4);
        img4 = findViewById(R.id.imageView5);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        // LISTA COM PARES CERTOS
        lista = new ArrayList<>();
        lista.add(R.drawable.ic_action_name_3);
        lista.add(R.drawable.ic_action_name_3);
        lista.add(R.drawable.ic_action_name_4);
        lista.add(R.drawable.ic_action_name_4);

        Collections.shuffle(lista);

        mostrarCartas();

        // esconder depois de 3s
        new Handler().postDelayed(this::esconderCartas, 3000);
    }

    private void mostrarCartas() {
        img1.setImageResource(lista.get(0));
        img2.setImageResource(lista.get(1));
        img3.setImageResource(lista.get(2));
        img4.setImageResource(lista.get(3));
    }

    private void esconderCartas() {
        img1.setImageResource(R.drawable.ic_action_name_2);
        img2.setImageResource(R.drawable.ic_action_name_2);
        img3.setImageResource(R.drawable.ic_action_name_2);
        img4.setImageResource(R.drawable.ic_action_name_2);
    }

    @Override
    public void onClick(View v) {

        if (bloqueado) return;

        contaToque++;

        ImageView imgAtual = (ImageView) v;
        int index = 0;

        if (v == img2) index = 1;
        if (v == img3) index = 2;
        if (v == img4) index = 3;

        imgAtual.setImageResource(lista.get(index));

        if (contaToque == 1) {
            imgPrimeiroToque = imgAtual;
            imagemPrimeiroToque = lista.get(index);
        } else {
            imgSegundoToque = imgAtual;
            imagemSegundoToque = lista.get(index);

            bloqueado = true;
            comparar();
        }
    }

    private void comparar() {

        new Handler().postDelayed(() -> {

            if (imagemPrimeiroToque == imagemSegundoToque) {

                imgPrimeiroToque.setBackgroundColor(Color.GREEN);
                imgSegundoToque.setBackgroundColor(Color.GREEN);

                imgPrimeiroToque.setEnabled(false);
                imgSegundoToque.setEnabled(false);

                paresEncontrados++;

                // venceu
                if (paresEncontrados == 2) {
                    Intent i = new Intent(Tela03.this, TelaFinal.class);
                    startActivity(i);
                    finish();
                }

            } else {

                imgPrimeiroToque.setImageResource(R.drawable.ic_action_name_2);
                imgSegundoToque.setImageResource(R.drawable.ic_action_name_2);
            }

            contaToque = 0;
            bloqueado = false;

        }, 1000);
    }
}