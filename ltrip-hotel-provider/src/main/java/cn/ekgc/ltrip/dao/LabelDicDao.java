package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.LabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LabelDicDao {

	List<LabelDic> findLabelDicListByQuery(Map<String, Object> queryMap) throws Exception;

	List<LabelDic> getHotelFeatureByHotelId(Long hotelId) throws Exception;
}
