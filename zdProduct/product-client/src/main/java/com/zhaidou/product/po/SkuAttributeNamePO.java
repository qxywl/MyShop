/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po;

import java.io.Serializable;
import java.util.List;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-21     Created
 *
 * </pre>
 * @since 1.
 */

public class SkuAttributeNamePO implements Serializable {
	private static final long serialVersionUID = 6969534067313400489L;
	Integer id;
    String skuCode;
    String name;
    List<SkuAttributeValuePO> values;
}
