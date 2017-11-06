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



public class UpdateTask extends AsyncTask<String,Void,String>
{
    private static final String URI = Config.SERVER_NAME+ "update_task.php";

    public boolean UpdateTask(Task t)
    {
        String execution_time = "null";
        String executor_id = "null";
        if(t.get_ExecutionTime() != null)
            execution_time = Config.DATE_FORMAT.format(t.get_ExecutionTime());
        if(t.get_ExecutorId() != null)
            executor_id = t.get_ExecutorId().toString();
        execute(new String[]{executor_id, t.get_StatusId().toString(),t.get_Id().toString(),execution_time,t.get_IsApproved().toString()});
        return true;
    }

    @Override
    protected String doInBackground(String... params)
    {       String result = "";

        String executor_id = params[0];
        String status_id=params[1];
        String task_id = params[2];
        String execution_time = params[3];
        String is_approved = params[4];
        try {
            URL url=new URL(URI);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();

            String data= URLEncoder.encode("executor_id","UTF-8")+"="+URLEncoder.encode(executor_id,"UTF-8")+"&"+
                    URLEncoder.encode("status_id","UTF-8")+"="+URLEncoder.encode(status_id,"UTF-8")+"&"+
            URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(task_id,"UTF-8")+"&"+
                    URLEncoder.encode("execution_time","UTF-8")+"="+URLEncoder.encode(execution_time,"UTF-8")+"&"+
                    URLEncoder.encode("is_approved","UTF-8")+"="+URLEncoder.encode(is_approved,"UTF-8")+"&"+
            URLEncoder.encode("application_id","UTF-8")+"="+URLEncoder.encode(Config.ApplicationId,"UTF-8");

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
