package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.Task_V;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azielinska on 30.05.2017.
 */
public class GetAllTasks extends GetJSONData
{
    private static final String URL = Config.SERVER_NAME +"get_all_tasks.php";
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID="ID";
    private static final String TAG_CATEGORY_NAME="CATEGORY_NAME";
    private static final String TAG_TIME_FROM="TIME_FROM";
    private static final String TAG_TIME_TO="TIME_TO";
    private static final String TAG_CREATION_TIME="CREATION_TIME";
    private static final String TAG_IS_APPROVED="IS_APPROVED";
    private static final String TAG_EXECUTION_TIME="EXECUTION_TIME";
    private static final String TAG_CATEGORY_ID="CATEGORY_ID";
    private static final String TAG_STATUS_ID="STATUS_ID";
    private static final String TAG_STATUS_NAME="STATUS_NAME";
    private static final String TAG_CREATOR_ID="CREATOR_ID";
    private static final String TAG_CREATOR_FNAME="CREATOR_FNAME";
    private static final String TAG_CREATOR_LNAME="CREATOR_LNAME";
    private static final String TAG_CREATOR_USERNAME="CREATOR_USERNAME";
    private static final String TAG_CREATOR_PHONE_NO="CREATOR_PHONE_NO";
    private static final String TAG_CREATOR_ADRESS_ID="CREATOR_ADRESS_ID";
    private static final String TAG_CREATOR_CITY="CREATOR_CITY";
    private static final String TAG_CREATOR_ROAD="CREATOR_ROAD";
    private static final String TAG_CREATOR_POSTCODE="CREATOR_POSTCODE";
    private static final String TAG_CREATOR_ROAD_NO="CREATOR_ROAD_NO";
    private static final String TAG_EXECUTOR_ID="EXECUTOR_ID";
    private static final String TAG_EXECUTOR_FNAME="EXECUTOR_FNAME";
    private static final String TAG_EXECUTOR_LNAME="EXECUTOR_LNAME";
    private static final String TAG_EXECUTOR_USERNAME="EXECUTOR_USERNAME";
    private static final String TAG_EXECUTOR_PHONE_NO="EXECUTOR_PHONE_NO";
    private static final String TAG_EXECUTOR_ADRESS_ID="EXECUTOR_ADRESS_ID";
    private static final String TAG_EXECUTOR_CITY="EXECUTOR_CITY";
    private static final String TAG_EXECUTOR_ROAD="EXECUTOR_ROAD";
    private static final String TAG_EXECUTOR_POSTCODE="EXECUTOR_POSTCODE";
    private static final String TAG_EXECUTOR_ROAD_NO="EXECUTOR_ROAD_NO";
    private static final String TAG_EXECUTOR_SCHOOL_ID="EXECUTOR_SCHOOL_ID";
    private static final String TAG_EXECUTOR_SCHOOL_NAME="EXECUTOR_SCHOOL_NAME";
    private static final String TAG_EXECUTOR_CLASS_ID="EXECUTOR_CLASS_ID";
    private static final String TAG_EXECUTOR_CLASS_NAME="EXECUTOR_CLASS_NAME";

    JSONArray tasks = null;

    public List<Task_V> _tasks;

    public GetAllTasks(Context ctx)
    {
        super(ctx);
        execute(URL);
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        JsonResult = s;
        _tasks = new ArrayList<Task_V>();
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(JsonResult.substring(JsonResult.indexOf("{"), JsonResult.lastIndexOf("}") + 1));

            tasks = jsonObject.getJSONArray(TAG_RESULTS);

            for(int i=0;i<tasks.length();i++)
            {
                Task_V t = new Task_V();
                JSONObject c = tasks.getJSONObject(i);
                t.set_Id(c.getInt(TAG_ID));
                t.set_CategoryName(c.getString(TAG_CATEGORY_NAME));;
                t.set_TimeFrom(Config.DATE_FORMAT.parse(c.getString(TAG_TIME_FROM)));
                t.set_TimeTo(Config.DATE_FORMAT.parse(c.getString(TAG_TIME_TO)));
                //t.set_CreationTime((DateFormat) c.get(TAG_CREATION_TIME));
                t.set_IsApproved(c.getInt(TAG_IS_APPROVED));
               // t.set_ExecutionTime((DateFormat) c.get(TAG_EXECUTION_TIME));
                t.set_CategoryId(c.getInt(TAG_CATEGORY_ID));
                t.set_StatusId(c.getInt(TAG_STATUS_ID));
                t.set_StatusName(c.getString(TAG_STATUS_NAME));
                t.set_CreatorId(c.getInt(TAG_CREATOR_ID));
                t.set_CreatorAdressId(c.getInt(TAG_CREATOR_ADRESS_ID));
                t.set_CreatorCity(c.getString(TAG_CREATOR_CITY));
                t.set_CreatorFName(c.getString(TAG_CREATOR_FNAME));
                t.set_CreatorLName(c.getString(TAG_CREATOR_LNAME));
                t.set_CreatorPhoneNo(c.getString(TAG_CREATOR_PHONE_NO));
                t.set_CreatorUsername(c.getString(TAG_CREATOR_USERNAME));
                t.set_CreatorRoad(c.getString(TAG_CREATOR_ROAD));
                t.set_CreatorPostCode(c.getString(TAG_CREATOR_POSTCODE));
                t.set_CreatorRoadNo(c.getString(TAG_CREATOR_ROAD_NO));
                t.set_ExecutorId(c.getInt(TAG_EXECUTOR_ID));
                t.set_ExecutorAdressId(c.getInt(TAG_EXECUTOR_ADRESS_ID));
                t.set_ExecutorCity(c.getString(TAG_EXECUTOR_CITY));
                t.set_ExecutorFName(c.getString(TAG_EXECUTOR_FNAME));
                t.set_ExecutorLName(c.getString(TAG_EXECUTOR_LNAME));
                t.set_ExecutorPhoneNo(c.getString(TAG_EXECUTOR_PHONE_NO));
                t.set_ExecutorUsername(c.getString(TAG_EXECUTOR_USERNAME));
                t.set_ExecutorRoad(c.getString(TAG_EXECUTOR_ROAD));
                t.set_ExecutorRoadNo(c.getString(TAG_EXECUTOR_ROAD_NO));
                t.set_ExecutorPostCode(c.getString(TAG_EXECUTOR_POSTCODE));
                t.set_ExecutorClassId(c.isNull(TAG_EXECUTOR_CLASS_ID) ? null:c.getInt(TAG_EXECUTOR_CLASS_ID));
                t.set_ExecutorClassName(c.isNull(TAG_EXECUTOR_CLASS_NAME) ? null:c.getString(TAG_EXECUTOR_CLASS_NAME));
                t.set_ExecutorSchoolId(c.isNull(TAG_EXECUTOR_SCHOOL_ID)? null:c.getInt(TAG_EXECUTOR_SCHOOL_ID));
                t.set_ExecutorSchoolName(c.isNull(TAG_EXECUTOR_SCHOOL_NAME)?null:c.getString(TAG_EXECUTOR_SCHOOL_NAME));

                _tasks.add(t);
            }
        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText( _context, ex.toString(), Toast.LENGTH_LONG);
        }
        pDialog.dismiss();
        mListener.onDBRequestFinished();
    }


}