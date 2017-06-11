package com.application.tak.takapplication.data_access;

import android.os.AsyncTask;
import com.application.tak.takapplication.data_model.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by azielinska on 30.05.2017.
 */
public class CreateStudent extends AsyncTask<String,Void,String>
{
    private static final String URI = Config.SERVER_NAME+ "new_student.php";

    public boolean InsertStudent(User u)
    {
        execute(new String[]{u.get_LName(),u.get_FName(),u.get_Username(), u.get_PhoneNo(),u.get_IsActive().toString(),u.get_ClassId().toString()});
        return true;
    }

    @Override
    protected String doInBackground(String... params)
    {       String result = "";

        String lname=params[0];
        String fname=params[1];
        String username=params[2];
        String phone_no=params[3];
        String is_active=params[4];
        String class_id = params[5];
        try {
            URL url=new URL(URI);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();

            String data= URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                    URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                    URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("phone_no","UTF-8")+"="+URLEncoder.encode(phone_no,"UTF-8")+"&"+
                    URLEncoder.encode("is_active","UTF-8")+"="+URLEncoder.encode(is_active,"UTF-8")+"&"+
                    URLEncoder.encode("class_id","UTF-8")+"="+URLEncoder.encode(class_id,"UTF-8");

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
