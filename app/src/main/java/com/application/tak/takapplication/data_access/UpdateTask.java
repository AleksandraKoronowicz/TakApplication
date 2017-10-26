package com.application.tak.takapplication.data_access;

import android.os.AsyncTask;

import com.application.tak.takapplication.data_model.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by azielinska on 26.10.2017.
 */

public class UpdateTask extends AsyncTask<String,Void,String>
{
    private static final String URI = Config.SERVER_NAME+ "update_task.php";

    public boolean UpdateTask(Task t)
    {
        String date_from = Config.DATE_FORMAT.format(t.get_TimeFrom());
        String date_to = Config.DATE_FORMAT.format(t.get_TimeTo());
        execute(new String[]{t.get_CreatorId().toString(),t.get_StatusId().toString(),t.get_CreatorId().toString(),date_from,date_to,t.get_Id().toString()});
        return true;
    }

    @Override
    protected String doInBackground(String... params)
    {       String result = "";

        String category_id=params[0];
        String status_id=params[1];
        String creator_id=params[2];
        String time_from=params[3];
        String time_to=params[4];
        String task_id = params[5];
        String app_id = Config.ApplicationId;
        try {
            URL url=new URL(URI);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();

            String data= URLEncoder.encode("category_id","UTF-8")+"="+URLEncoder.encode(category_id,"UTF-8")+"&"+
                    URLEncoder.encode("status_id","UTF-8")+"="+URLEncoder.encode(status_id,"UTF-8")+"&"+
                    URLEncoder.encode("creator_id","UTF-8")+"="+URLEncoder.encode(creator_id,"UTF-8")+"&"+
                    URLEncoder.encode("time_from","UTF-8")+"="+URLEncoder.encode(time_from,"UTF-8")+"&"+
                    URLEncoder.encode("time_to","UTF-8")+"="+URLEncoder.encode(time_to,"UTF-8")+"&"+
            URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(task_id,"UTF-8")+"&"+
            URLEncoder.encode("application_id","UTF-8")+"="+URLEncoder.encode(app_id,"UTF-8");

            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();

            int statusCode = httpURLConnection.getResponseCode();
            if (statusCode == 200)
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                    sb.append(line).append("\n");

                result = sb.toString();
                bufferedWriter.close();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
