package com.imooc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.dao.AreaDao;
import com.imooc.entity.Area;
import com.imooc.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAreaList() {
		
		return areaDao.queryArea();
	}

	@Override
	public Area getAreaById(int areaId) {
//		int a=1/0;
		return areaDao.queryAreaById(areaId);
	}

	//可以特别注明回滚的Exception类@Transactional(rollbackFor = Exception.class)
	@Override
	@Transactional
	public boolean addArea(Area area) {
		//空值判断，主要是判断areaname不为空
		if(area.getAreaName()!=null&&!"".equals(area.getAreaName())) {
			//设置默认值
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try {
				int effectedNum=areaDao.insertArea(area);
				if(effectedNum>0) {
					return true;
				}else {
					//@Transactional默认回滚RuntimeException
					throw new RuntimeException("添加区域信息失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("添加区域信息失败:"+e.toString());
			}
		}else {
			throw new RuntimeException("区域信息不能为空");
		}
		
	}

	@Override
	@Transactional
	public boolean modifyArea(Area area) {
		if(area.getAreaId()!=null&&area.getAreaId()>0) {
			//设置默认值
			area.setLastEditTime(new Date());
			try {
				int effectedNum=areaDao.updateArea(area);
				if(effectedNum>0) {
					return true;
				}else {
					throw new RuntimeException("更新区域信息失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("更新区域信息失败:"+e.toString());
			}
		}else {
			throw new RuntimeException("区域信息不能为空");
		}
	}

	@Override
	@Transactional
	public boolean deleteArea(int areaId) {
		if(areaId>0) {
			try {
				int effectedNum=areaDao.deleteArea(areaId);
				if(effectedNum>0) {
					return true;
				}else {
					throw new RuntimeException("删除区域信息失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败:"+e.toString());
			}
		}else {
			throw new RuntimeException("区域信息不能为空");
		}
	}

}
