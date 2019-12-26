package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.AreaDic;

import java.util.List;

public interface AreaDicService {

	List<AreaDic> getHotCityByIsChina(Integer isChina)throws Exception;
}
