package com.molo.tomermai.moloalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.molo.tomermai.moloalpha.model.EmptyClass;

public class ResultClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_class);

        final EmptyClass emptyClassResult = (EmptyClass) getIntent()
                .getSerializableExtra(MainActivity.EXTRA_CLASS_RES);

        // todo: add results validation

        ImageView classImage = this.findViewById(R.id.empty_class_image);
        Button shareButton = this.findViewById(R.id.share_whatsapp_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWhatsAppIntent(emptyClassResult);
            }
        });

        Glide.with(this).load("http://via.placeholder.com/300.png").into(classImage);

        ((TextView) this.findViewById(R.id.response_class_name2))
                .setText(emptyClassResult.getClassName());

        ((TextView) this.findViewById(R.id.response_class_population2))
                .setText(String.valueOf(emptyClassResult.getClassPopulation()));

        ((TextView) this.findViewById(R.id.response_class_sound2))
                .setText(String.valueOf(emptyClassResult.getClassSoundLevel()));
    }

    private void createWhatsAppIntent(EmptyClass emptyClassResult) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT,
                "Molo discovered *" +emptyClassResult.getClassName() + "* is Empty!");
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            printToastToScreen("WhatsApp is not installed!");
        }
    }

    private void printToastToScreen(String msg) {
        Toast.makeText(ResultClassActivity.this,
                msg,
                Toast.LENGTH_LONG).show();
    }
}
