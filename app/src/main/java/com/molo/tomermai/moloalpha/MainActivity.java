package com.molo.tomermai.moloalpha;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.molo.tomermai.moloalpha.model.ClassesResponse;
import com.molo.tomermai.moloalpha.model.EmptyClass;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    public static final String EXTRA_CLASS_RES = "com.example.tomermai.ex2.FAMILY";

    /**
     * URL for class data from the Molo servers
     */
    private static final String MOLO_REQUEST_URL =
            "https://nj7e2klboc.execute-api.eu-west-1.amazonaws.com/follow_molo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the molo button
     */
    public void startResultActivity(View view, EmptyClass emptyClassResult) {
        Intent intent = new Intent(this, ResultClassActivity.class);
        intent.putExtra(EXTRA_CLASS_RES, emptyClassResult);
        startActivity(intent);
    }

    public void fetchClasses(final View view) {
        final ClassesFetcher fetcher = new ClassesFetcher(view.getContext());
        final String building = ((EditText) findViewById(R.id.building_name_input)).getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Looking for empty classes...");
        progressDialog.show();

        // temporal hard coded hack
//        EmptyClass emptyClassResult = new EmptyClass(
//                "E303",
//                1,
//                1, m_timeLeft, m_imageURL);

//        startResultActivity(view, emptyClassResult);

        fetcher.dispatchRequest(building, new ClassesFetcher.ClassesResponseListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(ClassesResponse response) {
                progressDialog.hide();

                if (response.isError()) {
                    Toast.makeText(view.getContext(),
                            "Error while looking for a class",
                            Toast.LENGTH_LONG);

                    return;
                }
                EmptyClass currentClass = response.getEmptyClasses().get(0);
//                EmptyClass emptyClassResult = new EmptyClass(
//                        response.className,
//                        response.classSound,
//                        response.classPopulation, m_timeLeft, m_imageURL);

                startResultActivity(view, currentClass);

//                ((TextView) MainActivity.this.findViewById(R.id.response_class_name))
//                        .setText(response.className);
//
//                ((TextView) MainActivity.this.findViewById(R.id.response_class_population))
//                        .setText(String.valueOf(response.classPopulation));
//
//                ((TextView) MainActivity.this.findViewById(R.id.response_class_sound))
//                        .setText(response.classSound);
            }
        });
    }
}
