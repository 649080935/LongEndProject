package cn.ekgc.ltrip.search.transport;

import cn.ekgc.ltrip.pojo.vo.HotelVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "ltrip-search-provider")
@RequestMapping("/search")
public interface SearchTransport {

	@RequestMapping(value = "/searchItripHotelListByHotCity", method = RequestMethod.POST)
	List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception;
}
