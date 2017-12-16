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
		
		String sqlPdSearch = "SELECT t.patient_id,    t.reg_date,     array_to_string(array_agg(t.pulse::text),'') as pulse,     array_to_string(array_agg(t.height::text),'') as Height,     array_to_string(array_agg(t.weight::text) ,'')as weight,     array_to_string(array_agg(t.Temprature::text),'') as Temprature,     array_to_string(array_agg(t.bp::text),'') as bp,     array_to_string(array_agg(t.Resp::text),'') as Resp,    t.date_time,    t.mr_no,    t.bed_name,    t.patient_name from (SELECT  vv.patient_id, pr.reg_date, case when vpm.param_label='Pulse' then vr.param_value::text  end as pulse, case when vpm.param_label='Height' then vr.param_value::text  end as height, case when vpm.param_label= 'Weight' then vr.param_value::text  end as WEIGHT, case when vpm.param_label= 'Resp' then vr.param_value::text  end as  Resp, case when vpm.param_label= 'Temp' then vr.param_value::text  end as Temprature,  case when vpm.param_label= 'B.P' then vr.param_value::text  end as bp, vpm.param_uom,              vv.vital_reading_id, vr.param_id, date_time,pd.mr_no,bed_name, (((sm.salutation::text || ' '::text) || pd.patient_name::text) ||        CASE            WHEN COALESCE(pd.middle_name, ''::character varying)::text = ''::text THEN ''::text            ELSE ' '::text || pd.middle_name::text        END) ||        CASE            WHEN COALESCE(pd.last_name, ''::character varying)::text = ''::text THEN ''::text            ELSE ' '::text || pd.last_name::text        END AS patient_name             FROM vital_reading vr              JOIN visit_vitals vv USING(vital_reading_id)                JOIN vital_parameter_master2 vpm USING(param_id)                JOIN patient_registration pr ON (pr.patient_id=vv.patient_id)                 JOIN patient_details pd on pd.mr_no=pr.mr_no                left join bed_status_report bsr on bsr.patient_id=pr.patient_id                LEFT JOIN salutation_master sm ON pd.salutation::text = sm.salutation_id::text          WHERE vpm.param_status='A' and vv.patient_id=?) as t group by     t.date_time,    t.mr_no,    t.bed_name,    t.patient_name,t.patient_id,t.reg_date";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlPdSearch);
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
