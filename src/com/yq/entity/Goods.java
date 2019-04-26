package com.yq.entity;

public class Goods extends Page{
	Long goods_id;
	String goods_name;
	String goods_img;
	float goods_price;
	String goods_detail;
	String add_time;
	Integer ctg_id;
	Integer status;
	Integer is_recommend;
	String goods_spe;
	Integer type;
	Integer goods_num;
	Integer is_coupon;
	String hx_username;
	String hx_oppen_id;
	String good_qr_image;

	String hxyzm;//核销用验证码
	String goods_old_price;//原价
	String order_total;//商品总数
	String single_quantity;//单次能购买的数量
	String sales_start_time;//销售开始的时间
	String sales_end_time;//销售结束的时间
	String use_start_time;//使用开始的时间
	String use_end_time;//使用结束时间
	String del_flag;//删除标记

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getHxyzm() {
		return hxyzm;
	}

	public void setHxyzm(String hxyzm) {
		this.hxyzm = hxyzm;
	}

	public String getGoods_old_price() {
		return goods_old_price;
	}

	public void setGoods_old_price(String goods_old_price) {
		this.goods_old_price = goods_old_price;
	}

	public String getOrder_total() {
		return order_total;
	}

	public void setOrder_total(String order_total) {
		this.order_total = order_total;
	}

	public String getSingle_quantity() {
		return single_quantity;
	}

	public void setSingle_quantity(String single_quantity) {
		this.single_quantity = single_quantity;
	}

	public String getSales_start_time() {
		return sales_start_time;
	}

	public void setSales_start_time(String sales_start_time) {
		this.sales_start_time = sales_start_time;
	}

	public String getSales_end_time() {
		return sales_end_time;
	}

	public void setSales_end_time(String sales_end_time) {
		this.sales_end_time = sales_end_time;
	}

	public String getUse_start_time() {
		return use_start_time;
	}

	public void setUse_start_time(String use_start_time) {
		this.use_start_time = use_start_time;
	}

	public String getUse_end_time() {
		return use_end_time;
	}

	public void setUse_end_time(String use_end_time) {
		this.use_end_time = use_end_time;
	}

	public String getGood_qr_image() {
		return good_qr_image;
	}

	public void setGood_qr_image(String good_qr_image) {
		this.good_qr_image = good_qr_image;
	}

	public String getHx_username() {
		return hx_username;
	}

	public void setHx_username(String hx_username) {
		this.hx_username = hx_username;
	}

	public String getHx_oppen_id() {
		return hx_oppen_id;
	}

	public void setHx_oppen_id(String hx_oppen_id) {
		this.hx_oppen_id = hx_oppen_id;
	}

	public Integer getIs_coupon() {
		return is_coupon;
	}

	public void setIs_coupon(Integer is_coupon) {
		this.is_coupon = is_coupon;
	}

	public Long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_img() {
		return goods_img;
	}

	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}


	public float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public Integer getCtg_id() {
		return ctg_id;
	}

	public void setCtg_id(Integer ctg_id) {
		this.ctg_id = ctg_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public Integer getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getGoods_spe() {
		return goods_spe;
	}

	public void setGoods_spe(String goods_spe) {
		this.goods_spe = goods_spe;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}

}
