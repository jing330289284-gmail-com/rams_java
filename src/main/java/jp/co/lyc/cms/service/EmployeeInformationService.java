package jp.co.lyc.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.controller.EmployeeInformationController;
import jp.co.lyc.cms.mapper.EmployeeInformationMapper;
import jp.co.lyc.cms.mapper.SituationChangesMapper;
import jp.co.lyc.cms.model.EmployeeInformationModel;
import jp.co.lyc.cms.model.SituationChangesModel;

@Component
public class EmployeeInformationService {

	@Autowired
	EmployeeInformationMapper employeeInformationMapper;

	public List<EmployeeInformationModel> getEmployeeInformation() {
		return employeeInformationMapper.getEmployeeInformation();
	}
}
