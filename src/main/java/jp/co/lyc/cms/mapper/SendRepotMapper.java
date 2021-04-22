package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.model.SendRepotModel;
import jp.co.lyc.cms.model.SendRepotsListName;

@Mapper
public interface SendRepotMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	public List<SendRepotModel> getCustomers();
	public List<SendRepotModel> getTargetEmployees(String customerNo);
	public int creatList(SendRepotModel model);
	public SendRepotModel getListByName(SendRepotModel model);
	public List<SendRepotModel> getLists();
	public int listNameUpdate(SendRepotsListName model);
	public List<SendRepotModel> getCustomersByNos(String[] ctmNos);
	public int deleteList(String storageListName);
	public int deleteListOfEmp(String storageListName);
	public SendRepotModel getCandidateInChargeList(SendRepotModel model);
	public List<ModelClass> getPurchasingManagersCode(SendRepotModel model);
	public List<ModelClass> getCustomerDepartmentCode(SendRepotModel model);
	public List<ModelClass> getSalesPersonsLists();
	public List<ModelClass> salesPersonsListsUpdate(SendRepotsListName model);
	public List<ModelClass> targetEmployeeListsUpdate(SendRepotModel sendRepotModel);
	public void deleteCustomerListByNo(SendRepotModel model);
	public int deleteCustomerList(SendRepotModel model);
	public void customerListUpdate(String storageListName, String customerList);
	public String getCustomerList(String storageListName);
	public String getMaxStorageListName();
	public SendRepotModel getMainChargeList(String storageListName);
	public void customerSendMailStorageListUpdate(String storageListName, String mainChargeList,String departmentCodeList);
}
