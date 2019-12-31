package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HotelDao {

	List<Hotel> findHotelListByQuery(Map<String, Object> queryMap) throws Exception;

}
