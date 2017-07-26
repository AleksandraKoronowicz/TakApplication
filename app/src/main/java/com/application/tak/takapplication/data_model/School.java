package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 11/05/2017.
 */
public class School
{
    private int _Id;
    private String _SchoolName;
    private int _AdressId;

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_SchoolName() {
        return _SchoolName;
    }

    public void set_SchoolName(String _SchoolName) {
        this._SchoolName = _SchoolName;
    }

    public int get_AdressId() {
        return _AdressId;
    }

    public void set_AdressId(int _AdressId) {
        this._AdressId = _AdressId;
    }
}
