package com.example.teste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("item");
            String age = bundle.getString("quant");
            TextView tvName = findViewById(R.id.item);
            TextView tvAge = findViewById(R.id.quant);
            tvName.setText(name);
            tvAge.setText(age);
        }

        configureBackButton();
        configureAddButton();
    }

    private void configureBackButton(){
        Button voltar = (Button) findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity6.this, MainActivity2.class));
            }
        });
    }
    private void configureAddButton(){
        Button add = (Button) findViewById(R.id.adicionar);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity6.this, MainActivity3.class));
            }
        });
    }
}