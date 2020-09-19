package com.kubg.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kubg.dao.ShopDAO;
import com.kubg.dto.CartDTO;
import com.kubg.dto.CartListDTO;
import com.kubg.dto.GoodsReviewDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderDetailDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyDTO;
import com.kubg.dto.ReplyListDTO;
@Service
public class ShopServiceImpl implements ShopService {

	@Inject
	private ShopDAO shopDAO;
	
	//카테고리별 상품 리스트
	@Override
	public List<GoodsViewDTO> list(int catecode, int level) throws Exception {
		int catecoderef = 0;
		if (level == 1) {
			catecoderef = catecode;
			return shopDAO.list(catecode, catecoderef);
		} else {
			return shopDAO.list(catecode);
		}
	}
	//상품조회
	@Override
	public GoodsViewDTO goodsview(int gdsnum) throws Exception {
		return shopDAO.goodsview(gdsnum);
	}
	//상품 소감(댓글) 작성
	@Override
	public void registReply(ReplyDTO replyDTO) throws Exception {
		shopDAO.registerReply(replyDTO);
	}
	//상품 소감(댓글) 리스트
	@Override
	public List<ReplyListDTO> replyList(int gdsnum) throws Exception {
		return shopDAO.replyList(gdsnum);
	}
	//상품소감삭제
	@Override
	public void deleteReply(ReplyDTO replyDTO) throws Exception {
		shopDAO.deleteReply(replyDTO);
	}
	//아이디체크
	@Override
	public String idCheck(int repnum) throws Exception {
		return shopDAO.idCheck(repnum);
	}
	//상품소감수정
	@Override
	public void modifyReply(ReplyDTO replyDTO) throws Exception {
		shopDAO.modifyReply(replyDTO);
	}
	//카트담기
	@Override
	public void addCart(CartDTO cartDTO) throws Exception {
		shopDAO.addCart(cartDTO);
	}
	//카트리스트
	@Override
	public List<CartListDTO> cartList(String mem_id) throws Exception {
		return shopDAO.cartList(mem_id);
	}
	//카트삭제
	@Override
	public void deleteCart(CartDTO cartDTO) throws Exception {
		shopDAO.deleteCart(cartDTO);
		
	}
	//주문정보
	@Override
	public void orderInfo(OrderDTO orderDTO) throws Exception {
		shopDAO.orderInfo(orderDTO);
	}
	//주문상세정보
	@Override
	public void orderInfo_Details(OrderDetailDTO orderDetailDTO) throws Exception {
		shopDAO.orderInfo_Details(orderDetailDTO);
	}
	//카트 비우기
	@Override
	public void cartAllDelete(String mem_id) throws Exception {
		shopDAO.cartAllDelete(mem_id);
	}
	//주문 목록
	@Override
	public List<OrderDTO> orderList(OrderDTO orderDTO) throws Exception {
		return shopDAO.orderList(orderDTO);
	}
	//특정주문목록
	@Override
	public List<OrderListDTO> orderView(OrderDTO orderDTO) throws Exception {
		return shopDAO.orderView(orderDTO);
	}
	@Override
	public GoodsReviewDTO goodsReview(int gdsnum) throws Exception {
		return shopDAO.goodsReview(gdsnum);
	}
	
	
}
