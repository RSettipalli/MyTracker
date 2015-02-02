package com.mygoconsulting.mytracking.model;

import java.util.List;

public class IMY_COMPANY
{
    private String SPRAS;

    private String ORT01;

    private String WAERS;

    private String SEGMENT;

    private String ADRNR;

    private List<IMY_SHIP_POINT> IMY_SHIP_POINT;

    private String BUTXT;

    private String BUKRS;
    
    private String COUNTRY;
    
    private String ADDRESS2;
    
    private String PHONE;

    private String ADDRESS1;
    
    private String FAX;
    
    private String ZIP;

    public String getSPRAS ()
    {
        return SPRAS;
    }

    public void setSPRAS (String SPRAS)
    {
        this.SPRAS = SPRAS;
    }

    public String getORT01 ()
    {
        return ORT01;
    }

    public void setORT01 (String ORT01)
    {
        this.ORT01 = ORT01;
    }

    public String getWAERS ()
    {
        return WAERS;
    }

    public void setWAERS (String WAERS)
    {
        this.WAERS = WAERS;
    }
    
    public String getCOUNTRY ()
    {
        return COUNTRY;
    }

    public void setCOUNTRY (String COUNTRY)
    {
        this.COUNTRY = COUNTRY;
    }
    
    public String getZIP ()
    {
        return ZIP;
    }

    public void setZIP (String ZIP)
    {
        this.ZIP = ZIP;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }
    
    public String getADDRESS2 ()
    {
        return ADDRESS2;
    }

    public void setADDRESS2 (String ADDRESS2)
    {
        this.ADDRESS2 = ADDRESS2;
    }
    
    
    public String getPHONE ()
    {
        return PHONE;
    }

    public void setPHONE (String PHONE)
    {
        this.PHONE = PHONE;
    }
    
    public String getADDRESS1 ()
    {
        return ADDRESS1;
    }

    public void setADDRESS1 (String ADDRESS1)
    {
        this.ADDRESS1 = ADDRESS1;
    }
    
    public String getADRNR ()
    {
        return ADRNR;
    }

    public void setADRNR (String ADRNR)
    {
        this.ADRNR = ADRNR;
    }

    public List<IMY_SHIP_POINT> getIMY_SHIP_POINT ()
    {
        return IMY_SHIP_POINT;
    }

    public void setIMY_SHIP_POINT (List<IMY_SHIP_POINT> IMY_SHIP_POINT)
    {
        this.IMY_SHIP_POINT = IMY_SHIP_POINT;
    }

    public String getBUTXT ()
    {
        return BUTXT;
    }

    public void setBUTXT (String BUTXT)
    {
        this.BUTXT = BUTXT;
    }

    public String getBUKRS ()
    {
        return BUKRS;
    }

    public void setBUKRS (String BUKRS)
    {
        this.BUKRS = BUKRS;
    }
    
    public String getFAX ()
    {
        return FAX;
    }

    public void setFAX (String FAX)
    {
        this.FAX = FAX;
    }
}