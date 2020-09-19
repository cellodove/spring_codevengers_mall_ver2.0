package com.kubg.dao;

import java.util.List;

import com.kubg.dto.CategoryDTO;
import com.kubg.dto.GoodsDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyListDTO;

public interface AdminDAO {
	//카테고리
	public List<CategoryDTO> category() throws Exception;
	//상품등록
	public void register(GoodsDTO goodsDTO) throws Exception;
	//상품목록
	public List<GoodsViewDTO> goodslist() throws Exception;
	//상품조회
	public GoodsViewDTO goodsView(int gdsnum) throws Exception;
	//상품수정
	public void goodsModify(GoodsDTO goodsDTO) throws Exception;
	//상품삭제
	public void goodsDelete(int gdsnum) throws Exception;
	//주문목록
	public List<OrderDTO> adminOrderList() throws Exception;
	//특정 주문 목록
	public List<OrderListDTO> adminOrderView(OrderDTO orderDTO) throws Exception;
	//배송상태
	public void delivery(OrderDTO orderDTO) throws Exception;
	//상품수량조절
	public void changeStock(GoodsDTO goodsDTO) throws Exception;
	//모든소감(댓글)
	public List<ReplyListDTO> allReply() throws Exception;
	//소감(댓글)삭제
	public void adminDeleteReply(int repnum) throws Exception;
}
