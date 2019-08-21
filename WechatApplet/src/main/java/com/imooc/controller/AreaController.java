package com.imooc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.entity.Area;
import com.imooc.service.AreaService;

@RestController
@RequestMapping("/superadmin")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	/**
	 *显示列表页面
	 */
	@RequestMapping(value = "/listarea",method = RequestMethod.GET)
	private Map<String, Object> ListArea(){
		Map<String, Object> modelMap=new HashMap<String, Object>();
		List<Area> list=areaService.getAreaList();
		modelMap.put("areaList", list);
		return modelMap;
	}
	
	@RequestMapping(value = "/getareabyid",method = RequestMethod.GET)
	private Map<String, Object> getAreaById(/*@RequestParam("areaId")*/Integer areaId){
		Map<String, Object> modelMap=new HashMap<String, Object>();
		Area area=areaService.getAreaById(areaId);
		modelMap.put("area", area);
		return modelMap;
	}
	
	@RequestMapping(value = "/addarea",method = RequestMethod.POST)
	private Map<String, Object> addArea(@RequestBody Area area){
		//由于前端传的参数只能为xml,json或其他类型，所以需要@RequestBody将对象转化为json串然后写到客户端
		Map<String, Object> modelMap=new HashMap<String, Object>();
		modelMap.put("success", areaService.addArea(area));
		return modelMap;
	}
	
	@RequestMapping(value = "/deletearea",method = RequestMethod.GET)
	private Map<String, Object> deleteArea(Integer areaId){
		//由于前端传的参数只能为xml,json或其他类型，所以需要@ResponseBody将对象转化为json串然后写到客户端
		Map<String, Object> modelMap=new HashMap<String, Object>();
		modelMap.put("success", areaService.deleteArea(areaId));
		return modelMap;
	}
	
	//实现修改区域信息
	@RequestMapping(value = "/modifyarea",method = RequestMethod.POST)
	private Map<String, Object> modifyArea(@RequestBody Area area){
		//由于前端传的参数只能为xml,json或其他类型，所以需要@ResponseBody将对象转化为json串然后写到客户端
		Map<String, Object> modelMap=new HashMap<String, Object>();
		modelMap.put("success", areaService.modifyArea(area));
		return modelMap;
	}

}
