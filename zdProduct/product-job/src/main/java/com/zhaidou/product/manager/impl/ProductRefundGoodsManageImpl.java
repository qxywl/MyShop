package com.zhaidou.product.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Node;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zhaidou.ecerp.RefundGoodsQuery;
import com.zhaidou.framework.manager.impl.BaseManagerImpl;
import com.zhaidou.product.manager.ProductRefundGoodsManager;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductRefundGoodsModel;
import com.zhaidou.product.service.ProductRefundGoodsService;
/**
 * 同步退货单
 * @author google
 *
 */
@Service("productRefundGoodsManager")
public class ProductRefundGoodsManageImpl extends BaseManagerImpl implements ProductRefundGoodsManager{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRefundGoodsManageImpl.class);

    private static  boolean JOB_Lock = true;
    
    @Resource
    private ProductRefundGoodsService productRefundGoodsService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if (JOB_Lock) {
			JOB_Lock = false;
			try {
				LOGGER.info("从erp获取退货单数据");

				DateTime date = new DateTime();
				//当前时间一小时内
				String datetimestr = "yyyy-MM-dd'T'HH:mm:ss";
				String dateStart =date.minusHours(1).toString(datetimestr);
				String dateEnd = date.toString(datetimestr);
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("[{'paramname':'cwsh', 'paramcompair': '=', 'paramvalue':'1'},");
				stringBuilder.append("{'paramname':'cwrq', 'paramcompair': '>', 'paramvalue':'"+dateStart+"'},");
				stringBuilder.append("{'paramname':'cwrq', 'paramcompair': '<=', 'paramvalue':'"+dateEnd+"'}]");
				Document document = RefundGoodsQuery.findRefundGoods(stringBuilder.toString());
				Node node = document.selectSingleNode("//thd_get_response/total_results");
				if (node == null || node.getText() == null ||"0".equals(node.getText()) ) {
					LOGGER.info("erp当前时间段无查询结果:dateStart ="+dateStart+", dateEnd ="+dateEnd);
					return;
				}

				List<ProductRefundGoodsModel> productRefundGoodsModels = Lists.newArrayList();
				List<Node> list = document.selectNodes("//thd_get_response/thds/thd");
				for (Node refundNode : list) {
					Node idNode = refundNode.selectSingleNode("id");
					Long refundId = Long.valueOf(idNode.getText());
					long count= productRefundGoodsService.countListPage(refundId);
					if(count > 0) {
						LOGGER.warn("this data is exist count ="+count +", refundNode ="+refundNode.asXML());
						continue;
					}

					Node djbh = refundNode.selectSingleNode("djbh");
					Node ydjh = refundNode.selectSingleNode("ydjh");
					Node dddjbh = refundNode.selectSingleNode("dddjbh");
					Node sl = refundNode.selectSingleNode("sl");
					Node sjje = refundNode.selectSingleNode("sjje");
					Node cwr = refundNode.selectSingleNode("cwr");
					Node cwrq = refundNode.selectSingleNode("cwrq");
					Node hydm = refundNode.selectSingleNode("hydm");
					Node ckdm = refundNode.selectSingleNode("ckdm");
					Node ckmc = refundNode.selectSingleNode("ckmc");
					
					List<Node> thdmxNodes = refundNode.selectNodes("thdmxs/thdmx");

					for (Node thdmxNode : thdmxNodes) {

						Node fhcbj = thdmxNode.selectSingleNode("fhcbj");
						Node spdm = thdmxNode.selectSingleNode("spdm");
						Node skudm = thdmxNode.selectSingleNode("skudm");
						ProductRefundGoodsModel productRefundGoodsModel = new ProductRefundGoodsModel();
						productRefundGoodsModel.setRefundId(refundId);
						productRefundGoodsModel.setDjbh(djbh.getText());
						productRefundGoodsModel.setYdjh(ydjh.getText());
						productRefundGoodsModel.setDddjbh(dddjbh.getText());
						double refundsl = Double.valueOf(sl.getText());
						productRefundGoodsModel.setSl((int)refundsl);
						productRefundGoodsModel.setSjje(Double.valueOf(sjje.getText()));
						productRefundGoodsModel.setCwr(cwr.getText());
						DateTime dateTime = new DateTime(cwrq.getText());
						productRefundGoodsModel.setCwrq(dateTime.toDate());
						productRefundGoodsModel.setHydm(hydm.getText());
						productRefundGoodsModel.setCkdm(ckdm.getText());
						productRefundGoodsModel.setCkmc(ckmc.getText());
						productRefundGoodsModel.setFhcbj(Double.valueOf(fhcbj.getText()));
						productRefundGoodsModel.setSpdm(spdm.getText());
						productRefundGoodsModel.setSkudm(skudm.getText());
						productRefundGoodsModel.setStatus(0);
					    productRefundGoodsModels.add(productRefundGoodsModel);
					}
				}
				productRefundGoodsService.insertProductRefundGoodsList(productRefundGoodsModels);

			} catch (Exception e) {
				LOGGER.error("从erp获取退货单数据 error" , e);
			} finally {
				JOB_Lock = true;
			}
		} else {
			LOGGER.info("从erp获取退货单数据  job  Last time not  end");
		}
	}
}
