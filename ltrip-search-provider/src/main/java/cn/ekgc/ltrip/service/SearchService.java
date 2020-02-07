package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.vo.HotelVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelVO;

import java.util.List;

public interface SearchService {

	List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception;

	List<SearchHotelVO> searchItripHotelPage(SearchHotelVO searchHotelVO) throws Exception;
}
