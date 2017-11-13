package com.application.tak.takapplication.data_access;

import android.os.AsyncTask;
import com.application.tak.takapplication.data_model.Task;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by azielinska on 23/05/2017.
 */
public class CreateTask extends AsyncTask<String,Void,String>
{
    private static final String URI = Config.SERVER_NAME+ "new_task.php";

    public boolean InsertTask(Task t)
    {

        String date_from = Config.DATE_FORMAT.format(t.get_TimeFrom());
        String date_to = Config.DATE_FORMAT.format(t.get_TimeTo());
        //executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new String[]{t.get_CreatorId().toString(),t.get_StatusId().toString(),t.get_CreatorId().toString(),date_from,date_to});
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new String[]{t.get_CategoryId().toString(),t.get_StatusId().toString(),t.get_CreatorId().toString(),date_from,date_to});
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
                    URLEncoder.encode("time_to","UTF-8")+"="+URLEncoder.encode(time_to,"UTF-8")+ "&"+
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


            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
        return result;
    }}
