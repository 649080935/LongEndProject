package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.vo.*;

import java.util.List;
import java.util.Map;

public interface HotelOrderService {

	RoomStoreVO getPreOrderInfo(ValidateRoomStoreVO validateRoomStoreVO) throws Exception;

	Map<String, Object> addHotelOrder(HotelOrderAddVO hotelOrderAddVO) throws Exception;

	List<HotelOrder> getPersonalOrderInfo(Long orderId) throws Exception;

	PersonalOrderRoomVO getPersonalOrderRoomInfo(Long orderId) throws Exception;

	ModifyHotelOrderVO queryOrderById(Long orderId) throws Exception;

	HotelOrder getOrderByNo(String orderNo) throws Exception;

	Page<HotelOrder> getHotelOrderListByPage(SearchOrderVO searchOrderVO) throws Exception;
}
