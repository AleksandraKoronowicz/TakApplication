package com.application.tak.takapplication.data_model;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by azielinska on 11/05/2017.
 */
public class Task
{
    private Integer _Id;
    private Integer _CategoryId;
    private Integer _StatusId;
    private Date _TimeFrom;
    private Date _TimeTo;
    private Integer _CreatorId;
    private Date _CreationTime;
    private Integer _ExecutorId;
    private Date _ExecutionTime;
    private Integer _IsApproved;

    public Integer get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public Integer get_CategoryId() {
        return _CategoryId;
    }

    public void set_CategoryId(int _CategoryId) {
        this._CategoryId = _CategoryId;
    }

    public Integer get_StatusId() {
        return _StatusId;
    }

    public void set_StatusId(int _StatusId) {
        this._StatusId = _StatusId;
    }

    public Date get_TimeFrom() {
        return _TimeFrom;
    }

    public void set_TimeFrom(Date _TimeFrom) {
        this._TimeFrom = _TimeFrom;
    }

    public Date get_TimeTo() {
        return _TimeTo;
    }

    public void set_TimeTo(Date _TimeTo) {
        this._TimeTo = _TimeTo;
    }

    public Integer get_CreatorId() {
        return _CreatorId;
    }

    public void set_CreatorId(int _CreatorId) {
        this._CreatorId = _CreatorId;
    }

    public Date get_CreationTime() {
        return _CreationTime;
    }

    public void set_CreationTime(Date _CreationTime) {
        this._CreationTime = _CreationTime;
    }

    public Integer get_ExecutorId() {
        return _ExecutorId;
    }

    public void set_ExecutorId(Integer _ExecutorId) {
        this._ExecutorId = _ExecutorId;
    }

    public Date get_ExecutionTime() {
        return _ExecutionTime;
    }

    public void set_ExecutionTime(Date _ExecutionTime) {
        this._ExecutionTime = _ExecutionTime;
    }

    public Integer get_IsApproved() {
        return _IsApproved;
    }

    public void set_IsApproved(int _IsApproved) {
        this._IsApproved = _IsApproved;
    }
}
