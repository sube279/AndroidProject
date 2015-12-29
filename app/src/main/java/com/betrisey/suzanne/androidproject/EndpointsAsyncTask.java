package com.betrisey.suzanne.androidproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.betrisey.suzanne.dondesang.backend.cSangApi.model.CSang;
import com.betrisey.suzanne.dondesang.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.adapter.SangDataSource;

import com.betrisey.suzanne.dondesang.backend.cSangApi.CSangApi;

/**
 * Created by Suzanne on 26.12.2015.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, List<CSang>> {
    private static CSangApi cSangApi = null;
    private List<CSang> listInsertSang;

    EndpointsAsyncTask(List<CSang> list) {
        listInsertSang = list;

    }

    @Override
    protected void doInBackground(List<CSang>... params) {
        if (cSangApi == null) {  // Only do this once
            CSangApi.Builder builder = new CSangApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://dondesang-1111.appspot.com/_ah/api/");
            // end options for devappserver

            cSangApi = builder.build();
        }


        try {
            // Call here the wished methods on the Endpoints
            // For instance insert

            for (int i = 0; i < listInsertSang.size(); i++) {

                if (listInsertSang.get(i) != null) {

                    cSangApi.insertCSang(listInsertSang.get(i)).execute();
                    Log.i(TAG, "insert sang");
                }
            }

            return;
        }
        catch (IOException e){
        }

    }
}

