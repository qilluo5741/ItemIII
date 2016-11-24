package com.sharebo.service;

import java.util.Map;

public interface CarIntoService {
	/**
	 * �õ���ǰ�豸�Ƿ����
	 * 
	 * @param serialno
	 * @return
	 */
	public int getCarIsInout(String serialno);

	/**
	 * ��֤�����Ƿ�Ϊ������
	 * 
	 * @param carNo
	 * @return
	 */
	public boolean valCarIsWhiteList(String carNo, String serialno);

	// ͨ���豸�õ�������
	public Map<String, Object> getPartnerBySerialno(String serialno);
}
