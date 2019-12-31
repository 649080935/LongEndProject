package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.HotelDao;
import cn.ekgc.ltrip.dao.LabelDicDao;
import cn.ekgc.ltrip.pojo.entity.Hotel;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.ltrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private LabelDicDao labelDicDao;

	public Hotel queryHotelVideoDescByHotelId(Long hotelId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", hotelId);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);
		if (hotelList != null && hotelList.size() > 0) {
			return hotelList.get(0);
		}
		return null;
	}

	public List<SearchDetailsHotelVO> queryHotelDetails(Long hotelId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", hotelId);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);
		// 查询酒店特色列表
		List<LabelDic> labelDicList = labelDicDao.getHotelFeatureByHotelId(hotelId);

		List<SearchDetailsHotelVO> detailsHotelVOList = new ArrayList<>();
		if (hotelList != null && hotelList.size() > 0) {
			Hotel hotel = hotelList.get(0);
			detailsHotelVOList.add(new SearchDetailsHotelVO("酒店介绍", hotel.getDetails()));
			for (LabelDic labelDic : labelDicList) {
				detailsHotelVOList.add(new SearchDetailsHotelVO(labelDic.getName(), labelDic.getDescription()));
			}
		}
		return detailsHotelVOList;
	}

	public String queryHotelFacilities(Long hotelId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", hotelId);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);

		if (hotelList != null && hotelList.size() > 0) {
			Hotel hotel = hotelList.get(0);
			return hotel.getFacilities();
		}
		return "";
	}

	public String queryHotelPolicy(Long hotelId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("id", hotelId);
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(queryMap);

		if (hotelList != null && hotelList.size() > 0) {
			Hotel hotel = hotelList.get(0);
			return hotel.getHotelPolicy();
		}
		return "";
	}
}
