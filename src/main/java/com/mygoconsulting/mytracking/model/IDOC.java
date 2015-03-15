package com.mygoconsulting.mytracking.model;

import java.util.List;

public class IDOC
{
    
	private List<IMY_COMPANY> IMY_COMPANY;;
    
	private List<IMY_MAT_ONLINE> IMY_MAT_ONLINE_List;
    
    private List<IMY_MGOL_CUSTOMER> IMY_MGOL_CUSTOMER;
    
    private IMY_MGOL_SO_HEADER IMY_MGOL_SO_HEADER;
    private List<IMY_MGOL_SO_DETAIL> IMY_MGOL_SO_DETAIL;
    
    private List<IMY_MGOL_OD_DETAIL> IMY_MGOL_OD_DETAIL;
    private IMY_MGOL_OD_HEADER IMY_MGOL_OD_HEADER;
    
    private IMY_MGOL_INV_HEADER IMY_MGOL_INV_HEADER;
    private List<IMY_MGOL_INV_DETAIL> IMY_MGOL_INV_DETAIL;

    private String BEGIN;

    private EDI_DC40 EDI_DC40;

    public List<IMY_COMPANY> getIMY_COMPANY ()
    {
        return IMY_COMPANY;
    }

    public void setIMY_COMPANY (List<IMY_COMPANY> IMY_COMPANY)
    {
        this.IMY_COMPANY = IMY_COMPANY;
    }
    
    public List<IMY_MAT_ONLINE> getIMY_MAT_ONLINE_List() {
		return IMY_MAT_ONLINE_List;
	}

	public void setIMY_MAT_ONLINE_List(List<IMY_MAT_ONLINE> iMY_MAT_ONLINE_List) {
		IMY_MAT_ONLINE_List = iMY_MAT_ONLINE_List;
	}

	public List<IMY_MGOL_CUSTOMER> getIMY_MGOL_CUSTOMER()
    {
        return IMY_MGOL_CUSTOMER;
    }

    public void setIMY_MGOL_CUSTOMER (List<IMY_MGOL_CUSTOMER> IMY_MGOL_CUSTOMER)
    {
        this.IMY_MGOL_CUSTOMER = IMY_MGOL_CUSTOMER;
    }
    
        
    public IMY_MGOL_SO_HEADER getIMY_MGOL_SO_HEADER ()
    {
        return IMY_MGOL_SO_HEADER;
    }

    public void setIMY_MGOL_SO_HEADER (IMY_MGOL_SO_HEADER IMY_MGOL_SO_HEADER)
    {
        this.IMY_MGOL_SO_HEADER = IMY_MGOL_SO_HEADER;
    }
    
    public List<IMY_MGOL_SO_DETAIL> getIMY_MGOL_SO_DETAIL ()
    {
        return IMY_MGOL_SO_DETAIL;
    }

    public void setIMY_MGOL_SO_DETAIL (List<IMY_MGOL_SO_DETAIL> IMY_MGOL_SO_DETAIL)
    {
        this.IMY_MGOL_SO_DETAIL = IMY_MGOL_SO_DETAIL;
    }
    
    public List<IMY_MGOL_OD_DETAIL> getIMY_MGOL_OD_DETAIL ()
    {
        return IMY_MGOL_OD_DETAIL;
    }

    public void setIMY_MGOL_OD_DETAIL (List<IMY_MGOL_OD_DETAIL> IMY_MGOL_OD_DETAIL)
    {
        this.IMY_MGOL_OD_DETAIL = IMY_MGOL_OD_DETAIL;
    }

    public IMY_MGOL_OD_HEADER getIMY_MGOL_OD_HEADER ()
    {
        return IMY_MGOL_OD_HEADER;
    }

    public void setIMY_MGOL_OD_HEADER (IMY_MGOL_OD_HEADER IMY_MGOL_OD_HEADER)
    {
        this.IMY_MGOL_OD_HEADER = IMY_MGOL_OD_HEADER;
    }
    
    public IMY_MGOL_INV_HEADER getIMY_MGOL_INV_HEADER ()
    {
        return IMY_MGOL_INV_HEADER;
    }

    public void setIMY_MGOL_INV_HEADER (IMY_MGOL_INV_HEADER IMY_MGOL_INV_HEADER)
    {
        this.IMY_MGOL_INV_HEADER = IMY_MGOL_INV_HEADER;
    }
    
    public List<IMY_MGOL_INV_DETAIL> getIMY_MGOL_INV_DETAIL ()
    {
        return IMY_MGOL_INV_DETAIL;
    }

    public void setIMY_MGOL_INV_DETAIL (List<IMY_MGOL_INV_DETAIL> IMY_MGOL_INV_DETAIL)
    {
        this.IMY_MGOL_INV_DETAIL = IMY_MGOL_INV_DETAIL;
    }

    public String getBEGIN ()
    {
        return BEGIN;
    }

    public void setBEGIN (String BEGIN)
    {
        this.BEGIN = BEGIN;
    }

    public EDI_DC40 getEDI_DC40 ()
    {
        return EDI_DC40;
    }

    public void setEDI_DC40 (EDI_DC40 EDI_DC40)
    {
        this.EDI_DC40 = EDI_DC40;
    }
}
