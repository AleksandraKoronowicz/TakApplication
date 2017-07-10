package com.application.tak.takapplication.data_access;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.Adress;
import com.application.tak.takapplication.data_model.Client_V;
import com.application.tak.takapplication.data_model.Task_V;
import com.application.tak.takapplication.data_model.User;
import com.application.tak.takapplication.interfaces.OnDBRequestFinished;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azielinska on 30.05.2017.
 */
public class CreateClient extends GetJSONData
{
    public CreateClient(Context ctx)
    {
        super(ctx);
        context = ctx;
    }

    Context context;
    public Client_V newClient = null;
    public DB_result dbResult = null;

    private static final String URI = Config.SERVER_NAME+ "new_client.php";
    private static final String TAG_RESULTS="new_client";
    private static final String TAG_ID="ID";
    private static final String TAG_LNAME="LNAME";
    private static final String TAG_FNAME="FNAME";
    private static final String TAG_USERNAME="USERNAME";
    private static final String TAG_PHONE_NO="PHONE_NO";
    private static final String TAG_ROLE_ID="ROLE_ID";
    private static final String TAG_IS_ACTIVE="IS_ACTIVE";
    private static final String TAG_ADRESS_ID="ADRESS_ID";
    private static final String TAG_CITY="CITY";
    private static final String TAG_ROAD="ROAD";
    private static final String TAG_POSTCODE="POSTCODE";
    private static final String TAG_ROAD_NO="ROAD_NO";
    private static final String TAG_PASSWORD="PASSWORD";

    public boolean InsertClient(User u, Adress a)
    {
       /* setDBRequestFinishedListener(new OnDBRequestFinished() {
            @Override
            public void onDBRequestFinished() {
                ProcessData();
            }
        });*/
        String passEncrypted = "";
       try {
            passEncrypted = Config.encrypt(u.get_Password());

       }
       catch(Exception ex)
       {
           Toast t = Toast.makeText(context,"Problem z utworzeniem konta",Toast.LENGTH_LONG);
           t.show();
           ex.printStackTrace();
           return false;
       }
       if(passEncrypted != "")
       {
        execute(new String[]{u.get_LName(),u.get_FName(),u.get_Username(), u.get_PhoneNo(),a.get_City(), a.get_Road(), a.get_PostCode(), a.get_RoadNo(), passEncrypted});
        return true;
       }

       return false;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String result = "";

        String lname=params[0];
        String fname=params[1];
        String username=params[2];
        String phone_no=params[3];
        String city = params[4];
        String road = params[5];
        String postcode = params[6];
        String road_no = params[7];
        String password = params[8];

            try{
            URL url=new URL(URI);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();

            String data= URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                    URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                    URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("phone_no","UTF-8")+"="+URLEncoder.encode(phone_no,"UTF-8")+"&"+
                    URLEncoder.encode("road","UTF-8")+"="+URLEncoder.encode(road,"UTF-8")+"&"+
                    URLEncoder.encode("road_no","UTF-8")+"="+URLEncoder.encode(road_no,"UTF-8")+"&"+
                    URLEncoder.encode("postcode","UTF-8")+"="+URLEncoder.encode(postcode,"UTF-8")+"&"+
                    URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"+
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


                bufferedWriter.close();
                result = sb.toString().trim();
                if(!ProcessData(result))
                {
                    Helper returnData = new Helper();
                    returnData.ProcessData(result);
                    dbResult = returnData.resultset;
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean ProcessData(String data)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(data.substring(data.indexOf("{"), data.lastIndexOf("}") + 1));
if(!jsonObject.isNull(TAG_RESULTS))
{
            JSONArray jsonUser = jsonObject.getJSONArray(TAG_RESULTS);

            if(jsonUser.length() == 1)
            {
                Client_V client = new Client_V();
                Adress adress = new Adress();
                JSONObject c = jsonUser.getJSONObject(0);
                client.set_Id(c.getInt(TAG_ID));
                client.set_LName(c.getString(TAG_LNAME));
                client.set_FName(c.getString(TAG_FNAME));
                client.set_Username(c.getString(TAG_USERNAME));
                client.set_RoleId(c.getInt(TAG_ROLE_ID));
                client.set_AdressId(c.getInt(TAG_ADRESS_ID));
                client.set_IsActive(1);
                client.set_PhoneNo(c.getString(TAG_PHONE_NO));
                adress.set_Id(client.get_AdressId());
                adress.set_City(c.getString(TAG_CITY));
                adress.set_PostCode(c.getString(TAG_POSTCODE));
                adress.set_RoadNo(c.getString(TAG_ROAD_NO));
                adress.set_Road(c.getString(TAG_ROAD));

                client.set_Adress(adress);
                newClient = client;
                return true;
            }


}
        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText( _context, ex.toString(), Toast.LENGTH_LONG);
            t.show();
            ex.printStackTrace();
        }

        return false;


    }


}