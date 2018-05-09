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
import com.molo.tomermai.moloalpha.model.EmptyClassList;
import com.molo.tomermai.moloalpha.util.Mappers;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.molo.tomermai.moloalpha.util.Mappers.getClassNoiseOrEmpty;
import static com.molo.tomermai.moloalpha.util.Mappers.getClassPopulationOrEmpty;

public class ResultClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_class);

        final List<EmptyClass> emptyClasses = ((EmptyClassList) getIntent()
                .getSerializableExtra(MainActivity.EXTRA_CLASS_RES)).getClasses();

        final ListIterator<EmptyClass> classIterator = emptyClasses.listIterator();
        EmptyClass emptyClassResult = null;
        if (!emptyClasses.isEmpty()) {
            emptyClassResult = classIterator.next();
        }
        // todo: add results validation

        ImageView classImage = this.findViewById(R.id.empty_class_image);
        Button shareButton = this.findViewById(R.id.share_whatsapp_button);
        Button nextButton = this.findViewById(R.id.next_class_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextEmptyClassIndex = classIterator.nextIndex();
                if (nextEmptyClassIndex == emptyClasses.size() + 1) {
                    createWhatsAppIntent(classIterator.previous());
                } else {
                    createWhatsAppIntent(emptyClasses.get(nextEmptyClassIndex - 1));
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classIterator.hasNext()) {
                    updateFieldsUI(classIterator.next());
                } else {
                    printToastToScreen("NO MORE CLASSES!");
                }
            }
        });


//        Glide.with(this).load("http://www.mealliance.com.au/wp-content/uploads/2017/03/education-classroom-empty.jpg").into(classImage);
        Glide.with(this).load(R.drawable.arison_1).into(classImage);
        updateFieldsUI(emptyClassResult);


    }

    private void updateFieldsUI(EmptyClass emptyClassResult) {
        ((TextView) this.findViewById(R.id.response_class_name2))
                .setText(emptyClassResult.getClassName());

        ((ImageView) this.findViewById(R.id.response_class_population2))
//                .setText(String.valueOf(emptyClassResult.getClassPopulation()));
                .setImageResource(getClassPopulationOrEmpty(emptyClassResult.getClassPopulation()));

        ((ImageView) this.findViewById(R.id.response_class_sound2))
//                .setText(String.valueOf(emptyClassResult.getClassSoundLevel()));
                .setImageResource(getClassNoiseOrEmpty(emptyClassResult.getClassSoundLevel()));
    }

    private void createWhatsAppIntent(EmptyClass emptyClassResult) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT,
                "Molo discovered *" + emptyClassResult.getClassName() + "* is Empty!");
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
