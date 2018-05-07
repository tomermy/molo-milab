package com.molo.tomermai.moloalpha;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.molo.tomermai.moloalpha.model.ClassesResponse;
import com.molo.tomermai.moloalpha.model.EmptyClassList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    public static final String EXTRA_CLASS_RES = "com.example.tomermai.ex2.FAMILY";
    LottieAnimationView lottieAnimation;

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
    public void startResultActivity(View view, EmptyClassList emptyClassResult) {
        Intent intent = new Intent(this, ResultClassActivity.class);
        intent.putExtra(EXTRA_CLASS_RES, emptyClassResult);
        startActivity(intent);
    }

    public void fetchClasses(final View view) {
        final ClassesFetcher fetcher = new ClassesFetcher(view.getContext());
        final String building = "";
//        final String building = ((EditText) findViewById(R.id.building_name_input)).getText().toString();
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Looking for empty classes...");
//        progressDialog.show();

        fetcher.dispatchRequest(building, new ClassesFetcher.ClassesResponseListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(ClassesResponse response) {
//                progressDialog.hide();
                lottieAnimation.cancelAnimation();

                if (response.isError()) {
                    Toast.makeText(view.getContext(),
                            "Error while looking for a class",
                            Toast.LENGTH_LONG).show();

                    return;
                }
                EmptyClassList currentClass = response.getEmptyClasses();
                startResultActivity(view, currentClass);
            }
        });
    }
}
