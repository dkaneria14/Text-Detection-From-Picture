package com.example.visiontryout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

     ImageView imageView;
     TextView textView;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        imageView= findViewById(R.id.imageView1);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertImageToText( v);
            }
        });


    }



    public void ConvertImageToText(View view){
        // Storing the image from the imageView to the bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.twosentence);
        //Using the method from vision API to recognize the text
        // getApplicationContext because it will return the context of entire application and not the context of specific activity
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();


        //checking if TextRecognizer really detected any text or not!
        if(!textRecognizer.isOperational()){
            Toast.makeText(this, "Couldnt detect text! Try again", Toast.LENGTH_SHORT).show();
        }

        else{
            // setting the bitmap image to the frame class(from vision)||image info is stored in a Frame and this frame is detected by TextRecognizer
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            //The detect method(pre defined from vision api, takes a frame object)
            SparseArray<TextBlock> arrayOfText = textRecognizer.detect(frame);
                TextBlock tb = arrayOfText.valueAt(0);
            textView.setText(tb.getValue());
            }
        }


    }
