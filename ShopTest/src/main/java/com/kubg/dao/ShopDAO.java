package com.kubg.dao;

import java.util.List;

import com.kubg.dto.CartDTO;
import com.kubg.dto.CartListDTO;
import com.kubg.dto.GoodsReviewDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderDetailDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyDTO;
import com.kubg.dto.ReplyListDTO;

public interface ShopDAO {
	//카테고리별 상품 리스트
	//public List<GoodsViewDTO> list(int catecode) throws Exception;
	//카테고리별 상품 리스트 - 1차 분류
	public List<GoodsViewDTO> list(int catecode, int catecoderef) throws Exception;
	//카테고리별 상품 리스트 - 2차 분류
	public List<GoodsViewDTO> list(int catecode) throws Exception;
	//상품조회
	public GoodsViewDTO goodsview(int gdsnum) throws Exception;
	//상품 소감(댓글) 작성
	public void registerReply(ReplyDTO replyDTO) throws Exception;
	//상품 소감(댓글) 리스트
	public List<ReplyListDTO> replyList(int gdsnum) throws Exception;
	//상품소감삭제
	public void deleteReply(ReplyDTO replyDTO) throws Exception;
	//아이디체크
	public String idCheck(int repnum) throws Exception;
	//상품소감수정
	public void modifyReply(ReplyDTO replyDTO) throws Exception;
	//카트담기
	public void addCart(CartDTO cartDTO) throws Exception;
	//카트리스트
	public List<CartListDTO> cartList(String mem_id) throws Exception;
	//카트 삭제
	public void deleteCart(CartDTO cartDTO) throws Exception;
	//주문 정보
	public void orderInfo(OrderDTO orderDTO) throws Exception;
	//주문 상세 정보
	public void orderInfo_Details(OrderDetailDTO orderDetailDTO) throws Exception;
	//카트 비우기
	public void cartAllDelete(String mem_id) throws Exception;
	//주문 목록
	public List<OrderDTO> orderList(OrderDTO orderDTO) throws Exception;
	//특정주문목록
	public List<OrderListDTO> orderView(OrderDTO orderDTO) throws Exception;
	//상품조회
	public GoodsReviewDTO goodsReview(int gdsnum) throws Exception;
	
}
