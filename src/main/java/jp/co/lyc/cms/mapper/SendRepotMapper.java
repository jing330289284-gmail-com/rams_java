package jp.co.lyc.cms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import jp.co.lyc.cms.model.SendRepotModel;
import jp.co.lyc.cms.model.SendRepotsListName;

@Mapper
public interface SendRepotMapper {

	/**
	 * ログイン
	 * @param sendMap
	 * @return
	 */
	public List<SendRepotModel> getSalesCustomers();
	public List<SendRepotModel> getSalesPersons(String customerNo);
	public int creatList(SendRepotModel model);
	public List<SendRepotModel> getLists();
	public int listNameUpdate(SendRepotsListName model);
	public List<SendRepotModel> getSalesCustomersByNos(String[] ctmNos);
	public int deleteList(String storageListName);
}
