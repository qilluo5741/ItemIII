package com.sharebo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Whitelist;
import com.sharebo.entity.dto.WhitelistDto;
import com.sharebo.service.WhitelistService;
import com.sharebo.util.Pagers;
import com.sharebo.util.ResultDto;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("white")
public class WhitelistController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WhitelistService service;
	/**
	 * 添加白名单
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * white/whitelist.do?carNo=%E6%B2%AAFO6602&commId=24602080450744070&name=1251&address=1511251&phone=1541&periodvalidity=20161012
	 */
	 @ResponseBody
     @RequestMapping(value="whitelist")
	 public ResultDto Whitelist(WhitelistDto whitelist){
		try {
			if(whitelist.getCarNo()==null){
				return new ResultDto(2003,"请求参数为空！");	
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date_creat=dateFormat.parse(whitelist.getPeriodvalidity());
			SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd");
			String datecreat = dateForma.format(date_creat);
			whitelist.setPeriodvalidity(datecreat);
			if(service.valserwhitelistExists(whitelist.getCarNo(),whitelist.getCommId())>0){
				if(service.updatewhitelist(whitelist)>0){
					return new ResultDto(200,"修改白名单成功！");
				}
			}
			if(service.addWhitelist(whitelist)>0){
				return new ResultDto(200,"白名单录入成功！");
			}
		} catch (Exception e) {
			log.error("添加白名单异常！");
			System.out.println("添加白名单异常！");
		}
		return new ResultDto(2001,"录入异常！");
	}
	 /**
	  * 删除白名单
	  * @param commId
	  * @param carNo
	  * @return
	  * white/delectwhitelist.do?whitelistId=24602080450748999
	  */
	 @ResponseBody
     @RequestMapping(value="delectwhitelist")
	 public ResultDto deleteWhitelist(String whitelistId){
		try {
			if(whitelistId==null){
					return new ResultDto(2003,"请求参数为空！");	
			 }
			 if(service.delectisfailure(whitelistId)>=0){
					return new ResultDto(200,"白名单删除成功！");
			 }
		} catch (Exception e) {
			log.error("删除白名单异常！");
			System.out.println("删除白名单异常！");
		}
		return new ResultDto(2001,"删除异常！");
	 }
	 
	/**
	 * @param commId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * white/getwhitelist.do?commId=24602080450744070&carNo=沪FE2609&name=施玉兰&address=楼2503&pageIndex=1&pageSize=12
	 */
	@ResponseBody
    @RequestMapping(value="getwhitelist")
	 public ResultDto seletInviye(String commId,Integer pageIndex,Integer pageSize,String carNo,String name,String address){
    	try {
    		if(pageIndex==null || pageSize==null || commId==null || pageIndex==0){
				return new ResultDto(3006,"请求参数不合法");
			}
    		StringBuffer sb = new StringBuffer();
		    String[] temp = commId.split(",");
		    for (int i = 0; i < temp.length; i++) {
			    sb.append("'" + temp[i] + "',");
		    }
		    String result = sb.toString().substring(0,sb.length()-1);
    		Pagers<WhitelistDto> pager=new Pagers<WhitelistDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("commId", result);
			map.put("carNo", carNo);
			map.put("name", name);
			map.put("address", address);
			List<WhitelistDto> list=service.getselectWhitelist(map);
			for(int i=0;i<list.size();i++){
				String periodvalidity=list.get(i).getPeriodvalidity();
				Date now=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String time=sdf.format(now).toString();
				System.out.println("有效期为:"+periodvalidity);
				System.out.println("当前时间为:"+time);
				if(periodvalidity.compareTo(time)>=0){
					list.get(i).setIsDisable(1);//未失效
				}else{
					list.get(i).setIsDisable(0);//已失效
				}
			}
			pager.setList(list);
			//查询总数
			pager.setTotalRecords(service.selectWhitelistCount(result,carNo,name,address));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("白名单分页异常！");
			System.out.println("白名单分页异常！");
		}
    	return new ResultDto(3002,"暂无数据");
	}
	/**
	 * 修改白名单是否有效
	 * @param whitelistId
	 * @param whitelist
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateisfailure")
	@ResponseBody
	public ResultDto updateisfailure(String whitelistId,Integer isfailure,String periodvalidity,Integer chargeTimeType) throws Exception{
		if(whitelistId==null){
			return new ResultDto(3001,"请求参数不能为空");
		}
		if(service.updateIsfailure(whitelistId,isfailure,periodvalidity,chargeTimeType)){
			return new ResultDto(200,"修改成功!");
		}
		return new ResultDto(3002,"修改失败");
	}
}
