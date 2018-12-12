package com.cfcp.incc.controller.orders;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.cfcp.incc.entity.*;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.service.commodity.CommodityService;
import com.cfcp.incc.service.orders.OrderPackageService;
import com.cfcp.incc.service.orders.OrderPriceSystemService;
import com.cfcp.incc.service.orders.OrdersService;
import com.cfcp.incc.service.orders.ProductService;
import com.cfcp.incc.service.user.UserService;
import com.cfcp.incc.utils.AlipayConfig;
import com.cfcp.incc.utils.JsonResult;
import com.cfcp.incc.utils.LeeJSONResult;
import com.cfcp.incc.utils.OrderStatusEnum;

import com.cfcp.incc.service.Sid;
import com.cfcp.incc.utils.generator.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * @Title: AlipayController.java
 * @Package com.sihai.controller
 * @Description: controller
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 *
 * @author sihai
 * @date 2017年8月18日 上午10:39:15
 * @version V1.0
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

	final static Logger log = LoggerFactory.getLogger(AlipayController.class);

	@Autowired
	private OrderPriceSystemService orderPriceSystemservice;
	@Autowired
	private OrderPackageService orderPackageService;


	@Autowired
	private CommodityService commodityService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrdersService orderService;

	@Autowired
	UserService userService;


	@Value("${product.Price}")
	private String product_Price;

	private Sid sid = new Sid();

	/**
	 * 获取产品列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/products")
	public ModelAndView products() throws Exception {
		List<OrderPriceSystem> oList = orderPriceSystemservice.queryAll();


		//List<Product> pList = productService.getProducts();
		ModelAndView mv = new ModelAndView("products");
		//mv.addObject("pList", pList);
		mv.addObject("pList", oList);

		return mv;
	}

	/**
	 * 获取当前用户所有已购套餐
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/orderPackagesByUserId")
	public  Map<String, Object>  orderPackages() throws Exception {
		User user = UserContext.getCurrentUser();
		List<OrderPackage> opList = orderPackageService.queryAllByUserId(user.getId());
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("rows",opList);
		map.put("total", opList.size());

		return map;
	}

	/**
	 * 进入确认页面
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goConfirm")
	public ModelAndView goConfirm(String productId, HttpServletRequest request) throws Exception {
		String productIds = request.getParameter("id");
		if(productIds==null)
			productIds = productId;


		OrderPriceSystem ops = orderPriceSystemservice.queryById(productId);

		User user = UserContext.getCurrentUser();
		Map mp = new HashMap();
		String userId = "";
		if(user!=null){
			userId = user.getId();
		}
		mp.put("userId",userId);
		mp.put("status",1);
		OrderPackage op = orderPackageService.findMaxOrderPackageByUserIdAndStatusIsBuy(mp);
        if(op==null){
			//首次加入费
			OrderPriceSystem ops1 = orderPriceSystemservice.queryByScjrf();
			if(ops1!=null){
				if (ops.getName().indexOf("测试")!=-1){
					ops.setPrice(ops.getPrice()+0.01);
				}else
				    ops.setPrice(ops.getPrice()+ops1.getPrice());
			}
		}


/*
		Commodity c  = null;
		Product p = new Product();
		if(productId!=null && "".equals(productId)){
			c  = commodityService.get(productId);
			p.setId(c.getId());
			p.setName(c.getName());
			p.setPrice(product_Price);
		}else{
			c = new Commodity();
		}*/
		//Product p = productService.getProductById(productId);

		ModelAndView mv = new ModelAndView("goConfirm");
		mv.addObject("p", ops);

		return mv;
	}

	/**
	 *
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createInccOrder2")
	public ModelAndView createInccOrde2r(Orders order, HttpServletRequest request) throws Exception {
		String productId = request.getParameter("id");
		Commodity c  = commodityService.get(productId);
		Product p = new Product();
		p.setId(c.getId());
		p.setName(c.getName());
		p.setPrice(product_Price);

		String orderId = sid.nextShort();
		order.setProductId(productId);
		order.setBuyCounts(1);
		order.setId(orderId);
		order.setOrderNum(orderId);
		order.setCreateTime(new Date());
		order.setOrderAmount(String.valueOf(Float.valueOf(p.getPrice()) * order.getBuyCounts()));
		order.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);
		orderService.saveOrder(order);

		ModelAndView mv = new ModelAndView("goPay");
		mv.addObject("order", order);
		mv.addObject("p", p);

		return mv;
	}

	/**
	 *
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createOrder2")
	public ModelAndView createOrde2r(Orders order) throws Exception {

		Product p = productService.getProductById(order.getProductId());

		String orderId = sid.nextShort();
		order.setId(orderId);
		order.setOrderNum(orderId);
		order.setCreateTime(new Date());
		order.setOrderAmount(String.valueOf(Float.valueOf(p.getPrice()) * order.getBuyCounts()));
		order.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);
		orderService.saveOrder(order);

		ModelAndView mv = new ModelAndView("goPay");
		mv.addObject("order", order);
		mv.addObject("p", p);

		return mv;
	}

	/**
	 * 分段提交
	 * 	第一段：保存订单
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createOrder")
	@ResponseBody
	public JsonResult createOrder(Orders order, HttpServletRequest request) throws Exception {
//public LeeJSONResult createOrder(Orders order, HttpServletRequest request) throws Exception {
		String productId = request.getParameter("id");
/*
		Commodity c  = commodityService.get(order.getProductId());
		//Product p = productService.getProductById(order.getProductId());
		//Product p = productService.getProductById(productId);
		Product p = new Product();
		p.setId(c.getId());
		p.setName(c.getName());
		p.setPrice(product_Price);
*/
        if(order==null){
        	order = new Orders();
        	order.setProductId(productId);
        	order.setBuyCounts(1);
			order.setOrderAmount("0.00");
		}
		OrderPriceSystem p = orderPriceSystemservice.queryById(order.getProductId());
		String orderId = sid.nextShort();
		order.setProductId(order.getProductId());
		order.setBuyCounts(1);
		order.setId(orderId);
		order.setOrderNum(orderId);
		order.setCreateTime(new Date());
		//order.setOrderAmount("2");
		//order.setOrderAmount(String.valueOf(p.getPrice() * order.getBuyCounts()));
		order.setOrderStatus(OrderStatusEnum.WAIT_PAY.key);
		orderService.saveOrder(order);

		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(orderId);
		jsonResult.setMessage("OK");
		jsonResult.setStatus(true);
		return jsonResult;
		//return orderId;
	}

	/**
	 * 分段提交
	 * 	第二段
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goPay")
	public ModelAndView goPay(String orderId) throws Exception {

		Orders order = orderService.getOrderById(orderId);
		String ProductId = "";
		if(order!=null){
			ProductId = order.getProductId();
		}
		//Product p = productService.getProductById(ProductId);
/*
		Commodity c  = commodityService.get(ProductId);
		Product p = new Product();
		p.setId(c.getId());
		p.setName(c.getName());
		p.setPrice(product_Price);
*/
		OrderPriceSystem p = orderPriceSystemservice.queryById(order.getProductId());


		User user = UserContext.getCurrentUser();
		Map mp = new HashMap();
		String userId = "";
		if(user!=null){
			userId = user.getId();
		}
		mp.put("userId",userId);
		mp.put("status",1);
		OrderPackage op = orderPackageService.findMaxOrderPackageByUserIdAndStatusIsBuy(mp);
		if(op==null){
			//首次加入费
			OrderPriceSystem ops1 = orderPriceSystemservice.queryByScjrf();
			if(ops1!=null){
				if (p.getName().indexOf("测试")!=-1){
					p.setPrice(p.getPrice()+0.01);
				}else
					p.setPrice(p.getPrice()+ops1.getPrice());
			}
		}


		ModelAndView mv = new ModelAndView("goPay");
		mv.addObject("order", order);
		mv.addObject("p", p);

		return mv;
	}

	/**
	 *
	 * @Title: AlipayController.java
	 * @Package com.sihai.controller
	 * @Description: 前往支付宝第三方网关进行支付
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *
	 * @author sihai
	 * @date 2017年8月23日 下午8:50:43
	 * @version V1.0
	 */
	@RequestMapping(value = "/goAlipay", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String goAlipay(String orderId, HttpServletRequest request, HttpServletRequest response) throws Exception {

		if(orderId==null){
			orderId = request.getParameter("id");
		}
		Orders order = orderService.getOrderById(orderId);
		String ProductId = "";
		if(order!=null){
			ProductId = order.getProductId();
		}
		//Product product = productService.getProductById(ProductId);
/*

		Commodity c  = commodityService.get(ProductId);
		Product product = new Product();
		product.setId(c.getId());
		product.setName(c.getName());
		product.setPrice(product_Price);
*/

		OrderPriceSystem product = orderPriceSystemservice.queryById(order.getProductId());

		User user = UserContext.getCurrentUser();
		Map mp = new HashMap();
		String userId = "";
		if(user!=null){
			userId = user.getId();
		}
		mp.put("userId",userId);
		mp.put("status",1);
		OrderPackage op = orderPackageService.findMaxOrderPackageByUserIdAndStatusIsBuy(mp);

		mp.put("status",0);
		OrderPackage noBuyOp = orderPackageService.findMaxOrderPackageByUserIdAndStatusNoBuy(mp);
		//List<OrderPackage> opL = orderPackageService.queryAllByUserId(user.getId());

        Integer buyingIimes = 1;
        if(op!=null){
			buyingIimes = op.getBuyingIimes();
			if(buyingIimes==null)
				buyingIimes = 1;
		}
		if(noBuyOp!=null && op==null){
			noBuyOp.setOrderNum(order.getOrderNum());
			noBuyOp.setTotal(product.getTotal());
			orderPackageService.updateOrderNum(noBuyOp);
		}else{
			noBuyOp = new OrderPackage();
			noBuyOp.setId(UUIDGenerator.getUuid());
			noBuyOp.setOrderNum(order.getOrderNum());
			noBuyOp.setOrderPriceSystemId(product.getId());
			noBuyOp.setName(product.getName());
			noBuyOp.setTotal(product.getTotal());
			noBuyOp.setPrice(Double.valueOf(order.getOrderAmount()));
			noBuyOp.setBuyingIimes(buyingIimes+1);
			noBuyOp.setTotal(product.getTotal());
			noBuyOp.setSurplusQuentity(product.getTotal());
			noBuyOp.setQuantityUsed(0);
			noBuyOp.setDele("0");
			noBuyOp.setStatus(0);
			noBuyOp.setUserRole("");
			if(user!=null){
				log.info("userId:"+user.getId()+";userName:"+user.getName());
				noBuyOp.setUserId(user.getId());
				noBuyOp.setUserName(user.getName());
				noBuyOp.setCreateUserId(user.getId());
				noBuyOp.setUpdateUserId(user.getId());
			}else{
				log.info("userId:"+user.getId()+";userName:"+user.getName());
				noBuyOp.setUserId("");
				noBuyOp.setUserName("");
				noBuyOp.setCreateUserId("");
				noBuyOp.setUpdateUserId("");
			}

			if(user!=null && user.getDistributor()!=null){
				noBuyOp.setDistributorId(user.getDistributor().getId());
				noBuyOp.setDistributorName(user.getDistributor().getName());
			}



			orderPackageService.insert(noBuyOp);
		}

/*
      ID,ORDER_PRICE_SYSTEM_ID,NAME,PRICE,
      BUYING_TIMES,TOTAL,QUANTITY_USED,SURPLUS_QUANTITY,DELE,
      IS_USE_LIGHT,STATUS,ORDER_NUM,USER_ID,USER_NAME,
      USER_ROLE,CREATE_USER_ID,DISTRIBUTOR_ID,DISTRIBUTOR_NAME,UPDATE_USER_ID,
      CREATE_TIME,UPDATE_TIME )
 */

		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = orderId;
		//付款金额，必填
		String total_amount = "";
		//订单名称，必填
		String subject = "";
		//商品描述，可空
		String body = "用户订购商品个数：";

        if(order!=null){
			//付款金额，必填
			total_amount = order.getOrderAmount();
			//订单名称，必填
			subject = product.getName();
			//商品描述，可空
			body = "用户订购商品个数：" + order.getBuyCounts();
		}

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    	String timeout_express = "1c";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		return result;
	}

	/**
	 *
	 * @Title: AlipayController.java
	 * @Package com.sihai.controller
	 * @Description: 支付宝同步通知页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *
	 * @author sihai
	 * @date 2017年8月23日 下午8:51:01
	 * @version V1.0
	 */
	@RequestMapping(value = "/alipayReturnNotice")
	public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		log.info("支付成功, 进入同步通知接口...");

		User user = UserContext.getCurrentUser();


		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		ModelAndView mv = new ModelAndView("alipaySuccess");
		//——请在这里编写您的程序（以下代码仅作参考）——
		//signVerified = true;
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			// 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
			orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);


			OrderPackage noBuyOp = orderPackageService.findMaxOrderPackageByOrderNum(out_trade_no);
			if(noBuyOp!=null && noBuyOp.getOrderNum()!=null && !"".equals(noBuyOp.getOrderNum())){
				noBuyOp.setStatus(1);
				noBuyOp.setUpdateUserId(user.getId());
				noBuyOp.setFlowNum(trade_no);
				noBuyOp.setPaymentAmount(Double.valueOf(total_amount));

				orderPackageService.updateStatusIsBuyedForOrderNum(noBuyOp);
			}


			Orders order = orderService.getOrderById(out_trade_no);
            //更新产品表中是否付款为（已付款）
			commodityService.updateIsPayIs(order.getProductId());

			OrderPriceSystem product = orderPriceSystemservice.queryById(order.getProductId());

			//根据 用户ID查询 当前登录人的购买二维码的剩余数量
			OrderPackage p = orderPackageService.findSumAllSutplusQuantityByUserId( user.getId());
			//更新 用户信息中的二维码剩余量的信息
			user.setSurplusQRcodeDesc("，您剩余商品码数还有"+String.valueOf(p.getSurplusQuentity())+"个。");
			userService.updateSurplusQRcodeDescById(user);
