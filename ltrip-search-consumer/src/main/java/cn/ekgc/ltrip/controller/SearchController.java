package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.pojo.vo.HotelVO;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ltrip.search.transport.SearchTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("searchController")
@RequestMapping("/search/api")
public class SearchController extends BaseController {

	@Autowired
	private SearchTransport searchTransport;

	@RequestMapping(value = "/hotellist/searchItripHotelListByHotCity", method = RequestMethod.POST)
	public ResponseResult<Object> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO)
			throws Exception {
		// 校验所传递的数据是否有效
		if (searchHotCityVO.getCityId() != null && searchHotCityVO.getCityId() > 0
				&& searchHotCityVO.getCount() != null && searchHotCityVO.getCount() > 0) {
			List<HotelVO> itripHotelVOList = searchTransport.searchItripHotelListByHotCity(searchHotCityVO);
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, itripHotelVOList);
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写有效信息");
		}
	}

}
