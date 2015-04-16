package com.mygoconsulting.mytracking.model;

import java.util.List;

public class IMY_MGOL_SO_HEADER
{
    private String ORDER_TYPE;

    private String ORDER_STATUS_CD;

    private List<IMY_MGOL_SO_HEADER_COMMENT> IMY_MGOL_SO_HEADER_COMMENT;

    private String BILL_TO_COMPANY_CD;
    
    private String SHIP_TO_COMPANY_CD;

    private String END_USER;

    private String ORDER_NBR;

    private String SEGMENT;

    private String SOLD_FROM_COMPANY_CD;

    private String ORDER_PLANT_CD;

    private String SOLD_TO_COMPANY_CD;
    
    private String OVERRIDE_CITY;

    private String OVERRIDE_ADDRESS1;

    private String ORDER_NBR_VER;

    private String OVERRIDE_ADDRESS2;
    
    private String OVERRIDE_ZIP;
    
    private String END_USER_COMPANY_CD;

    private String OVERRIDE_COMPANY_NAME;

    private String OVERRIDE_STATE;
    
    private String CUSTOMER_PO;
    
    private String TOTAL_PRICE;
    
    private String CURRENCY;
    
    private String CREATE_DATE;
    
    private List<IMY_MGOL_SO_DETAIL> IMY_MGOL_SO_DETAIL;
    
    private String DOCNUM;
    
    public List<IMY_MGOL_SO_DETAIL> getIMY_MGOL_SO_DETAIL() {
		return IMY_MGOL_SO_DETAIL;
	}

	public void setIMY_MGOL_SO_DETAIL(List<IMY_MGOL_SO_DETAIL> iMY_MGOL_SO_DETAIL) {
		IMY_MGOL_SO_DETAIL = iMY_MGOL_SO_DETAIL;
	}

	public String getORDER_TYPE ()
    {
        return ORDER_TYPE;
    }

    public void setORDER_TYPE (String ORDER_TYPE)
    {
        this.ORDER_TYPE = ORDER_TYPE;
    }

    public String getORDER_STATUS_CD ()
    {
        return ORDER_STATUS_CD;
    }

    public void setORDER_STATUS_CD (String ORDER_STATUS_CD)
    {
        this.ORDER_STATUS_CD = ORDER_STATUS_CD;
    }

    public List<IMY_MGOL_SO_HEADER_COMMENT> getIMY_MGOL_SO_HEADER_COMMENT ()
    {
        return IMY_MGOL_SO_HEADER_COMMENT;
    }

    public void setIMY_MGOL_SO_HEADER_COMMENT (List<IMY_MGOL_SO_HEADER_COMMENT> IMY_MGOL_SO_HEADER_COMMENT)
    {
        this.IMY_MGOL_SO_HEADER_COMMENT = IMY_MGOL_SO_HEADER_COMMENT;
    }

    public String getBILL_TO_COMPANY_CD ()
    {
        return BILL_TO_COMPANY_CD;
    }

    public void setBILL_TO_COMPANY_CD (String BILL_TO_COMPANY_CD)
    {
        this.BILL_TO_COMPANY_CD = BILL_TO_COMPANY_CD;
    }

    public String getEND_USER ()
    {
        return END_USER;
    }

    public void setEND_USER (String END_USER)
    {
        this.END_USER = END_USER;
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

    public String getORDER_NBR_VER ()
    {
        return ORDER_NBR_VER;
    }

    public void setORDER_NBR_VER (String ORDER_NBR_VER)
    {
        this.ORDER_NBR_VER = ORDER_NBR_VER;
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

	public String getDOCNUM() {
		return DOCNUM;
	}

	public void setDOCNUM(String dOCNUM) {
		DOCNUM = dOCNUM;
	}

	public String getSHIP_TO_COMPANY_CD() {
		return SHIP_TO_COMPANY_CD;
	}

	public void setSHIP_TO_COMPANY_CD(String sHIP_TO_COMPANY_CD) {
		SHIP_TO_COMPANY_CD = sHIP_TO_COMPANY_CD;
	}

	public String getTOTAL_PRICE() {
		return TOTAL_PRICE;
	}

	public void setTOTAL_PRICE(String tOTAL_PRICE) {
		TOTAL_PRICE = tOTAL_PRICE;
	}

	public String getCURRENCY() {
		return CURRENCY;
	}

	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}

	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
    
}
