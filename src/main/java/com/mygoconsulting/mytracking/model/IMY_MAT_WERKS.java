package com.mygoconsulting.mytracking.model;

public class IMY_MAT_WERKS
{
    private IMY_MAT_STORAGE_DETIALS IMY_MAT_STORAGE_DETIALS;

    private String PLANT;

    private String MRP_CONT;

    private String MAINT_STATUS;

    private String SEGMENT;

    private String MRP_TYPE;

    public IMY_MAT_STORAGE_DETIALS getIMY_MAT_STORAGE_DETIALS ()
    {
        return IMY_MAT_STORAGE_DETIALS;
    }

    public void setIMY_MAT_STORAGE_DETIALS (IMY_MAT_STORAGE_DETIALS IMY_MAT_STORAGE_DETIALS)
    {
        this.IMY_MAT_STORAGE_DETIALS = IMY_MAT_STORAGE_DETIALS;
    }

    public String getPLANT ()
    {
        return PLANT;
    }

    public void setPLANT (String PLANT)
    {
        this.PLANT = PLANT;
    }

    public String getMRP_CONT ()
    {
        return MRP_CONT;
    }

    public void setMRP_CONT (String MRP_CONT)
    {
        this.MRP_CONT = MRP_CONT;
    }

    public String getMAINT_STATUS ()
    {
        return MAINT_STATUS;
    }

    public void setMAINT_STATUS (String MAINT_STATUS)
    {
        this.MAINT_STATUS = MAINT_STATUS;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getMRP_TYPE ()
    {
        return MRP_TYPE;
    }

    public void setMRP_TYPE (String MRP_TYPE)
    {
        this.MRP_TYPE = MRP_TYPE;
    }
}