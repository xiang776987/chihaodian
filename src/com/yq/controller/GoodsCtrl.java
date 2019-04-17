package com.yq.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.weixin.entity.WxSetting;
import com.weixin.service.WxSettingService;
import com.weixin.util.WxUtil;
import com.yq.service.*;
import com.yq.util.QRCodeUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yq.util.StringUtil;
import com.yq.util.PageUtil;
import com.yq.entity.Address;
import com.yq.entity.Category;
import com.yq.entity.Coupons;
import com.yq.entity.Freight;
import com.yq.entity.Goods;
import com.yq.entity.User;

import static com.yq.controller.OrderCtrl.getId;

@Controller
@RequestMapping("/")
public class GoodsCtrl extends StringUtil {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	private Goods goods = new Goods();
	private Category category = new Category();
	@Autowired
	private CouponsService couponsService;
	private Coupons coupons = new Coupons();
	@Autowired
	private AddressService addressService;
	private Address address = new Address();
	@Autowired
	private FreightService freightService;
	private Freight freight = new Freight();
	@Autowired
	private UserService userService;
	private User user = new User();
	
	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	@RequestMapping(value = "/main/goodsAddjsp.html")
	public ModelAndView addjsp() {
		ModelAndView ml = new ModelAndView();
		category.setStatus(1);
		List<Category> list = categoryService.list(category);
		ml.addObject("category", list);
		ml.setViewName("main/goods/add");
		return ml;
	}

	@ResponseBody
	@RequestMapping(value = "/main/goodsInsert.html")
	public String insert(String goods_name, String goods_img,String goods_spe,
			Float goods_price, String goods_detail, Integer ctg_id,
			Integer status,Integer type,Integer is_coupon,HttpServletRequest request) throws Exception {



		String add_time =sf.format(new Date());
//		try {
//		goods_name = new String(goods_name.getBytes("iso8859-1"),"utf-8");
		String goods_id = getId();
		String localRedirect_uri = "/page/goodHxWxUser.html?goods_id="+ goods_id;
		String qrPath = "/upload/goodQr/";
		String qrName = goods_id + ".jpg";


		AbstractApplicationContext ctx   = new ClassPathXmlApplicationContext(new String []{"classpath:applicationContext.xml"});
		WxSettingService wxSettingService =(WxSettingService)ctx.getBean("wxSettingService") ;
		WxSetting wxSetting  =  wxSettingService.selectByPrimaryKey(1);
		String realpath = request.getSession().getServletContext().getRealPath("");
		String path = "";
		if(realpath.contains("\\")){
			path = realpath.substring(0,realpath.lastIndexOf("\\"));
		}else{
			path = realpath.substring(0,realpath.lastIndexOf("/"));
		}
		String redirect_uri = wxSetting.getLink()+localRedirect_uri;
		String text ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxSetting.getAppid()+"&redirect_uri="+ URLEncoder.encode(redirect_uri)
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
		QRCodeUtil.encode(text,path+"/upload/qr_img.jpg", path+qrPath,true,qrName);

		String good_qr_image = qrPath + qrName;
		goods_name = java.net.URLDecoder.decode(goods_name,"utf-8") ;
		map.put("goods_id", goods_id);
		map.put("goods_name", goods_name);
		map.put("goods_img", goods_img);
		map.put("goods_spe", goods_spe);
		map.put("goods_price", goods_price);
		map.put("goods_detail", goods_detail);
		map.put("add_time", add_time);
		map.put("ctg_id", ctg_id);
		map.put("is_coupon", is_coupon);
		map.put("good_qr_image", good_qr_image);
		map.put("status", 1);
		map.put("type", 1);
		return goodsService.insert(map) + "";
	}

//	private String createGoodsQr(HttpServletRequest request,  String goods_id) throws Exception {
//		AbstractApplicationContext ctx   = new ClassPathXmlApplicationContext(new String []{"classpath:applicationContext.xml"});
//		WxSettingService wxSettingService =(WxSettingService)ctx.getBean("wxSettingService") ;
//		WxSetting wxSetting  =  wxSettingService.selectByPrimaryKey(1);
//		String realpath = request.getSession().getServletContext().getRealPath("");
//		String path = "";
//		if(realpath.contains("\\")){
//			path = realpath.substring(0,realpath.lastIndexOf("\\"));
//		}else{
//			path = realpath.substring(0,realpath.lastIndexOf("/"));
//		}
//
//		String goodQrPath = "/upload/goodQr/";
//		String googQrName = goods_id + ".jpg";
//		String redirect_uri = wxSetting.getLink()+"/page/goodHxWxUser.html?goods_id="+ goods_id;
//		String text ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wxSetting.getAppid()+"&redirect_uri="+ URLEncoder.encode(redirect_uri)
//				+ "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
//
//		QRCodeUtil.encode(text,"", path+goodQrPath,true,googQrName);
//		return goodQrPath + googQrName;
//	}

