package cn.ekgc.ltrip.service;


import cn.ekgc.ltrip.pojo.entity.Hotel;
import cn.ekgc.ltrip.pojo.vo.SearchDetailsHotelVO;

import java.util.List;

public interface HotelService {

	Hotel queryHotelVideoDescByHotelId(Long hotelId) throws Exception;

	List<SearchDetailsHotelVO> queryHotelDetails(Long hotelId) throws Exception;

	String queryHotelFacilities(Long hotelId) throws Exception;

	String queryHotelPolicy(Long hotelId) throws Exception;
}
