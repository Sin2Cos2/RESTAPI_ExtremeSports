package com.attaproject.requestForm;

import com.attaproject.responseForm.LocationSportResponseList;

import java.sql.Date;
import java.util.List;

public class SearchForm {

    private Date startDate;
    private Date endDate;
    List<String> sports;

    public SearchForm() {
    }

    public SearchForm(Date startDate, Date endDate, List<String> sports) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sports = sports;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getSports() {
        return sports;
    }

    public void setSports(List<String> sports) {
        this.sports = sports;
    }
}
