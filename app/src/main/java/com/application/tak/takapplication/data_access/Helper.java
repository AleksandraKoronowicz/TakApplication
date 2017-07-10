package com.application.tak.takapplication.data_access;

import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azielinska on 02/07/2017.
 */
public class Helper
{
    private static final String TAG_RESULTS="result";
    private static final String TAG_LAST_INSERT_ID="LAST_INSERT_ID";
    private static final String TAG_RESULT = "RESULT";
    private static final String TAG_MSG = "MSG";
    public DB_result resultset = null;

    public boolean ProcessData(String JsonResult){
        resultset = new DB_result();
    JSONObject jsonObject;
        try
    {
        jsonObject = new JSONObject(JsonResult);

        if(!jsonObject.isNull(TAG_RESULTS)){
        JSONArray jArray = jsonObject.getJSONArray(TAG_RESULTS);

        if(jArray.length()== 1)
        {
            DB_result _result = new DB_result();
            JSONObject c = jArray.getJSONObject(0);
            _result.setMSG(c.getString(TAG_MSG));
            _result.setRESULT(c.getInt(TAG_RESULT));
            _result.setLAST_INSERT_ID(c.isNull(TAG_LAST_INSERT_ID) ? null : c.getInt(TAG_LAST_INSERT_ID));

            resultset = _result;
            return true;
        }
        }


    }
        catch (Exception ex)
    {

    }
    return false;
    }
}
