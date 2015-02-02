package com.mygoconsulting.mytracking.model;

import java.util.List;

public class IMY_MGOL_INV_DETAIL
{
    private String PRODUCT_NBR;

    private String NET_VAL;

    private String ITEM_CAT;

    private String BASE_UOM;

    private String OVERRIDE_PRODUCT;

    private List<IMY_MGOL_SO_DETAIL_COMMENT> IMY_MGOL_SO_DETAIL_COMMENT;

    private String ORDER_NBR;

    private String SEGMENT;

    private String ORDER_LINE_NBR;
    
    private IMY_MGOL_INV_ITEM_ATMT IMY_MGOL_INV_ITEM_ATMT;

    public String getPRODUCT_NBR ()
    {
        return PRODUCT_NBR;
    }

    public void setPRODUCT_NBR (String PRODUCT_NBR)
    {
        this.PRODUCT_NBR = PRODUCT_NBR;
    }

    public String getNET_VAL ()
    {
        return NET_VAL;
    }

    public void setNET_VAL (String NET_VAL)
    {
        this.NET_VAL = NET_VAL;
    }

    public String getITEM_CAT ()
    {
        return ITEM_CAT;
    }

    public void setITEM_CAT (String ITEM_CAT)
    {
        this.ITEM_CAT = ITEM_CAT;
    }

    public String getBASE_UOM ()
    {
        return BASE_UOM;
    }

    public void setBASE_UOM (String BASE_UOM)
    {
        this.BASE_UOM = BASE_UOM;
    }

    public String getOVERRIDE_PRODUCT ()
    {
        return OVERRIDE_PRODUCT;
    }

    public void setOVERRIDE_PRODUCT (String OVERRIDE_PRODUCT)
    {
        this.OVERRIDE_PRODUCT = OVERRIDE_PRODUCT;
    }

    public List<IMY_MGOL_SO_DETAIL_COMMENT> getIMY_MGOL_SO_DETAIL_COMMENT ()
    {
        return IMY_MGOL_SO_DETAIL_COMMENT;
    }

    public void setIMY_MGOL_SO_DETAIL_COMMENT (List<IMY_MGOL_SO_DETAIL_COMMENT> IMY_MGOL_SO_DETAIL_COMMENT)
    {
        this.IMY_MGOL_SO_DETAIL_COMMENT = IMY_MGOL_SO_DETAIL_COMMENT;
    }

    public String getORDER_NBR ()
    {
        return ORDER_NBR;
    }

    public void setORDER_NBR (String ORDER_NBR)
    {
        this.ORDER_NBR = ORDER_NBR;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getORDER_LINE_NBR ()
    {
        return ORDER_LINE_NBR;
    }

    public void setORDER_LINE_NBR (String ORDER_LINE_NBR)
    {
        this.ORDER_LINE_NBR = ORDER_LINE_NBR;
    }
    
    public IMY_MGOL_INV_ITEM_ATMT getIMY_MGOL_INV_ITEM_ATMT ()
    {
        return IMY_MGOL_INV_ITEM_ATMT;
    }
    
    public void setIMY_MGOL_INV_ITEM_ATMT (IMY_MGOL_INV_ITEM_ATMT IMY_MGOL_INV_ITEM_ATMT)
    {
        this.IMY_MGOL_INV_ITEM_ATMT = IMY_MGOL_INV_ITEM_ATMT;
    }
}
