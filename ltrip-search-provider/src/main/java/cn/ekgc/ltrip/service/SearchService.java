package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.vo.ItripHotelVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;

import java.util.List;

public interface SearchService {

	List<ItripHotelVO> searchItripHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception;
}
