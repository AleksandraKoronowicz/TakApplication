package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.widget.Toast;

import com.application.tak.takapplication.data_model.Adress;
import com.application.tak.takapplication.data_model.Client_V;
import com.application.tak.takapplication.data_model.School;
import com.application.tak.takapplication.data_model.Student_V;
import com.application.tak.takapplication.data_model.User;

import org.json.JSONArray;
import org.json.JSONObject;

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

    private static final String TAG_ID="ID";
    private static final String TAG_LNAME="LNAME";
    private static final String TAG_FNAME="FNAME";
    private static final String TAG_USERNAME="USERNAME";
    private static final String TAG_PHONE_NO="PHONE_NO";
    private static final String TAG_ROLE_ID="ROLE_ID";
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

    Context context;
    public DB_result dbResult = null;
    public Client_V _client = null;
    public Student_V _student = null;

    private static String URI = Config.SERVER_NAME;
    private static String TAG_RESULTS="result";

    public boolean CheckPassword(User u, Integer role_id)
    {
        if(role_id == 1)
        {
            URI = URI +"check_student_password.php";
            TAG_RESULTS = "student";
        }
        else if(role_id == 2)
        {
            URI = URI +"check_client_password.php";
            TAG_RESULTS = "client";
        }
        else
        {
            return false;
        }

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
        execute(new String[]{u.get_Username(),passEncrypted, role_id.toString()});
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
        Integer role_id = Integer.parseInt(params[2]);

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

                result = sb.toString().trim();

                if(!ProcessData(result,role_id))
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
                    _client = client;

                }
            }
        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText( context, ex.toString(), Toast.LENGTH_LONG);
            t.show();
        }
    }

    private void ProcessStudent(String data)
    {
        try{
            JSONObject jsonObject = new JSONObject(data.substring(data.indexOf("{"), data.lastIndexOf("}") + 1));
            if(!jsonObject.isNull("student"))
            {
                JSONArray jsonUser = jsonObject.getJSONArray("student");

                if(jsonUser.length() == 1)
                {
                    Student_V student = new Student_V();
                    School sSchool = new School();
                    com.application.tak.takapplication.data_model.Class sClass = new com.application.tak.takapplication.data_model.Class();
                    JSONObject c = jsonUser.getJSONObject(0);
                    student.set_Id(c.getInt(TAG_ID));
                    student.set_LName(c.getString(TAG_LNAME));
                    student.set_FName(c.getString(TAG_FNAME));
                    student.set_Username(c.getString(TAG_USERNAME));
                    student.set_RoleId(c.getInt(TAG_ROLE_ID));
                    student.set_IsActive(1);
                    student.set_PhoneNo(c.getString(TAG_PHONE_NO));
                    student.set_ClassId(c.getInt(TAG_CLASS_ID));
                    sSchool.set_Id(c.getInt(TAG_SCHOOL_ID));
                    sSchool.set_SchoolName(c.getString(TAG_SCHOOL_NAME));
                    sClass.set_Id(c.getInt(TAG_CLASS_ID));
                    sClass.set_ClassName(c.getString(TAG_CLASS_NAME));
                    sClass.set_SignUpCode(c.getInt(TAG_SIGNUP_CODE));
                    sClass.set_SchoolId(c.getInt(TAG_SCHOOL_ID));
                    student.set_School(sSchool);
                    student.set_Class(sClass);

                    _student = student;

                }
            }
        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText( context, ex.toString(), Toast.LENGTH_LONG);
            t.show();
        }
    }


    public boolean ProcessData(String json, Integer roleId)
    {
        switch (roleId)
        {
            case 2:
                ProcessClient(json);
                break;
            case 1:
                ProcessStudent(json);
                break;
            default:
                break;
        }
        return false;
    }
}
