package com.example.dell.qrgenerator;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class generateQr extends AppCompatActivity {
    Button generate;
    EditText mytext;
    ImageView qr_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        generate=(Button)findViewById(R.id.generate);
        mytext =(EditText)findViewById(R.id.mytext);
        qr_code=(ImageView)findViewById(R.id.qr_code);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= mytext.getText().toString();
                if (text != null)
                {
                    try{
                        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                        BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,400,400);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        qr_code.setImageBitmap(bitmap);
                        Toast.makeText(getApplicationContext(),"QR Generated Successfully",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception obj)
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Something : :"+obj,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
