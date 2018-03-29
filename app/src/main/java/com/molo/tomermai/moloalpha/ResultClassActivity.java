package com.molo.tomermai.moloalpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.molo.tomermai.moloalpha.model.EmptyClass;

public class ResultClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_class);

        EmptyClass emptyClassResult = (EmptyClass) getIntent()
                .getSerializableExtra(MainActivity.EXTRA_CLASS_RES);

        // todo: add results validation

        ((TextView) this.findViewById(R.id.response_class_name2))
                .setText(emptyClassResult.getClassName());

        ((TextView) this.findViewById(R.id.response_class_population2))
                .setText(String.valueOf(emptyClassResult.getClassPopulation()));

        ((TextView) this.findViewById(R.id.response_class_sound2))
                .setText(String.valueOf(emptyClassResult.getClassSoundLevel()));
    }
}
