package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AreaDicDao {

	List<AreaDic> findAreaDicListByQuery(Map<String, Object> queryMap)throws Exception;

	List<AreaDic> findTradeAreaByQuery(Map<String, Object> queryMap) throws Exception;
}
