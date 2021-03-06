package com.example.teste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        configureAddButton();
        configureListButton();
        configureList2Button();
    }

    private void configureAddButton(){
        Button add = (Button) findViewById(R.id.adicionar);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });
    }
    private void configureListButton(){
        Button lista = (Button) findViewById(R.id.lista);
        lista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity2.this, MainActivity6.class));
            }
        });
    }
    private void configureList2Button(){
        Button lista = (Button) findViewById(R.id.remedio);
        lista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity2.this, MainActivity7.class));
            }
        });
    }

}