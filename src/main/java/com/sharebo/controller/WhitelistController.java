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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
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
	 * ��Ӱ�����
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
				return new ResultDto(2003,"�������Ϊ�գ�");	
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date_creat=dateFormat.parse(whitelist.getPeriodvalidity());
			SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd");
			String datecreat = dateForma.format(date_creat);
			whitelist.setPeriodvalidity(datecreat);
			if(service.valserwhitelistExists(whitelist.getCarNo(),whitelist.getCommId())>0){
				if(service.updatewhitelist(whitelist)>0){
					return new ResultDto(200,"�޸İ������ɹ���");
				}
			}
			if(service.addWhitelist(whitelist)>0){
				return new ResultDto(200,"������¼��ɹ���");
			}
		} catch (Exception e) {
			log.error("��Ӱ������쳣��");
			System.out.println("��Ӱ������쳣��");
		}
		return new ResultDto(2001,"¼���쳣��");
	}
	 /**
	  * ɾ��������
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
					return new ResultDto(2003,"�������Ϊ�գ�");	
			 }
			 if(service.delectisfailure(whitelistId)>=0){
					return new ResultDto(200,"������ɾ���ɹ���");
			 }
		} catch (Exception e) {
			log.error("ɾ���������쳣��");
			System.out.println("ɾ���������쳣��");
		}
		return new ResultDto(2001,"ɾ���쳣��");
	 }
	 
	/**
	 * @param commId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * white/getwhitelist.do?commId=24602080450744070&carNo=��FE2609&name=ʩ����&address=¥2503&pageIndex=1&pageSize=12
	 */
	@ResponseBody
    @RequestMapping(value="getwhitelist")
	 public ResultDto seletInviye(String commId,Integer pageIndex,Integer pageSize,String carNo,String name,String address){
    	try {
    		if(pageIndex==null || pageSize==null || commId==null || pageIndex==0){
				return new ResultDto(3006,"����������Ϸ�");
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
			//���ÿ�ʼ
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
				System.out.println("��Ч��Ϊ:"+periodvalidity);
				System.out.println("��ǰʱ��Ϊ:"+time);
				if(periodvalidity.compareTo(time)>=0){
					list.get(i).setIsDisable(1);//δʧЧ
				}else{
					list.get(i).setIsDisable(0);//��ʧЧ
				}
			}
			pager.setList(list);
			//��ѯ����
			pager.setTotalRecords(service.selectWhitelistCount(result,carNo,name,address));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������ҳ�쳣��");
			System.out.println("��������ҳ�쳣��");
		}
    	return new ResultDto(3002,"��������");
	}
	/**
	 * �޸İ������Ƿ���Ч
	 * @param whitelistId
	 * @param whitelist
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateisfailure")
	@ResponseBody
	public ResultDto updateisfailure(String whitelistId,Integer isfailure,String periodvalidity,Integer chargeTimeType) throws Exception{
		if(whitelistId==null){
			return new ResultDto(3001,"�����������Ϊ��");
		}
		if(service.updateIsfailure(whitelistId,isfailure,periodvalidity,chargeTimeType)){
			return new ResultDto(200,"�޸ĳɹ�!");
		}
		return new ResultDto(3002,"�޸�ʧ��");
	}
}
