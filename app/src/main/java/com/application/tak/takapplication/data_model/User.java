package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 11/05/2017.
 */
public class User
{
    private Integer _Id;
    private String _LName;
    private String _FName;
    private String _Username;
    private String _PhoneNo;
    private Integer _RoleId;
    private Integer _IsActive;
    private Integer _AdressId;
    private Integer _ClassId;


    public Integer get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_LName() {
        return _LName;
    }

    public void set_LName(String _LName) {
        this._LName = _LName;
    }

    public String get_FName() {
        return _FName;
    }

    public void set_FName(String _FName) {
        this._FName = _FName;
    }

    public String get_Username() {
        return _Username;
    }

    public void set_Username(String _Username) {
        this._Username = _Username;
    }

    public String get_PhoneNo() {
        return _PhoneNo;
    }

    public void set_PhoneNo(String _PhoneNo) {
        this._PhoneNo = _PhoneNo;
    }

    public Integer get_RoleId() {
        return _RoleId;
    }

    public void set_RoleId(int _RoleId) {
        this._RoleId = _RoleId;
    }

    public Integer get_IsActive() {
        return _IsActive;
    }

    public void set_IsActive(int _IsActive) {
        this._IsActive = _IsActive;
    }

    public Integer get_AdressId() {
        return _AdressId;
    }

    public void set_AdressId(Integer _AdressId) {
        this._AdressId = _AdressId;
    }

    public Integer get_ClassId() {
        return _ClassId;
    }

    public void set_ClassId(Integer _ClassId) {
        this._ClassId = _ClassId;
    }
}