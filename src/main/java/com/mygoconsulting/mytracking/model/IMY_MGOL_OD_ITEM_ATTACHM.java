package com.mygoconsulting.mytracking.model;

public class IMY_MGOL_OD_ITEM_ATTACHM
{
    private String DOKTL;

    private String DOKVR;

    private String DOKAR;

    private String SEGMENT;

    private String DOKNR;

    private String OBJKY;
    
    private String ORDER_NBR_OD_DETAIL;
    
    private String ORDER_LINE_NBR_OD_DETAIL;

    public String getDOKTL ()
    {
        return DOKTL;
    }

    public void setDOKTL (String DOKTL)
    {
        this.DOKTL = DOKTL;
    }

    public String getDOKVR ()
    {
        return DOKVR;
    }

    public void setDOKVR (String DOKVR)
    {
        this.DOKVR = DOKVR;
    }

    public String getDOKAR ()
    {
        return DOKAR;
    }

    public void setDOKAR (String DOKAR)
    {
        this.DOKAR = DOKAR;
    }

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getDOKNR ()
    {
        return DOKNR;
    }

    public void setDOKNR (String DOKNR)
    {
        this.DOKNR = DOKNR;
    }

    public String getOBJKY ()
    {
        return OBJKY;
    }

    public void setOBJKY (String OBJKY)
    {
        this.OBJKY = OBJKY;
    }

	public String getORDER_NBR_OD_DETAIL() {
		return ORDER_NBR_OD_DETAIL;
	}

	public void setORDER_NBR_OD_DETAIL(String oRDER_NBR_OD_DETAIL) {
		ORDER_NBR_OD_DETAIL = oRDER_NBR_OD_DETAIL;
	}

	public String getORDER_LINE_NBR_OD_DETAIL() {
		return ORDER_LINE_NBR_OD_DETAIL;
	}

	public void setORDER_LINE_NBR_OD_DETAIL(String oRDER_LINE_NBR_OD_DETAIL) {
		ORDER_LINE_NBR_OD_DETAIL = oRDER_LINE_NBR_OD_DETAIL;
	}
}