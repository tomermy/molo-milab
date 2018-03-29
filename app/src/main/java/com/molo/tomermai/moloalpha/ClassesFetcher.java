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

public class ClassesFetcher {

    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.2.2:8080/classes";

    public class ClassesResponse {
        public boolean isError;
        public String className;
        public int classPopulation;
        public int classSound;

        public ClassesResponse(boolean isError, String className, int classPopulation, int classSound) {
            this.isError = isError;
            this.className = className;
            this.classPopulation = classPopulation;
            this.classSound = classSound;
        }

    }

    public interface ClassesResponseListener {
        public void onResponse(ClassesResponse response);
    }

    public ClassesFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private ClassesResponse createErrorResponse() {
        return new ClassesResponse(true, null, 0, 0);
    }

    public void dispatchRequest(final String building, final ClassesResponseListener listener) {
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("building", building);
        } catch (JSONException e) {
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray items = response.getJSONArray("items");
                            // todo: Implement iteration on JSON Array / Deserialize
//                            ClassesResponse res = new ClassesResponse(false,
//                                    items.getJSONObject(0).getString("className"),
//                                    items.getJSONObject(0).getInt("classPopulation"),
//                                    items.getJSONObject(0).getInt("soundLevel"));

                            // Hardcode version for testing
                            ClassesResponse res = new ClassesResponse(false,
                                    "E303",
                                    2,
                                    1);

                            listener.onResponse(res);
//                        } catch (JSONException e) {
//                            listener.onResponse(createErrorResponse());
//                        }
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
