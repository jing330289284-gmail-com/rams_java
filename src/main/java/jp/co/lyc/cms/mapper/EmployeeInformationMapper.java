package jp.co.lyc.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.lyc.cms.controller.EmployeeInformationController;
import jp.co.lyc.cms.model.EmployeeInformationModel;
import jp.co.lyc.cms.model.SituationChangesModel;

@Mapper
public interface EmployeeInformationMapper {

	public List<EmployeeInformationModel> getEmployeeInformation();

	public void updateEmployeeInformation(List<EmployeeInformationModel> list);

}
