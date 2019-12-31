package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;

import java.util.List;

public interface LabelDicService {

	List<LabelDic> getHotelFeature() throws Exception;

	List<LabelDicVO> queryLabelDicVOListByParent(Long parentId) throws Exception;
}