	@ResponseBody
	@RequestMapping(value = "/main/goodsUpdate.html")
	public Object update(String goods_name, String goods_img,String goods_spe,
			Float goods_price, String goods_detail, String add_time,
			Integer ctg_id, Long goods_id,Integer is_coupon) throws UnsupportedEncodingException {
		goods_name = java.net.URLDecoder.decode(goods_name,"utf-8") ;
		map.put("goods_name", goods_name);
		map.put("goods_img", goods_img);		
		map.put("goods_spe", goods_spe);
		map.put("goods_price", goods_price);
		map.put("goods_detail", goods_detail);
		map.put("add_time", add_time);
		map.put("ctg_id", ctg_id);
		map.put("goods_id", goods_id);
		map.put("is_coupon", is_coupon);
		map.put("type", 1);
		return goodsService.update(map) + "";

	}

	@ResponseBody
	@RequestMapping(value = "/main/goodsUpstatus.html")
	public Object upstatus(Long goods_id, Integer status) {
		map.put("status", status);
		map.put("goods_id", goods_id);
		return goodsService.upstatus(map) + "";
	}

	@ResponseBody
	@RequestMapping(value = "/main/goodsUpisrec.html")
	public Object upisrec(Long goods_id, Integer is_recommend) {
		map.put("is_recommend", is_recommend);
		map.put("goods_id", goods_id);
		return goodsService.upisrec(map) + "";
	}

    /**
     * 添加商品的核销人微信id和微信名称
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page/goodHxWxUser.html")
    public Object hxWxUser(HttpSession session,Long goods_id,HttpServletRequest request) {

        if(goods_id==null||"".equals(goods_id)){
            return "商品不能为空";
        }
        Goods parameterGoods = new Goods();
        Goods returnGoods = new Goods();
        parameterGoods.setGoods_id(goods_id);
        List<Goods> list = goodsService.listById(parameterGoods);
        if (list.size()==0){
            return "商品异常";
        }
        returnGoods = list.get(0);
        if (returnGoods.getHx_oppen_id()!=null&&!"".equals(returnGoods.getHx_oppen_id())&&"null".equals(returnGoods.getHx_oppen_id())){
            return "此商品已有核销人员，如要更换核销人员请联系管理员";
        }
		try {
			String oppen_id = "";
			String hx_username = "";

                oppen_id = getOppen_id(session);
			System.out.println("==============================进入goodHxWxUser");
			System.out.println("这个是session里面的oppen_id"+getOppen_id(session));
                if (oppen_id==null||"".equals(oppen_id)){
					map = WxUtil.oppenIdInfo(request, session);
					oppen_id = (String) map.get("oppen_id");
					hx_username = (String) map.get("realname");
				}else {
					user.setOppen_id(oppen_id);
					List<User> userList = userService.listById(user);
					hx_username = userList.get(0).getRealname();
				}
				if(oppen_id==null||"null".equals(oppen_id)){
                	return "核销失败";
				}

//                hx_username ="wum";
//
//                System.out.println("==============================进入goodHxWxUser");
//				System.out.println("这个是session里面的oppen_id"+getOppen_id(session)); ;
//                map = WxUtil.oppenIdInfo(request, session);
//                oppen_id = (String) map.get("oppen_id");
//                hx_username = (String) map.get("realname");
//                System.out.println("==========================oppen_id:"+oppen_id);

			Map<String,Object> map = new HashMap();
			map.put("hx_oppen_id",oppen_id);
			map.put("hx_username",hx_username);
			map.put("goods_id",goods_id);
			int i =  goodsService.hxWxUser(map);
		} catch (Exception e) {
			return "添加失败";
		}
		return  "添加成功";
    }


    @RequestMapping(value = "/main/goodsList.html")
	public ModelAndView list(Integer status,@RequestParam(defaultValue = "") String goods_name,
			@RequestParam(defaultValue = "0") Integer ctg_id,
			@RequestParam(defaultValue = "1") Integer currentPage,
			HttpServletRequest request) throws UnsupportedEncodingException {
		goods_name = java.net.URLDecoder.decode(goods_name,"utf-8") ;
		goods.setStatus(status);
		goods.setGoods_name(goods_name);
		goods.setCtg_id(ctg_id);
		goods.setType(1);
		goods.setIs_recommend(0);
		System.out.println(request.getParameter("goods_name"));
		int total = goodsService.count(goods);
		PageUtil.pager(currentPage, pagesize_1, total, request);
		goods.setPageSize(pagesize_1);
		goods.setCurrentNum(PageUtil.currentNum(currentPage, pagesize_1));
		List<Goods> list = goodsService.list(goods);
		ModelAndView ml = new ModelAndView();
		ml.addObject("goods", list);
		ml.addObject("goods_name", goods_name);
		ml.setViewName("main/goods/list");
		return ml;
	}

	@RequestMapping(value = "/main/goodsListById.html")
	public ModelAndView listById(Long goods_id) {
		// addjsp();
		goods.setGoods_id(goods_id);
		List<Goods> list = goodsService.listById(goods);
		ModelAndView ml = new ModelAndView();
		category.setStatus(1);
		List<Category> ctg = categoryService.list(category);
		ml.addObject("category", ctg);
		ml.addObject("list", list);
		ml.setViewName("main/goods/info");
		return ml;
	}

	/**
	 * 根据商品id查询商品详情
	 * 
	 * @param goods_id
	 * @return
	 */
	@RequestMapping(value = "/page/goodsListById.html")
	public ModelAndView goodsListById(Long goods_id) {
		goods.setGoods_id(goods_id);
		List<Goods> list = goodsService.listById(goods);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.addObject("goods_id", goods_id);
		ml.setViewName("page/goods-info");
		return ml;
	}

