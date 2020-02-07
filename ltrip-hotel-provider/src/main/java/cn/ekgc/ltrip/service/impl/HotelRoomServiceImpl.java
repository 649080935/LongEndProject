package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.HotelRoomDao;
import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.entity.HotelRoom;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.ltrip.service.HotelRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hotelRoomService")
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {

	@Autowired
	private HotelRoomDao hotelRoomDao;

	public List<HotelRoomVO> queryHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", searchHotelRoomVO.getHotelId());
		queryMap.put("isBook", searchHotelRoomVO.getIsBook());
		queryMap.put("isHavingBreakfast", searchHotelRoomVO.getIsHavingBreakfast());
		queryMap.put("isTimelyResponse", searchHotelRoomVO.getIsTimelyResponse());
		queryMap.put("roomBedTypeId", searchHotelRoomVO.getRoomBedTypeId());
		queryMap.put("startDate", searchHotelRoomVO.getStartDate());
		queryMap.put("endDate", searchHotelRoomVO.getEndDate());
		queryMap.put("isCancel", searchHotelRoomVO.getIsCancel());
		queryMap.put("payType", searchHotelRoomVO.getPayType());

		// 使用数据持久层进行查询
		List<HotelRoom> hotelRoomList = hotelRoomDao.findHotelRoomListByQuery(queryMap);
		// 将实体对象的属性复制到视图对象中
		List<HotelRoomVO> hotelRoomVOList = new ArrayList<>();
		if (hotelRoomList != null && hotelRoomList.size() > 0) {
			for (HotelRoom hotelRoom : hotelRoomList) {
				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				BeanUtils.copyProperties(hotelRoom, hotelRoomVO);
				hotelRoomVOList.add(hotelRoomVO);
			}
		}
		return hotelRoomVOList;
	}

	public List<HotelImage> getImgByTargetIdAndTypeId(Long targetId, String typeId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("targetId", targetId);
		queryMap.put("typeId", typeId);

		List<HotelImage> hotelImageList = hotelRoomDao.findHotelImageListByQuery(queryMap);
		return hotelImageList;
	}
}
