package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.entity.HotelRoom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HotelRoomDao {

	List<HotelRoom> findHotelRoomListByQuery(Map<String, Object> queryMap) throws Exception;

	List<HotelImage> findHotelImageListByQuery(Map<String, Object> queryMap) throws Exception;

	Integer findTotalRoomStore(Long roomId) throws Exception;
}
