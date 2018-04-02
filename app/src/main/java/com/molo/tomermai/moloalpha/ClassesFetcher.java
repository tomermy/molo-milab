package com.molo.tomermai.moloalpha;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.molo.tomermai.moloalpha.model.ClassesResponse;
import com.molo.tomermai.moloalpha.model.EmptyClass;

import java.util.ArrayList;
import java.util.List;

public class ClassesFetcher {

    private RequestQueue _queue;
    private final static String REQUEST_URL =
            "https://nj7e2klboc.execute-api.eu-west-1.amazonaws.com/follow_molo";
    private List<EmptyClass> EMPTY_RESULT = new ArrayList<>();

    public interface ClassesResponseListener {
        public void onResponse(ClassesResponse response);
    }


    public ClassesFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private ClassesResponse createErrorResponse() {
        return new ClassesResponse(true,
                true,
                0,
                new ArrayList<EmptyClass>());
    }

    public void dispatchRequest(final String building, final ClassesResponseListener listener) {
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("building", building);
        } catch (JSONException e) {
        }

//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, requestObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        try {
////                            JSONArray items = response.getJSONArray("items");
//                            // todo: Implement iteration on JSON Array / Deserialize
////                            ClassesResponse res = new ClassesResponse(false,
////                                    items.getJSONObject(0).getString("className"),
////                                    items.getJSONObject(0).getInt("classPopulation"),
////                                    items.getJSONObject(0).getInt("soundLevel"));
//
//                            // Hardcode version for testing
//                            ClassesResponse res = new ClassesResponse(false,
//                                    "E303",
//                                    2,
//                                    1);
//
//                            listener.onResponse(res);
////                        } catch (JSONException e) {
////                            listener.onResponse(createErrorResponse());
////                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onResponse(createErrorResponse());
//            }
//        });
//
//        _queue.add(req);
//    }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            JSONArray items = response.getJSONArray("items");
                            JSONObject item = new JSONObject(String.valueOf(response));
                            JSONArray items = new JSONArray();
                            items.put(item);

                            List<EmptyClass> emptyClassesListFromJSON = new ArrayList<>();
                            int numOfResults = items.length();
                            if (numOfResults == 0) {
                                listener.onResponse(new ClassesResponse(false,
                                        true,
                                        numOfResults,
                                        EMPTY_RESULT));
                            } else {
                                for (int i = 0; i < numOfResults; i++) {
                                    JSONObject currentEmptyClass = items.getJSONObject(i);
                                    String className = currentEmptyClass.getString("className");
                                    int population = currentEmptyClass.getInt("population");
                                    int noise = currentEmptyClass.getInt("noise");
                                    int timeLeft = currentEmptyClass.getInt("timeLeft");
                                    String imageURL = currentEmptyClass.getString("classImage");

                                    EmptyClass emptyClass = new EmptyClass(className,
                                            noise,
                                            population,
                                            timeLeft,
                                            imageURL);

                                    emptyClassesListFromJSON.add(emptyClass);
                                }
                                ClassesResponse res = new ClassesResponse(false,
                                        false,
                                        numOfResults,
                                        emptyClassesListFromJSON);

                                listener.onResponse(res);
                            }
                        } catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}
