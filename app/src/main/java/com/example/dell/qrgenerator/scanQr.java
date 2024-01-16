package com.example.dell.qrgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanQr extends AppCompatActivity {

    Button scan;
    ImageView qr_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        scan=(Button)findViewById(R.id.scan);
        qr_code=(ImageView)findViewById(R.id.qr_code);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator i=new IntentIntegrator(scanQr.this);
                i.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                i.setCameraId(0);
                i.setPrompt("Place the Qr Code Inside the frame");
                i.setBeepEnabled(true);
                i.setBarcodeImageEnabled(true);
                i.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult res =IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (res != null)
        {
            new AlertDialog.Builder(scanQr.this).setTitle("Scan Result").setMessage(res.getContents())
                    .setPositiveButton("Copy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ClipboardManager manager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData data = ClipData.newPlainText("result", res.getContents());
                            manager.setPrimaryClip(data);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
