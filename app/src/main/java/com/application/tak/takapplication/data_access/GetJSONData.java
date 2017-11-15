package com.application.tak.takapplication.data_access;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by azielinska on 14/05/2017.
 */
public class GetJSONData extends AsyncTask<String, Void, String>
{
    OnDBRequestFinished mListener;
    public String JsonResult = "";
    ProgressDialog pDialog;
    Context _context;
    public boolean showProgressDialog = true;

    public void setDBRequestFinishedListener(OnDBRequestFinished eventListener)
    {
        mListener = eventListener;
    }

    public GetJSONData(Context ctx)
    {
        _context = ctx;
    }
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        if(showProgressDialog) {
            pDialog = new ProgressDialog(_context);
            pDialog.setMessage("ŁADUJĘ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params)
    {

        String uri = params[0];

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }

            return sb.toString().trim();

        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(_context,e.toString(),Toast.LENGTH_LONG);
            toast.show();
            return "";
        }

    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        JsonResult = s;
        if(showProgressDialog)
            pDialog.dismiss();
        mListener.onDBRequestFinished();
    }
}