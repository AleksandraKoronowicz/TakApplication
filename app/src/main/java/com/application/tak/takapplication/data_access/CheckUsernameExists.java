package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by azielinska on 11/11/2017.
 */

public class CheckUsernameExists extends GetJSONData {

    private static String URL = Config.SERVER_NAME + "check_username_exists.php" ;
    private static final String TAG_RESULTS="result";
    public DB_result dbResult = null;

    public CheckUsernameExists(String username,Context ctx)
    {
        super(ctx);
        execute(new String[]{username});
    }

    @Override
    protected String doInBackground(String... params)
    {
        String result = "";
        String username = params[0];

        BufferedReader bufferedReader = null;
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            OutputStream os=con.getOutputStream();

            String data=
                    URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                            URLEncoder.encode("application_id","UTF-8")+"="+URLEncoder.encode(Config.ApplicationId,"UTF-8");

            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }

            result = sb.toString().trim();

                Helper returnData = new Helper();
                returnData.ProcessData(result);
                dbResult = returnData.resultset;

            return result;

        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(_context,e.toString(),Toast.LENGTH_LONG);
            toast.show();
            return "";
        }

    }
}
