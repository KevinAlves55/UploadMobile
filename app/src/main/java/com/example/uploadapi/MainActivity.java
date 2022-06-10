package com.example.uploadapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar;
    ImageView imgLivro;
    EditText txtTitulo;
    final int GALERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        imgLivro = findViewById(R.id.imgLivro);
        txtTitulo = findViewById(R.id.txtTitulo);

        btnCadastrar.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider
                            .MediaStore
                            .Images.
                            Media.EXTERNAL_CONTENT_URI);

            intent.setType("image/*");

            startActivityForResult(intent, GALERY);

        });

    }//FIM DO MÉTODO ONCREATE

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_CANCELED){

            return;

        }

        if(requestCode == GALERY){

            if(data != null){
                Uri uri = data.getData();

                try{

                    Bitmap bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(this.getContentResolver(), uri);
                    imgLivro.setImageBitmap(bitmap);

                }catch(IOException e){
                    Log.d("IMAGEM", e.getMessage());
                }

            }

        }

    }
}//FIM DA CLASSE









