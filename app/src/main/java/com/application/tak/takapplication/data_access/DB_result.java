package com.application.tak.takapplication.data_access;

/**
 * Created by azielinska on 02/07/2017.
 */
public class DB_result {

    private String MSG;
    private Integer RESULT;
    private Integer LAST_INSERT_ID;

    public Integer getRESULT() {
        return RESULT;
    }

    public void setRESULT(Integer RESULT) {
        this.RESULT = RESULT;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public Integer getLAST_INSERT_ID() {
        return LAST_INSERT_ID;
    }

    public void setLAST_INSERT_ID(Integer LAST_INSERT_ID) {
        this.LAST_INSERT_ID = LAST_INSERT_ID;
    }
}
