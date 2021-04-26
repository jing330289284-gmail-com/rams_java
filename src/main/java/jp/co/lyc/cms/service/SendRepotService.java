package jp.co.lyc.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.lyc.cms.model.SendRepotsListName;
import jp.co.lyc.cms.mapper.SendRepotMapper;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.model.SendRepotModel;


@Component
public class SendRepotService {

	@Autowired
	SendRepotMapper sendRepotMapper;
	
	public List<SendRepotModel> getCustomers(){
		return sendRepotMapper.getCustomers();
	};
	
	public SendRepotModel getCandidateInChargeList(String model){
		return sendRepotMapper.getCandidateInChargeList(model);
	};
	public List<SendRepotModel> getTargetEmployees(String customerNo){
		return sendRepotMapper.getTargetEmployees(customerNo);
	};
	
	public int creatList(SendRepotModel model){
		return sendRepotMapper.creatList(model);
	};
	
	public List<SendRepotModel> getLists(){
		return sendRepotMapper.getLists();
	};
	
	public int listNameUpdate(SendRepotsListName model){
		return sendRepotMapper.listNameUpdate(model);
	};
	
	public List<SendRepotModel> getCustomersByNos(String[] ctmNos){
		return sendRepotMapper.getCustomersByNos(ctmNos);
	};
	
	public int deleteList(String storageListName){
		sendRepotMapper.deleteListOfEmp(storageListName);
		return sendRepotMapper.deleteList(storageListName);
	};
	public  List<ModelClass> getPurchasingManagersCode(SendRepotModel model){
		return sendRepotMapper.getPurchasingManagersCode(model);
	};
	public  List<ModelClass> getCustomerDepartmentCode(SendRepotModel model){
		return sendRepotMapper.getCustomerDepartmentCode(model);
	}
	public  List<ModelClass> getSalesPersonsLists(){
		return sendRepotMapper.getSalesPersonsLists();
	}
	public SendRepotModel getListByName(SendRepotModel model) {
		return sendRepotMapper.getListByName(model);
	}
	public List<ModelClass> salesPersonsListsUpdate(SendRepotsListName updateModel) {
		return sendRepotMapper.salesPersonsListsUpdate(updateModel);
	}
	
	public void targetEmployeeListsUpdate(SendRepotModel sendRepotModel) {
		sendRepotMapper.targetEmployeeListsUpdate(sendRepotModel);
	}
	public void customerListUpdate(String storageListName, String customerList) {
		sendRepotMapper.customerListUpdate(storageListName,customerList);
	}
	public String getCustomerList(String storageListName) {
		return sendRepotMapper.getCustomerList(storageListName);
	}
	public int deleteCustomerList(SendRepotModel model){
		return sendRepotMapper.deleteCustomerList(model);
	};
	public String getMaxStorageListName() {
		return sendRepotMapper.getMaxStorageListName();
	}
	public SendRepotModel getMainChargeList(String storageListName) {
		return sendRepotMapper.getMainChargeList(storageListName);
	}
	public void deleteCustomerListByNo(SendRepotModel model) {
		sendRepotMapper.deleteCustomerListByNo(model);
	}
}
