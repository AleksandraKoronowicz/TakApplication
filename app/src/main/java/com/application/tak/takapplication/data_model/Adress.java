package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 11/05/2017.
 */
public class Adress
{
    private int _Id;
    private String _City;
    private String _Road;
    private String _PostCode;
    private String _RoadNo;

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_City() {
        return _City;
    }

    public void set_City(String _City) {
        this._City = _City;
    }

    public String get_Road() {
        return _Road;
    }

    public void set_Road(String _Road) {
        this._Road = _Road;
    }

    public String get_PostCode() {
        return _PostCode;
    }

    public void set_PostCode(String _PostCode) {
        this._PostCode = _PostCode;
    }

    public String get_RoadNo() {
        return _RoadNo;
    }

    public void set_RoadNo(String _RoadNo) {
        this._RoadNo = _RoadNo;
    }
}
