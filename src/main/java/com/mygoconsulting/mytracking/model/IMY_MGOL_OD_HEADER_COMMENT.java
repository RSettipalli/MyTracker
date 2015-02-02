package com.mygoconsulting.mytracking.model;

public class IMY_MGOL_OD_HEADER_COMMENT
{
    private String SEGMENT;

    private String ORDER_NBR;

    private String LINE;

    private String TYPE;
    
    private String DELVI_NBR_OD_HEADER;

    public String getSEGMENT ()
    {
        return SEGMENT;
    }

    public void setSEGMENT (String SEGMENT)
    {
        this.SEGMENT = SEGMENT;
    }

    public String getORDER_NBR ()
    {
        return ORDER_NBR;
    }

    public void setORDER_NBR (String ORDER_NBR)
    {
        this.ORDER_NBR = ORDER_NBR;
    }

    public String getLINE ()
    {
        return LINE;
    }

    public void setLINE (String LINE)
    {
        this.LINE = LINE;
    }

    public String getTYPE ()
    {
        return TYPE;
    }

    public void setTYPE (String TYPE)
    {
        this.TYPE = TYPE;
    }

	public String getDELVI_NBR_OD_HEADER() {
		return DELVI_NBR_OD_HEADER;
	}

	public void setDELVI_NBR_OD_HEADER(String dELVI_NBR_OD_HEADER) {
		DELVI_NBR_OD_HEADER = dELVI_NBR_OD_HEADER;
	}
}