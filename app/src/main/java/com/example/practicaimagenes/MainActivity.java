package com.example.practicaimagenes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b;
    ImageView imagen;
    //int SELECT_picture=1;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            //new ActivityResultCallback<ActivityResult>() es lo mismo que result ->
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    ObtenerImagen(data);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.iniciar_controles();
    }


    private void iniciar_controles(){
        b=findViewById(R.id.Buscar);
        imagen=findViewById(R.id.Foto);

    }

    public void abrir_galeria(View view) {
        Intent i = new Intent().setAction(Intent.ACTION_PICK);
        i.setType("image/*");
        launcher.launch(i);
        //startActivity(i);
        // startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }


    public void ObtenerImagen(Intent data) {
        Uri selectedImageUri = data.getData();
        if (null != selectedImageUri) {
            // update the preview image in the layout
            imagen.setImageURI(selectedImageUri);
        }

    }

   /*  deprecated


    void SeleccionarImagen() {

        // create an instance of the
          // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
          // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }*/
}