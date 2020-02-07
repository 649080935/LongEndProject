package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.entity.OrderLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HotelOrderDao {

	Integer findStoreByPreOrder(Map<String, Object> queryMap) throws Exception;

	Integer findCountByOrderNoPay(Map<String, Object> queryMap) throws Exception;

	void saveOrder(HotelOrder hotelOrder) throws Exception;

	List<HotelOrder> findOrderByQuery(Map<String, Object> queryMap) throws Exception;

	List<OrderLinkUser> findOrderLinkUserListByQuery(Map<String, Object> queryMap) throws Exception;

	List<HotelOrder> findHotelOrderListByQuery(Map<String, Object> queryMap) throws Exception;
}
