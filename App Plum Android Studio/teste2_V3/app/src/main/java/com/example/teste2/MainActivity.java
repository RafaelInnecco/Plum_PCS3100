package com.example.teste2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureLoginButton();
    }
    private void configureLoginButton(){
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view){
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }
    });
    }
}