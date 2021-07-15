package com.example.teste2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


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
        // Começo da inicialização do firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.useEmulator("10.0.2.2", 8080);


        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        //Fim da inicialização do firestore

        etItem = findViewById(R.id.etItem);
        etQuant = findViewById(R.id.etQuant);

        Button button = findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int qtdeParaSalvar = Integer.parseInt(etQuant.getText().toString().trim());
                String nomeParaSalvar = etItem.getText().toString().trim();


                Map<String, Object> itemData = new HashMap<>();
                itemData.put("Nome", nomeParaSalvar);
                DocumentReference novoElemento = db.document("/lista/"+nomeParaSalvar);
                novoElemento.set(itemData, SetOptions.merge());
                novoElemento.update("Qtde", FieldValue.increment(qtdeParaSalvar));

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