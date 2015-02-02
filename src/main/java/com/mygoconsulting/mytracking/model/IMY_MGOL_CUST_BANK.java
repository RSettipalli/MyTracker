package com.mygoconsulting.mytracking.model;

public class IMY_MGOL_CUST_BANK
{
    private String CUST_NUMBER;

    private String SEGMENT;

    private String BANK_KEY;

    private String BANK_TYPE;

    private String BANK_ACC;

    private String BANK_COUNTRY;

    public String getCUST_NUMBER ()
    {
        return CUST_NUMBER;
    }

    public void setCUST_NUMBER (String CUST_NUMBER)
    {
        this.CUST_NUMBER = CUST_NUMBER;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getBANK_KEY ()
    {
        return BANK_KEY;
    }

    public void setBANK_KEY (String BANK_KEY)
    {
        this.BANK_KEY = BANK_KEY;
    }

    public String getBANK_TYPE ()
    {
        return BANK_TYPE;
    }

    public void setBANK_TYPE (String BANK_TYPE)
    {
        this.BANK_TYPE = BANK_TYPE;
    }

    public String getBANK_ACC ()
    {
        return BANK_ACC;
    }

    public void setBANK_ACC (String BANK_ACC)
    {
        this.BANK_ACC = BANK_ACC;
    }

    public String getBANK_COUNTRY ()
    {
        return BANK_COUNTRY;
    }

    public void setBANK_COUNTRY (String BANK_COUNTRY)
    {
        this.BANK_COUNTRY = BANK_COUNTRY;
    }
}