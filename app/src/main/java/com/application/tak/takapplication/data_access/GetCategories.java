package com.application.tak.takapplication.data_access;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.application.tak.takapplication.data_model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azielinska on 14/05/2017.
 */
public class GetCategories extends GetJSONData
{
    private static final String URL = Config.SERVER_NAME +"get_categories.php";
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID="ID";
    private static final String TAG_CATEGORY_NAME="CATEGORY_NAME";
    JSONArray categories = null;

    public List<Category> _categories;

    public GetCategories(Context ctx)
    {
        super(ctx);
        execute(URL);

    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        JsonResult = s;
        _categories = new ArrayList<Category>();
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(JsonResult.substring(JsonResult.indexOf("{"), JsonResult.lastIndexOf("}") + 1));

            categories = jsonObject.getJSONArray(TAG_RESULTS);

            for(int i=0;i<categories.length();i++)
            {
                Category _category = new Category();
                JSONObject c = categories.getJSONObject(i);
                _category.set_Id(c.getInt(TAG_ID));
                _category.set_CategoryName(c.getString(TAG_CATEGORY_NAME));;

                _categories.add(_category);
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
