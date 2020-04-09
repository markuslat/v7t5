package com.example.v7t5;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    EditText text;
    EditText tiedosto;
    Button tallenna;
    Button lataa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        text = (EditText) findViewById(R.id.editText);
        tiedosto = (EditText) findViewById(R.id.tiedosto);
        tallenna = (Button) findViewById(R.id.tallenna);
        lataa = (Button) findViewById(R.id.lataa);
        System.out.println("Tiedoston sijainti" + context.getFilesDir());


        lataa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tulos;
                try {
                    InputStream ins = context.openFileInput(tiedosto.getText().toString().trim());
                    BufferedReader br = new BufferedReader(new InputStreamReader(ins));

                    while ((tulos = br.readLine()) != null) {
                        text.setText(tulos);
                    }

                    ins.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tallenna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(tiedosto.getText().toString().trim(), Context.MODE_PRIVATE));

                    ows.write(text.getText().toString().trim());
                    ows.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

