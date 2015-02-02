package com.mygoconsulting.mytracking.model;

import java.util.List;

public class IMY_MGOL_INV_HEADER
{
    private String ORDER_STATUS_CD;

    private String INVOI_NBR;

    private String SEGMENT;

    private String SOLD_FROM_COMPANY_CD;

    private String ORDER_PLANT_CD;
    
    private String SHIP_TO_COMPANY_CD;
    
    private String CUSTOMER_PO;

    private String END_USER_COMPANY_CD;
    
    private String OVERRIDE_COMPANY_NAME;    

    private String OVERRIDE_CITY;
    
    private String OVERRIDE_STATE;

    private String OVERRIDE_ADDRESS1;

    private String OVERRIDE_ADDRESS2;

    private String OVERRIDE_ZIP;

    private List<IMY_MGOL_INV_HEADER_COMMEN> IMY_MGOL_INV_HEADER_COMMEN;

    private String SOLD_TO_COMPANY_CD;

    public String getORDER_STATUS_CD ()
    {
        return ORDER_STATUS_CD;
    }

    public void setORDER_STATUS_CD (String ORDER_STATUS_CD)
    {
        this.ORDER_STATUS_CD = ORDER_STATUS_CD;
    }

    public String getINVOI_NBR ()
    {
        return INVOI_NBR;
    }

    public void setINVOI_NBR (String INVOI_NBR)
    {
        this.INVOI_NBR = INVOI_NBR;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getSOLD_FROM_COMPANY_CD ()
    {
        return SOLD_FROM_COMPANY_CD;
    }

    public void setSOLD_FROM_COMPANY_CD (String SOLD_FROM_COMPANY_CD)
    {
        this.SOLD_FROM_COMPANY_CD = SOLD_FROM_COMPANY_CD;
    }

    public String getORDER_PLANT_CD ()
    {
        return ORDER_PLANT_CD;
    }

    public void setORDER_PLANT_CD (String ORDER_PLANT_CD)
    {
        this.ORDER_PLANT_CD = ORDER_PLANT_CD;
    }

    public List<IMY_MGOL_INV_HEADER_COMMEN> getIMY_MGOL_INV_HEADER_COMMEN ()
    {
        return IMY_MGOL_INV_HEADER_COMMEN;
    }

    public void setIMY_MGOL_INV_HEADER_COMMEN (List<IMY_MGOL_INV_HEADER_COMMEN> IMY_MGOL_INV_HEADER_COMMEN)
    {
        this.IMY_MGOL_INV_HEADER_COMMEN = IMY_MGOL_INV_HEADER_COMMEN;
    }

    public String getSOLD_TO_COMPANY_CD ()
    {
        return SOLD_TO_COMPANY_CD;
    }

    public void setSOLD_TO_COMPANY_CD (String SOLD_TO_COMPANY_CD)
    {
        this.SOLD_TO_COMPANY_CD = SOLD_TO_COMPANY_CD;
    }
    
    public String getCUSTOMER_PO ()
    {
        return CUSTOMER_PO;
    }

    public void setCUSTOMER_PO (String CUSTOMER_PO)
    {
        this.CUSTOMER_PO = CUSTOMER_PO;
    }

    public String getEND_USER_COMPANY_CD ()
    {
        return END_USER_COMPANY_CD;
    }

    public void setEND_USER_COMPANY_CD (String END_USER_COMPANY_CD)
    {
        this.END_USER_COMPANY_CD = END_USER_COMPANY_CD;
    }

    public String getOVERRIDE_COMPANY_NAME ()
    {
        return OVERRIDE_COMPANY_NAME;
    }

    public void setOVERRIDE_COMPANY_NAME (String OVERRIDE_COMPANY_NAME)
    {
        this.OVERRIDE_COMPANY_NAME = OVERRIDE_COMPANY_NAME;
    }

    public String getOVERRIDE_STATE ()
    {
        return OVERRIDE_STATE;
    }

    public void setOVERRIDE_STATE (String OVERRIDE_STATE)
    {
        this.OVERRIDE_STATE = OVERRIDE_STATE;
    }
    
    public String getOVERRIDE_CITY ()
    {
        return OVERRIDE_CITY;
    }

    public void setOVERRIDE_CITY (String OVERRIDE_CITY)
    {
        this.OVERRIDE_CITY = OVERRIDE_CITY;
    }

    public String getOVERRIDE_ADDRESS1 ()
    {
        return OVERRIDE_ADDRESS1;
    }

    public void setOVERRIDE_ADDRESS1 (String OVERRIDE_ADDRESS1)
    {
        this.OVERRIDE_ADDRESS1 = OVERRIDE_ADDRESS1;
    }

    public String getOVERRIDE_ADDRESS2 ()
    {
        return OVERRIDE_ADDRESS2;
    }

    public void setOVERRIDE_ADDRESS2 (String OVERRIDE_ADDRESS2)
    {
        this.OVERRIDE_ADDRESS2 = OVERRIDE_ADDRESS2;
    }
    
    public String getOVERRIDE_ZIP ()
    {
        return OVERRIDE_ZIP;
    }

    public void setOVERRIDE_ZIP (String OVERRIDE_ZIP)
    {
        this.OVERRIDE_ZIP = OVERRIDE_ZIP;
    }
    
    public String getSHIP_TO_COMPANY_CD ()
    {
        return SHIP_TO_COMPANY_CD;
    }

    public void setSHIP_TO_COMPANY_CD (String SHIP_TO_COMPANY_CD)
    {
        this.SHIP_TO_COMPANY_CD = SHIP_TO_COMPANY_CD;
    }
}
