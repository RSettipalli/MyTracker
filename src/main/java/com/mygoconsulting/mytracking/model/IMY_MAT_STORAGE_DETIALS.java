package com.mygoconsulting.mytracking.model;

public class IMY_MAT_STORAGE_DETIALS
{
    private String PLANT;

    private String MATERIAL;

    private String MAINT_STATUS;

    private String SEGMENT;

    private String STO_LOCATION;

    private String STOC_IN_QLTY_INS;

    public String getPLANT ()
    {
        return PLANT;
    }

    public void setPLANT (String PLANT)
    {
        this.PLANT = PLANT;
    }

    public String getMATERIAL ()
    {
        return MATERIAL;
    }

    public void setMATERIAL (String MATERIAL)
    {
        this.MATERIAL = MATERIAL;
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

    public String getSTO_LOCATION ()
    {
        return STO_LOCATION;
    }

    public void setSTO_LOCATION (String STO_LOCATION)
    {
        this.STO_LOCATION = STO_LOCATION;
    }

    public String getSTOC_IN_QLTY_INS ()
    {
        return STOC_IN_QLTY_INS;
    }

    public void setSTOC_IN_QLTY_INS (String STOC_IN_QLTY_INS)
    {
        this.STOC_IN_QLTY_INS = STOC_IN_QLTY_INS;
    }
}