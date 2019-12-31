package cn.ekgc.ltrip.hotel.transport;

import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.pojo.entity.Hotel;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.SearchDetailsHotelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ltrip-hotel-provider")
@RequestMapping("/hotel")
public interface HotelTransport {

	@RequestMapping(value = "/queryhotcity", method = RequestMethod.POST)
	List<AreaDic> getHotCityByIsChina(@RequestParam Integer isChina) throws Exception;

	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	List<LabelDic> getHotelFeature() throws Exception;

	@RequestMapping(value = "/getvideodesc", method = RequestMethod.POST)
	Hotel queryHotelVideoDescByHotelId(@RequestParam Long hotelId) throws Exception;

	@RequestMapping(value = "/queryhoteldetails", method = RequestMethod.POST)
	List<SearchDetailsHotelVO> queryHotelDetails(@RequestParam Long hotelId) throws Exception;

	@RequestMapping(value = "/queryhotelfacilities", method = RequestMethod.POST)
	String queryHotelFacilities(@RequestParam Long hotelId) throws Exception;

	@RequestMapping(value = "/queryhotelpolicy", method = RequestMethod.POST)
	String queryHotelPolicy(@RequestParam Long id) throws Exception;
}
