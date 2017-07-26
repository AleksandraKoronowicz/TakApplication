package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.Adress;
import com.application.tak.takapplication.data_model.Category;
import com.application.tak.takapplication.data_model.Client_V;
import com.application.tak.takapplication.data_model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by azielinska on 04/07/2017.
 */
public class GetUserById extends GetJSONData
{
    private static String URL = Config.SERVER_NAME ;
    private static final String TAG_ID="ID";
    private static final String TAG_LNAME="LNAME";
    private static final String TAG_FNAME="FNAME";
    private static final String TAG_USERNAME="USERNAME";
    private static final String TAG_PHONE_NO="PHONE_NO";
    private static final String TAG_ROLE_ID="ROLE_ID";
    private static final String TAG_IS_ACTIVE="IS_ACTIVE";
    private static final String TAG_ADRESS_ID="ADRESS_ID";
    private static final String TAG_CLASS_ID="CLASS_ID";
    private static final String TAG_CITY="CITY";
    private static final String TAG_ROAD="ROAD";
    private static final String TAG_POSTCODE="POSTCODE";
    private static final String TAG_ROAD_NO="ROAD_NO";
    //for student and teacher only
    private static final String TAG_CLASS_NAME="CLASS_NAME";
    private static final String TAG_SCHOOL_ID="SCHOOL_ID";
    private static final String TAG_SIGNUP_CODE="SIGNUP_CODE";
    private static final String TAG_SCHOOL_NAME="SCHOOL_NAME";

    JSONArray jsonUser = null;

    public User _user;
    public DB_result dbResult;

    public GetUserById(String userType,Integer id,Context ctx)
    {
        super(ctx);
        switch (userType)
        {
            case "Client":
                URL = URL+"get_client_by_id.php";
                break;
            case "Student":
                URL = URL+"get_student_by_id.php";
                break;
            case "Teacher":
                URL = URL+"get_teacher_by_id.php";
                break;
            default: URL = Config.SERVER_NAME +"get_user_by_id.php";
            break;
        }
        execute(new String[]{URL,id.toString(),userType});
    }

    @Override
    protected String doInBackground(String... params)
    {
        String result = "";
        String uri = params[0];
        String id = params[1];
        String userType = params[2];

        BufferedReader bufferedReader = null;
        try {
            java.net.URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            OutputStream os=con.getOutputStream();

            String data=
                    URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
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
            if(!ProcessData(result, userType))
            {
                Helper returnData = new Helper();
                returnData.ProcessData(result);
                dbResult = returnData.resultset;
            }
            return result;

        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(_context,e.toString(),Toast.LENGTH_LONG);
            toast.show();
            return "";
        }

    }

private void ProcessClient(String data)
{
    try{
    JSONObject jsonObject = new JSONObject(data.substring(data.indexOf("{"), data.lastIndexOf("}") + 1));
    if(!jsonObject.isNull("client"))
    {
        JSONArray jsonUser = jsonObject.getJSONArray("client");

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
            Config.LoggedInClient = client;
        }
    }
}
        catch (Exception ex)
    {
        Toast t = Toast.makeText( _context, ex.toString(), Toast.LENGTH_LONG);
        t.show();
    }
}

    public boolean ProcessData(String json, String userType)
    {
        switch (userType)
        {
            case "Client":
                ProcessClient(json);
                break;
            case "Student":
                break;
            case "Teacher":
                break;
            default:
                break;
        }
        return false;
    }

}
