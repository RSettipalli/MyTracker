package com.mygoconsulting.mytracking.model;

public class IMY_MGOL_SO_DETAIL_COMMENT
{
    private String ORDER_NBR;

    private String SEGMENT;

    private String ORDER_LINE_NBR;

    private String LINE;

    private String TYPE;
    
    private String ORDER_NBR_SO_DETAIL;
    
    private String ORDER_LINE_NBR_SO_DETAIL;

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

	public String getORDER_NBR_SO_DETAIL() {
		return ORDER_NBR_SO_DETAIL;
	}

	public void setORDER_NBR_SO_DETAIL(String oRDER_NBR_SO_DETAIL) {
		ORDER_NBR_SO_DETAIL = oRDER_NBR_SO_DETAIL;
	}

	public String getORDER_LINE_NBR_SO_DETAIL() {
		return ORDER_LINE_NBR_SO_DETAIL;
	}

	public void setORDER_LINE_NBR_SO_DETAIL(String oRDER_LINE_NBR_SO_DETAIL) {
		ORDER_LINE_NBR_SO_DETAIL = oRDER_LINE_NBR_SO_DETAIL;
	}
}