/*
			Commodity c  = commodityService.get(order.getProductId());
			Product product = new Product();
			product.setId(c.getId());
			product.setName(c.getName());
			product.setPrice(product_Price);*/

			//Product product = productService.getProductById(order.getProductId());

			log.info("********************** 支付成功(支付宝同步通知) **********************");
    		log.info("* 订单号: {}", out_trade_no);
    		log.info("* 支付宝交易号: {}", trade_no);
    		log.info("* 实付金额: {}", total_amount);
    		log.info("* 购买产品: {}", product.getName());
    		log.info("***************************************************************");


    		mv.addObject("out_trade_no", out_trade_no);
    		mv.addObject("trade_no", trade_no);
    		mv.addObject("total_amount", total_amount);
    		mv.addObject("productName", product.getName());

		}else {
			log.info("支付, 验签失败...");
		}

		return mv;
	}

	/**
	 *
	 * @Title: AlipayController.java
	 * @Package com.sihai.controller
	 * @Description: 支付宝异步 通知页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *
	 * @author sihai
	 * @date 2017年8月23日 下午8:51:13
	 * @version V1.0
	 */
	@RequestMapping(value = "/alipayNotifyNotice")
	@ResponseBody
	public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		log.info("支付成功, 进入异步通知接口...");

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知

				// 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
				orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

				Orders order = orderService.getOrderById(out_trade_no);
				//Product product = productService.getProductById(order.getProductId());

				//更新产品表中是否付款为（已付款）
				commodityService.updateIsPayIs(order.getProductId());
				//更新产品表中是否付款为（已付款） 由10更改为20
				//orderPriceSystemservice.updateState20ById(order.getProductId());

				Commodity c  = commodityService.get(order.getProductId());
				Product product = new Product();
				product.setId(c.getId());
				product.setName(c.getName());
				product.setPrice(product_Price);


				log.info("********************** 支付成功(支付宝异步通知) **********************");
	    		log.info("* 订单号: {}", out_trade_no);
	    		log.info("* 支付宝交易号: {}", trade_no);
	    		log.info("* 实付金额: {}", total_amount);
	    		log.info("* 购买产品: {}", product.getName());
	    		log.info("***************************************************************");
			}
			log.info("支付成功...");

		}else {//验证失败
			log.info("支付, 验签失败...");
		}

		return "success";
	}

}
