package com.imooc.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.entity.Area;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
	//通过spring容器注入DAO的实现类
	@Autowired
	private AreaDao areaDao;

	@Test
	@Ignore
	public void testQueryArea() {
		List<Area>areaList=areaDao.queryArea();
		//验证预期值是否和实际值相符
		assertEquals(2, areaList.size());
//		System.out.println("验证成功");
	}

	@Test
	@Ignore
	public void testQueryAreaById() {
		try {
			Area area=areaDao.queryAreaById(2);
			assertEquals("东苑", area.getAreaName());
		} catch (Exception e) {
			System.out.println("验证失败");
		}
		
	}

	@Test
	public void testInsertArea() {
		Area area=new Area();
		area.setAreaName("北京");
		area.setPriority(1);
		int effectedNum=areaDao.insertArea(area);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testUpdateArea() {
		Area area=new Area();
		area.setAreaId(4);
		area.setAreaName("上海");
		area.setPriority(3);
		area.setLastEditTime(new Date());
		int effectedNum=areaDao.updateArea(area);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testDeleteArea() {
		int effectedNum=areaDao.deleteArea(4);
		assertEquals(1, effectedNum);
	}

}
