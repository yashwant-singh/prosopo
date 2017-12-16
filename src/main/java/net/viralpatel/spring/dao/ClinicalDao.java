package net.viralpatel.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.springframework.stereotype.Repository;

import net.viralpatel.spring.common.DBUtil;

@Repository
public class ClinicalDao {

	@SuppressWarnings("rawtypes")
	public List getPatientDetails(String mrNo) {
		Connection con = DBUtil.getConnection();
		String sql = "SELECT vv.patient_id, pr.reg_date, vpm.param_label, vpm.param_uom, "
				+ " vv.vital_reading_id, vr.param_id, vr.param_value, date_time,pd.mr_no,bed_name,"
				+ " (((sm.salutation::text || ' '::text) || pd.patient_name::text) || "
				+ "CASE WHEN COALESCE(pd.middle_name, ''::character varying)::text = ''::text THEN ''::text "
				+ "ELSE ' '::text || pd.middle_name::text END) ||" 
				+ "CASE WHEN COALESCE(pd.last_name, ''::character varying)::text = ''::text THEN ''::text "
				+ "ELSE ' '::text || pd.last_name::text END AS patient_name "
				+ "FROM vital_reading vr "
                + "JOIN visit_vitals vv USING(vital_reading_id) " 
                + "JOIN vital_parameter_master vpm USING(param_id) " 
                + "JOIN patient_registration pr ON (pr.patient_id=vv.patient_id) " 
                + "JOIN patient_details pd on pd.mr_no=pr.mr_no "
                + "left join bed_status_report bsr on bsr.patient_id=pr.patient_id "
                + "LEFT JOIN salutation_master sm ON pd.salutation::text = sm.salutation_id::text "
                + "WHERE vpm.param_status='A' and vv.patient_id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mrNo);
			ResultSet rs = pstmt.executeQuery();
			RowSetDynaClass rowSetDynaClass = new RowSetDynaClass(rs);
			return rowSetDynaClass.getRows();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