	/**
	 * 根据商品id查询商品详情
	 * 
	 * @param goods_id
	 * @return
	 */
	@RequestMapping(value = "/page/goodsOrder.html")
	public ModelAndView goodsOrder(Long goods_id) {
		goods.setGoods_id(goods_id);
		List<Goods> list = goodsService.listById(goods);
		list.get(0).setGoods_num(1);
		ModelAndView ml = new ModelAndView();
		ml.addObject("goods", list);
		ml.addObject("goods_id", goods_id);
		ml.setViewName("page/goods-order");
		return ml;
	}



	/**
	 * 接收二维码
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/twoCode.html")
	@ResponseBody
	public Object twoCode(HttpServletRequest request) throws IOException {
		System.out.println("-----------------------------------");
		JSONObject data = new JSONObject();
		String accessToken = TwoCode.getToken();
		System.out.println("accessToken;" + accessToken);
		String twoCodeUrl = TwoCode.getminiqrQr(accessToken, request);
		System.out.println("twoCodeUrl:"+twoCodeUrl);
		data.put("twoCodeUrl", twoCodeUrl);
		return data;
	}



	@RequestMapping(value = "/page/secGoodsList.html")
	public ModelAndView secGoodsList(String goods_name,
			@RequestParam(defaultValue = "0") Integer is_recommend,
			@RequestParam(defaultValue = "1") Integer status,
			@RequestParam(defaultValue = "0") Integer ctg_id,
			@RequestParam(defaultValue = "1") Integer currentPage,
			HttpServletRequest request) {
//		try {
			if (StringUtils.isNotEmpty(goods_name)) {
//				goods_name = new String(goods_name.getBytes("iso-8859-1"),
//						"utf-8");
			}
			goods.setType(1);
			goods.setStatus(status);
			goods.setCtg_id(ctg_id);
			goods.setGoods_name(goods_name);
			goods.setIs_recommend(is_recommend);
			List<Goods> list = goodsService.list(goods);
			ModelAndView ml = new ModelAndView();
			ml.addObject("goods", list);
			ml.setViewName("page/goods-list");
			return ml;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
	}
	


}
