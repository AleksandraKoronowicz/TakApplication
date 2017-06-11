package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 11/05/2017.
 */
public class Class
{
    private int _Id;
    private String _ClassName;
    private int _SchoolId;
    private int _SignUpCode;

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_ClassName() {
        return _ClassName;
    }

    public void set_ClassName(String _ClassName) {
        this._ClassName = _ClassName;
    }

    public int get_SchoolId() {
        return _SchoolId;
    }

    public void set_SchoolId(int _SchoolId) {
        this._SchoolId = _SchoolId;
    }

    public int get_SignUpCode() {
        return _SignUpCode;
    }

    public void set_SignUpCode(int _SignUpCode) {
        this._SignUpCode = _SignUpCode;
    }
}
