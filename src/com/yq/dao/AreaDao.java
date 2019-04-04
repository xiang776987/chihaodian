package com.yq.dao;

import java.util.List;
import java.util.Map;

import com.yq.entity.Area;

public interface AreaDao {
	public int daoInsert(Map<String,Object> map);
	
	public int daoUpdate(Map<String,Object> map);
	
	public int upstatus(Map<String,Object> map);
	
	public int daoSort(Map<String,Object> map);
	
	public List<Area> list(Area area); 
	
	public List<Area> listById(Area area); 
}
