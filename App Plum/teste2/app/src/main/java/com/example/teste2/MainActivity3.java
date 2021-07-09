package com.example.teste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    EditText etItem, etQuant;
    Button camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        camera = (Button)findViewById(R.id.camera_button);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        etItem = findViewById(R.id.etItem);
        etQuant = findViewById(R.id.etQuant);
        Button button = findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString().trim();
                String quant = etQuant.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString("item", item);
                bundle.putString("quant", quant);
                Intent intent = new Intent(MainActivity3.this, MainActivity6.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        configureBackButton();




    }

    private void configureBackButton(){
        Button voltar = (Button) findViewById(R.id.voltar);
        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
    }



}