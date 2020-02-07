package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.pojo.vo.HotelVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelVO;
import cn.ekgc.ltrip.search.transport.SearchTransport;
import cn.ekgc.ltrip.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("searchTransport")
@RequestMapping("/search")
public class SearchTransportImpl implements SearchTransport {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/searchItripHotelListByHotCity", method = RequestMethod.POST)
	public List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO) throws Exception {
		return searchService.searchItripHotelListByHotCity(searchHotCityVO);
	}

	@RequestMapping(value = "/hotellist/searchItripHotelPage", method = RequestMethod.POST)
	public List<SearchHotelVO> searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO) throws Exception {
		return searchService.searchItripHotelPage(searchHotelVO);
	}
}
