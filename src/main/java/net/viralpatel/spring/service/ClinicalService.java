package net.viralpatel.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.viralpatel.spring.dao.ClinicalDao;

@Component
public class ClinicalService {

	@Autowired private ClinicalDao clinicalDao;
	
	public List getPatientDetails(String mrNo) {
		System.out.println("mrNo :"+mrNo);
		List list = clinicalDao.getPatientDetails(mrNo);
		return list;
	}
}
