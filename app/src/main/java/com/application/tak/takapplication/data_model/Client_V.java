package com.application.tak.takapplication.data_model;

/**
 * Created by azielinska on 05/07/2017.
 */
public class Client_V extends User
{
    private Adress _Adress;

    public Adress get_Adress() {
        return _Adress;
    }

    public void set_Adress(Adress _Adress) {
        this._Adress = _Adress;
    }
}
