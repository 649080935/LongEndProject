package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;

import java.util.List;

public interface HotelRoomService {

	List<HotelRoomVO> queryHotelRoomByHotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception;

	List<HotelImage> getImgByTargetIdAndTypeId(Long targetId, String typeId) throws Exception;
}
