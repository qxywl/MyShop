<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <meta charset="utf-8">
    <title>模板1</title>
    <!-- <link rel="stylesheet" href="css/reset.css"> -->
    <link rel="stylesheet" href="${contextPath}/styles/yulan/css/style.css">
</head>

<body> 
        <div class="top">
            <img src="${contextPath}/styles/yulan/img/top.jpg" alt="">
        </div>
        <!-- 头部删掉 -->

        <div class="porduct_info">
            <h2>${productModel.productName }</h2>
            <div class="clearfix">
                <div class="showPro">
                    <!--产品放大镜效果开始-->
                    <div class="showImg left">
                        <div id="preview">
                            <div class="jqzoom" id="spec-n1">
                                <img height="388" src="${productModel.mainPic }" jqimg="${productModel.mainPic }" width="388" alt="">
                            </div>
                            <div id="spec-n5">
                                <div class="control" id="spec-left">
                                    <img src="${contextPath}/styles/yulan/img/left.gif" alt="">
                                </div>
                                <div id="spec-list">
                                    <ul id="imageUrl">
                                    <c:if test="${imglist!=null }">
                    		               <c:forEach items="${imglist }" var="imagesku">
                    		                     <li>
                                                   <img src="${imagesku.image}" alt="${imagesku.image}">
                                                 </li>
                    		               </c:forEach>
                    		         </c:if>
                    		            
                                    </ul>
                                </div>
                                <div class="control" id="spec-right">
                                    <img src="${contextPath}/styles/yulan/img/right.gif" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="sku-select">
                    <div class="sku-selecttitle">
                        <p class="sku-t">
                                                                                                     价格：<em class="sku-rmb">¥</em><em class="sku-price">${productModel.tshPrice }</em>  <span>+<em class="tb">${productModel.tb }</em>特币</span>
                        </p>
                        <p class="sku-e"><span>品牌:</span> ${productModel.brandName }</p>
                        <p class="sku-e"><span>销售情况:</span> 售出 <b>0</b> 件（<b>0</b> 条评论）</p>
                        <p class="sku-e"><span>所在地区:</span>中国</p>
                    </div>

                    <dl class="clearfix">
                        <dt>${ys}：</dt>
                        <dd>
                            <c:forEach items="${yanseMap}" var="map">
                    		      <input type="button" class="sku" attr_id="${map.key}" value="${map.key}" onclick="changeSkuKeyColor(this);" data_src="${map.value }"/>
                    		 </c:forEach>
                    		  
                        </dd>
                    </dl>

                    <dl class="clearfix">
                        <dt>${cm}：</dt>
                        <dd>
                            <c:forEach items="${chimaMap}" var="map2">
                    		      <input type="button" class="sku" attr_id="${map2.key}" onclick="changeSkuKeySpec(this);" value="${map2.key}"/>
                    		</c:forEach>
                    		
                        </dd>
                    </dl>
					<div id="skuKey" style="display:none"></div>
                    <dl class="clearfix">
                        <dt>数量：</dt>
                        <dd class="clearfix sku-add">
                            <a href="javascript:;" class="cut">-</a>
                            <input type="text" readonly="" value="1" data-kucun="5">
                            <a href="javascript:;" class="add">+</a>
                            <span>库存：<span class="kucun">0</span> 件</span>
                        </dd>
                    </dl>

                    <div class="btnsubmit">
                        <a href="javascript:;" class="btn-buy">立即购买</a>
                        <a href="javascript:;" class="btn-addcar">加入购物车</a>
                    </div>
                </div>
            </div>
        </div>
		<div id="imageMapJson" style="display:none;">${imageMap }</div>
        <div class="porduct_c clearfix">
            <div class="porduct_cl"></div>
            <div class="porduct_cr">
               <div class="title">
                   <a href="#" class="on">商品详情</a>
               </div>
               <div class="word">
                   <ul class="attributes-list clearfix">
                   	<li title=" 默认加价率">默认加价率: ${productModel.productOperateModel.productPriceRate }</li>
                       <li title=" 货号">货号: ${productModel.productMallModel.productAtrNumber }</li>
                       <li title=" （包装）长">（包装）长: ${productModel.productMallModel.productLong }</li>
                       <li title=" （包装）高">（包装）高: ${productModel.productMallModel.productHeight }</li>
                       <li title=" （包装）宽">（包装）宽: ${productModel.productMallModel.productWidth }</li>
                       <li title=" 单品毛重量">单品毛重量: ${productModel.productMallModel.productWeight }</li>
                       <li title=" 体积">体积: ${productModel.productMallModel.productDensity }</li>
                       <li title=" 产地">产地: ${productModel.productMallModel.productProducer }</li>
                       
                       <li title=" 商品短名称">商品短名称: ${productModel.productOperateModel.productShortName }</li>
                       <li title=" 商品简介">商品简介: ${productModel.productDesc }</li>
                       <li title=" 品牌">品牌: ${productModel.brandName }</li>
                       <li title=" 供应商编号">供应商编号: ${productModel.supplierId }</li>
                       <li title=" 店铺编号">店铺编号: ${productModel.shopId }</li>
                       
                       <li title=" 商品前缀">商品前缀: ${productModel.productOperateModel.productPrefix }</li>
                       <li title=" 商品后缀">商品后缀: ${productModel.productOperateModel.productSuffix }</li>
                       <li title=" 商品关键字">商品关键字: ${productModel.productOperateModel.productKeyword }</li>
                       <li title=" 是否下架显示">是否下架显示: <c:if test="${productModel.productOperateModel.productDownShow==1 }">是</c:if><c:if test="${productModel.productOperateModel.productDownShow==2 }">否</c:if></li>
                       <li title=" 是否支持贵就赔">是否支持贵就赔: <c:if test="${productModel.productOperateModel.isExpensive==1 }">支持</c:if><c:if test="${productModel.productOperateModel.isExpensive==2 }">不支持</c:if></li>
                       
                       <c:if test="${productModel.productAttributeList!=null }">
                    		<c:forEach items="${productModel.productAttributeList }" var="attr">
                       			<li title=" ${attr.productAttributeName }">${attr.productAttributeName }: ${attr.productAttributeValue }</li>
                       		</c:forEach>
                       </c:if>
                   </ul>
               </div>
               <div class="word">
               	手机图文详情：<br/>${productModel.productInfoModel.appProductInfo }
               </div>
            </div>
        </div>
        
    <script src="${contextPath}/styles/yulan/js/jquery-1.10.2.min.js"></script>
    <script src="${contextPath}/styles/yulan/js/zoom.lib.js" type="text/javascript"></script>
    <script src="${contextPath}/styles/yulan/js/zoom.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        $(function(){
         
            //放大镜效果
            $(".jqzoom").jqueryzoom({
                xzoom:330,//放大图显示范围
                yzoom:388,//放大图显示范围
                offset:10,
                position:"right",
                preload:1,
                lens:1
            });
            $("#spec-list").jdMarquee({
                deriction:"left",
                width:440,//小图显示范围
                height:56,//小图高度
                step:2,
                speed:4,//小图滚动速度
                delay:10,
                control:true,
                _front:"#spec-right",//右边按钮容器
                _back:"#spec-left"//左边按钮容器
            });
            // 更改src大图
            $("#spec-list").on('mouseover','img',function(){
                var src=$(this).attr("src");
                $("#spec-n1 img").eq(0).attr({
                    src:src.replace("\/n5\/","\/n1\/"),
                    jqimg:src.replace("\/n5\/","\/n0\/")
                });
            }); 
            // end
        })
    </script>

    <script type="text/javascript">
        var startTime = new Date().getTime();
        var keys = ${keys};
        var data = ${ycDate};
        
        //{"24;12": {price: 366.00, count: 46,tb: 123,stock: 1}, "25;12": {price: 436, count: 66,tb: 123,stock: 2},"24;13": {price: 326.00, count: 46,tb: 123,stock: 3}, "25;13": {price: 426, count: 66,tb: 123,stock: 4}}

        //保存最后的组合结果信息
        var SKUResult = {};
        //获得对象的key
        function getObjKeys(obj) {
            if (obj !== Object(obj)) throw new TypeError('Invalid object');
            var keys = [];
            for (var key in obj)
                if (Object.prototype.hasOwnProperty.call(obj, key))
                    keys[keys.length] = key;
            return keys;
        }

        //把组合的key放入结果集SKUResult
        function add2SKUResult(combArrItem, sku) {
            var key = combArrItem.join(";");
            if(SKUResult[key]) {//SKU信息key属性·
                SKUResult[key].count += sku.count;
                SKUResult[key].prices.push(sku.price);
                SKUResult[key].tb.push(sku.tb);
            } else {
                SKUResult[key] = {
                    count : sku.count,
                    prices : [sku.price],
                    tb : [sku.tb]
                };
            }
        }

        //初始化得到结果集
        function initSKU() {
            var i, j, skuKeys = getObjKeys(data);
            for(i = 0; i < skuKeys.length; i++) {
                var skuKey = skuKeys[i];//一条SKU信息key
                var sku = data[skuKey]; //一条SKU信息value
                var skuKeyAttrs = skuKey.split(";"); //SKU信息key属性值数组
                skuKeyAttrs.sort(function(value1, value2) {
                    return parseInt(value1) - parseInt(value2);
                });

                //对每个SKU信息key属性值进行拆分组合
                var combArr = combInArray(skuKeyAttrs);
                for(j = 0; j < combArr.length; j++) {
                    add2SKUResult(combArr[j], sku);
                }
					
                //结果集接放入SKUResult
                SKUResult[skuKeyAttrs.join(";")] = {
                    count:sku.count,
                    prices:[sku.price],
                    tb:[sku.tb]
                }
            }
        }

        /**
         * 从数组中生成指定长度的组合
         * 方法: 先生成[0,1...]形式的数组, 然后根据0,1从原数组取元素，得到组合数组
         */
        function combInArray(aData) {
            if(!aData || !aData.length) {
                return [];
            }

            var len = aData.length;
            var aResult = [];

            for(var n = 1; n < len; n++) {
                var aaFlags = getCombFlags(len, n);
                while(aaFlags.length) {
                    var aFlag = aaFlags.shift();
                    var aComb = [];
                    for(var i = 0; i < len; i++) {
                        aFlag[i] && aComb.push(aData[i]);
                    }
                    aResult.push(aComb);
                }
            }
            
            return aResult;
        }

        /**
         * 得到从 m 元素中取 n 元素的所有组合
         * 结果为[0,1...]形式的数组, 1表示选中，0表示不选
         */
        function getCombFlags(m, n) {
            if(!n || n < 1) {
                return [];
            }

            var aResult = [];
            var aFlag = [];
            var bNext = true;
            var i, j, iCnt1;

            for (i = 0; i < m; i++) {
                aFlag[i] = i < n ? 1 : 0;
            }

            aResult.push(aFlag.concat());

            while (bNext) {
                iCnt1 = 0;
                for (i = 0; i < m - 1; i++) {
                    if (aFlag[i] == 1 && aFlag[i+1] == 0) {
                        for(j = 0; j < i; j++) {
                            aFlag[j] = j < iCnt1 ? 1 : 0;
                        }
                        aFlag[i] = 0;
                        aFlag[i+1] = 1;
                        var aTmp = aFlag.concat();
                        aResult.push(aTmp);
                        if(aTmp.slice(-n).join("").indexOf('0') == -1) {
                            bNext = false;
                        }
                        break;
                    }
                    aFlag[i] == 1 && iCnt1++;
                }
            }
            return aResult;
        } 

        //初始化用户选择事件
        $(function() {
            initSKU();
            // var endTime = new Date().getTime();
            // $('#init_time').text('init sku time: ' + (endTime - startTime) + " ms");

            $('.sku').each(function() {
                var self = $(this);
                var attr_id = self.attr('attr_id');
                if(!SKUResult[attr_id]) {
                    self.attr('disabled', 'disabled');
                }
            }).click(function() {
                var self = $(this);

                //选中自己，兄弟节点取消选中
                self.toggleClass('bh-sku-selected').siblings().removeClass('bh-sku-selected');
                
                //已经选择的节点
                var selectedObjs = $('.bh-sku-selected');

                if(selectedObjs.length) {
                	
                    //获得组合key价格
                    var selectedIds = [];
                    selectedObjs.each(function() {
                        selectedIds.push($(this).attr('attr_id'));
                    });
                    selectedIds.sort(function(value1, value2) {
                        return parseInt(value1) - parseInt(value2);
                    });
                    var len = selectedIds.length;
                    var prices = SKUResult[selectedIds.join(';')].prices;
                    var counts = SKUResult[selectedIds.join(';')].count;
                    var tebi = SKUResult[selectedIds.join(';')].tb;
                    var maxPrice = Math.max.apply(Math, prices);
                    var minPrice = Math.min.apply(Math, prices);
                    

                    $('.sku-price').text(maxPrice > minPrice ? minPrice + "-" + maxPrice : maxPrice);
                    
                    $('.kucun').text(counts);
                    $('.tb').text(tebi);

                    //用已选中的节点验证待测试节点 underTestObjs
                    $(".sku").not(selectedObjs).not(self).each(function() {
                        var siblingsSelectedObj = $(this).siblings('.bh-sku-selected');
                        var testAttrIds = [];//从选中节点中去掉选中的兄弟节点
                        if(siblingsSelectedObj.length) {
                            var siblingsSelectedObjId = siblingsSelectedObj.attr('attr_id');
                            for(var i = 0; i < len; i++) {
                                (selectedIds[i] != siblingsSelectedObjId) && testAttrIds.push(selectedIds[i]);
                            }
                        } else {
                            testAttrIds = selectedIds.concat();
                        }
                        testAttrIds = testAttrIds.concat($(this).attr('attr_id'));
                        testAttrIds.sort(function(value1, value2) {
                            return parseInt(value1) - parseInt(value2);
                        });
                        if(!SKUResult[testAttrIds.join(';')]) {
                            $(this).attr('disabled', 'disabled').removeClass('bh-sku-selected');
                        } else {
                            $(this).removeAttr('disabled');
                        }
                    });
                } else {
                    //设置默认价格
                    // $('#price').text('--');
                    //设置属性状态
                    $('.sku').each(function() {
                        SKUResult[$(this).attr('attr_id')] ? $(this).removeAttr('disabled') : $(this).attr('disabled', 'disabled').removeClass('bh-sku-selected');
                    })
                }
            });
            
            // 增加减少数量
            $(".sku-add .add").click(function(){
                var _this=$(this),
                    _textcount=_this.parents(".sku-add").find("input"),
                    _kucun=parseInt(_textcount.attr("data-kucun"));//库存放在input的自定义属性里面

                    if (_textcount.val()>=_kucun) {
                        alert("库存不够了.");
                        return false;
                    }
                    else{
                        _textcount.val(parseInt(_textcount.val())+1);
                    }
            });

            $(".sku-add .cut").click(function(){
                    var _this=$(this),
                    _textcount=_this.parents(".sku-add").find("input"),
                    _kucun=parseInt(_textcount.attr("data-kucun"));//库存放在input的自定义属性里面

                    if (_textcount.val()==1) {
                        alert("最少一件商品.");
                        return false;
                    }
                    else{
                        _textcount.val(parseInt(_textcount.val())-1);
                    }
            });
			
            
            $('.sku-select').on('click','input',function(){
                var src=$(this).attr('data_src');
                // $('#spec-n1 img').attr('src',src);
                // console.log(src);

                $("#spec-n1 img").eq(0).attr({
                    src:src.replace("\/n5\/","\/n1\/"),
                    jqimg:src.replace("\/n5\/","\/n0\/")
                });
            })
            // end
        });
        
        
        $(document).ready(function(){
        	
        	
            $('#check_on').click(function(){
        		$('#check_on').removeClass("check_off");
        		$('#check_on').addClass("check_orange");
        		$('#check_off').removeClass("check_orange");
        		$('#check_off').addClass("check_off");
        		$("#pc_txt").show(500);
        		$("#phone_txt").hide();
        	});
        	$('#check_off').click(function(){
        		$('#check_on').removeClass("check_orange");
        		$('#check_on').addClass("check_off");
        		$('#check_off').removeClass("check_off");
        		$('#check_off').addClass("check_orange");
        		$("#pc_txt").hide();
        		$("#phone_txt").show(500);
        	});
        	
        	$('#check_reserve').click(function(){
        		$('#check_reserve').removeClass("check_off");
        		$('#check_reserve').addClass("check_orange");
        		$('#check_custom').removeClass("check_orange");
        		$('#check_custom').addClass("check_off");
        		$("#reserve_txt").show(500);
        		$("#custom_txt").hide();
        	});
        	$('#check_custom').click(function(){
        		$('#check_reserve').removeClass("check_orange");
        		$('#check_reserve').addClass("check_off");
        		$('#check_custom').removeClass("check_off");
        		$('#check_custom').addClass("check_orange");
        		$("#reserve_txt").hide();
        		$("#custom_txt").show(500);
        	});
        });
        
        
        function changeImageList(){
        	var imageJson = new Function("return" + $("#imageMapJson").html())();
        	var skuKey = $("#skuKey").html();
        	 /* var imageJson = $("#imageMapJson").html(); */
        	 for(var i=0;i<imageJson.length;i++){
        		 var skuKey1 = imageJson[i].colorValueAlias+":"+imageJson[i].specValueAlias;
        		 if(skuKey1==skuKey){
        			 if(imageJson[i].productImagerList!=undefined && imageJson[i].productImagerList!=''){
        				 $("#spec-n1 img").attr("src",imageJson[i].productImagerList[0].image);
        				 var imgageUtl = '';
        				 for(var y=0;y<imageJson[i].productImagerList.length;y++){
        					 imgageUtl = imgageUtl + '<li><img src="'+imageJson[i].productImagerList[y].image+'" alt="'+imageJson[i].productImagerList[y].image+'"></li>';
        				 }
        				 $("#imageUrl").html(imgageUtl);
        			 }
        		 }
        	 }
        }
        
        function changeSkuKeyColor(element){
        	var skuKey = $("#skuKey").html();
        	if(skuKey!=undefined && skuKey!=''){
        		var str = skuKey.split(":");
        		$("#skuKey").html(element.value+":"+str[1]);
        	}else{
        		$("#skuKey").html(element.value+":");
        	}
        	
        	changeImageList();
        }
        function changeSkuKeySpec(element){
        	var skuKey = $("#skuKey").html();
        	if(skuKey!=undefined && skuKey!=''){
        		var str = skuKey.split(":");
        		$("#skuKey").html(str[0]+":"+element.value);
        	}else{
        		$("#skuKey").html(":"+element.value);
        	}
        	changeImageList();
        }
    </script>
</body>
</html>
