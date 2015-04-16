package com.mygoconsulting.mytracking.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mygoconsulting.mytracking.model.EDI_DC40;

@Component("EDIDC40Mapper")
public class EDIDC40RowMapper implements RowMapper<EDI_DC40> {

	@Override
	public EDI_DC40 mapRow(ResultSet rs, int rowNum) throws SQLException {
		EDI_DC40 ediDC40 = new EDI_DC40();
		ediDC40.setTABNAM(rs.getString("TABNAM"));
		ediDC40.setMANDT(rs.getString("MANDT"));
		ediDC40.setDOCNUM(rs.getString("DOCNUM"));
		ediDC40.setDOCREL(rs.getString("DOCREL"));
		ediDC40.setSTATUS(rs.getString("STATUS"));
		ediDC40.setDIRECT(rs.getString("DIRECT"));
		ediDC40.setOUTMOD(rs.getString("OUTMOD"));
		ediDC40.setIDOCTYP(rs.getString("IDOCTYP"));
		ediDC40.setMESTYP(rs.getString("MESTYP"));
		ediDC40.setSNDPOR(rs.getString("SNDPOR"));
		ediDC40.setSNDPRT(rs.getString("SNDPRT"));
		ediDC40.setSNDPRN(rs.getString("SNDPRN"));
		ediDC40.setRCVPOR(rs.getString("RCVPOR"));
		ediDC40.setRCVPRT(rs.getString("RCVPRT"));
		ediDC40.setRCVPRN(rs.getString("RCVPRN"));
		ediDC40.setCREDAT(rs.getString("CREDAT"));
		ediDC40.setCRETIM(rs.getString("CRETIM"));
		ediDC40.setSERIAL(rs.getString("SERIAL"));
		return ediDC40;
	}

}