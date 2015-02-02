package com.mygoconsulting.mytracking.model;

public class IMY_MAT_ONLINE
{
    private String MAT_DESC;

    private String MATERIAL;

    private String NET_WEIGHT;

    private String MAT_TYPE;

    private IMY_MAT_WERKS IMY_MAT_WERKS;

    private String UOM;
    
    private String BOM;
    
    private String STOCK;

    private String SEGMENT;

    private String MATERIAL_GROUP;

    private String GROSS_WEIGHT;

    public String getMAT_DESC ()
    {
        return MAT_DESC;
    }

    public void setMAT_DESC (String MAT_DESC)
    {
        this.MAT_DESC = MAT_DESC;
    }

    public String getMATERIAL ()
    {
        return MATERIAL;
    }

    public void setMATERIAL (String MATERIAL)
    {
        this.MATERIAL = MATERIAL;
    }

    public String getNET_WEIGHT ()
    {
        return NET_WEIGHT;
    }

    public void setNET_WEIGHT (String NET_WEIGHT)
    {
        this.NET_WEIGHT = NET_WEIGHT;
    }
    
    public String getBOM ()
    {
        return BOM;
    }

    public void setBOM (String BOM)
    {
        this.BOM = BOM;
    }
    
    public String getSTOCK ()
    {
        return STOCK;
    }

    public void setSTOCK (String STOCK)
    {
        this.STOCK = STOCK;
    }

    public String getMAT_TYPE ()
    {
        return MAT_TYPE;
    }

    public void setMAT_TYPE (String MAT_TYPE)
    {
        this.MAT_TYPE = MAT_TYPE;
    }

    public IMY_MAT_WERKS getIMY_MAT_WERKS ()
    {
        return IMY_MAT_WERKS;
    }

    public void setIMY_MAT_WERKS (IMY_MAT_WERKS IMY_MAT_WERKS)
    {
        this.IMY_MAT_WERKS = IMY_MAT_WERKS;
    }

    public String getUOM ()
    {
        return UOM;
    }

    public void setUOM (String UOM)
    {
        this.UOM = UOM;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getMATERIAL_GROUP ()
    {
        return MATERIAL_GROUP;
    }

    public void setMATERIAL_GROUP (String MATERIAL_GROUP)
    {
        this.MATERIAL_GROUP = MATERIAL_GROUP;
    }

    public String getGROSS_WEIGHT ()
    {
        return GROSS_WEIGHT;
    }

    public void setGROSS_WEIGHT (String GROSS_WEIGHT)
    {
        this.GROSS_WEIGHT = GROSS_WEIGHT;
    }
}
