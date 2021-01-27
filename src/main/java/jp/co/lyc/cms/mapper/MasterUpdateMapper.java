package jp.co.lyc.cms.mapper;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import jp.co.lyc.cms.model.MasterModel;

@Mapper
public interface MasterUpdateMapper {

	/**
	 * 修正
	 * 
	 * @param sendMap
	 */
	public void updateMaster(HashMap<String, Object> sendMap);
	
	/**
	 * 削除
	 * 
	 * @param sendMap
	 */
	public void deleteMaster(HashMap<String, Object> sendMap);

	/**
	 * 削除後のデータ更新
	 * 
	 * @param sendMap
	 */
	public void masterUpdateAfterDelete(HashMap<String, Object> sendMap);
	
	/**
	 * 検索
	 * 
	 * @param sendMap
	 * @return
	 */
	public List<MasterModel> getMasterInfo(HashMap<String, String> sendMap);
	
	/**
	 * 有無判断
	 * @param data
	 * @return
	 */
	public String checkHave(MasterModel masterModel);

}
