package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 09/11/2017.
 */

public class Student_V extends User {

    private Class _Class;
    private School _School;

    public Class get_Class() {
        return _Class;
    }

    public void set_Class(Class _Class) {
        this._Class = _Class;
    }

    public School get_School() {
        return _School;
    }

    public void set_School(School _School) {
        this._School = _School;
    }
}
