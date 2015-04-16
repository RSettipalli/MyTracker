package com.mygoconsulting.mytracking.model;

public class IMY_MGOL_CUSTOMER
{
    private String NAME1;

    private String KUNNR;

    private String COUNTRY;

    private String POSTL_COD1;

    private String SEGMENT;

    private String CITY;

    private String STREET;

    private String REGION;

    private String TELEPHONE;

    private String FAX;
    
    private String STR_SUPPL3;

    private IMY_MGOL_CUST_BANK IMY_MGOL_CUST_BANK;
    
    private String DOCNUM;

    public String getNAME1 ()
    {
        return NAME1;
    }

    public void setNAME1 (String NAME1)
    {
        this.NAME1 = NAME1;
    }
    
    public String getSTR_SUPPL3 ()
    {
        return STR_SUPPL3;
    }

    public void setSTR_SUPPL3 (String STR_SUPPL3)
    {
        this.STR_SUPPL3 = STR_SUPPL3;
    }

    public String getKUNNR ()
    {
        return KUNNR;
    }

    public void setKUNNR (String KUNNR)
    {
        this.KUNNR = KUNNR;
    }

    public String getCOUNTRY ()
    {
        return COUNTRY;
    }

    public void setCOUNTRY (String COUNTRY)
    {
        this.COUNTRY = COUNTRY;
    }

    public String getPOSTL_COD1 ()
    {
        return POSTL_COD1;
    }

    public void setPOSTL_COD1 (String POSTL_COD1)
    {
        this.POSTL_COD1 = POSTL_COD1;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getCITY ()
    {
        return CITY;
    }

    public void setCITY (String CITY)
    {
        this.CITY = CITY;
    }

    public String getSTREET ()
    {
        return STREET;
    }

    public void setSTREET (String STREET)
    {
        this.STREET = STREET;
    }

    public String getREGION ()
    {
        return REGION;
    }

    public void setREGION (String REGION)
    {
        this.REGION = REGION;
    }

    public String getTELEPHONE ()
    {
        return TELEPHONE;
    }

    public void setTELEPHONE (String TELEPHONE)
    {
        this.TELEPHONE = TELEPHONE;
    }

    public String getFAX ()
    {
        return FAX;
    }

    public void setFAX (String FAX)
    {
        this.FAX = FAX;
    }

    public IMY_MGOL_CUST_BANK getIMY_MGOL_CUST_BANK ()
    {
        return IMY_MGOL_CUST_BANK;
    }

    public void setIMY_MGOL_CUST_BANK (IMY_MGOL_CUST_BANK IMY_MGOL_CUST_BANK)
    {
        this.IMY_MGOL_CUST_BANK = IMY_MGOL_CUST_BANK;
    }

	public String getDOCNUM() {
		return DOCNUM;
	}

	public void setDOCNUM(String dOCNUM) {
		DOCNUM = dOCNUM;
	}
}
