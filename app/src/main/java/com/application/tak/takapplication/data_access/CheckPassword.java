package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by azielinska on 10/07/2017.
 */
public class CheckPassword extends GetJSONData {

    public CheckPassword(Context ctx)
    {
        super(ctx);
        context = ctx;
    }

    Context context;
    DB_result dbResult = null;

    private static final String URI = Config.SERVER_NAME+ "check_password.php";
    private static final String TAG_RESULTS="result";

    public boolean CheckPassword(User u)
    {
        String passEncrypted = "";
        try {
            passEncrypted = Config.encrypt(u.get_Password());

        }
        catch(Exception ex)
        {
            Toast t = Toast.makeText(context,"Problem z weryfikacją hasła.",Toast.LENGTH_LONG);
            t.show();
            ex.printStackTrace();
            return false;
        }
        if(passEncrypted != "")
        {
        execute(new String[]{u.get_Username(),passEncrypted});
        return true;
        }
        return false;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String result = "";

        String username=params[0];
        String password = params[1];

        try{
            URL url=new URL(URI);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();

            String data=
                    URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") +"&"+
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


                    Helper returnData = new Helper();
                    returnData.ProcessData(result);
                    dbResult = returnData.resultset;

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
