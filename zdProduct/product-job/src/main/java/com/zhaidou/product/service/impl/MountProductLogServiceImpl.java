/*
 * 文 件 名:  SalecategoryProductRelationLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.dao.MountProductLogDao;
import com.zhaidou.product.model.MountProductLogModel;
import com.zhaidou.product.service.MountProductLogService;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("mountProductLogService")
public class MountProductLogServiceImpl implements MountProductLogService {
	private static final Log logger = LogFactory.getLog(MountProductLogServiceImpl.class);

	@Resource
	private MountProductLogDao mountProductLogDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean batchInsert(List<MountProductLogModel> logList) throws ZhaidouRuntimeException {
		try {
			mountProductLogDao.batchInsert(logList);
		} catch (Exception e) {
			// throw new TeshehuiRuntimeException("产品挂载日志批量插入失败",e);
			logger.error("产品挂载日志批量插入失败",e);
			return false;
		}
		return true;
	}

}
