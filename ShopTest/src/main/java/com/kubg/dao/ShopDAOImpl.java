package com.kubg.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kubg.dto.CartDTO;
import com.kubg.dto.CartListDTO;
import com.kubg.dto.GoodsReviewDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderDetailDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyDTO;
import com.kubg.dto.ReplyListDTO;
@Repository
public class ShopDAOImpl implements ShopDAO {

	@Inject
	private SqlSession sqlSession;
	//카테고리별 상품 리스트 - 1차 분류
	@Override
	public List<GoodsViewDTO> list(int catecode, int catecoderef) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("catecode", catecode);
		map.put("catecoderef", catecoderef);
		
		return sqlSession.selectList("list_1", map);
	}
	//카테고리별 상품 리스트 - 2차 분류
	@Override
	public List<GoodsViewDTO> list(int catecode) throws Exception {
		return sqlSession.selectList("list_2", catecode);
	}
	//상품조회
	@Override
	public GoodsViewDTO goodsview(int gdsnum) throws Exception {
		return sqlSession.selectOne("goodsView", gdsnum);
	}
	//상품 소감(댓글) 작성
	@Override
	public void registerReply(ReplyDTO replyDTO) throws Exception {
		sqlSession.insert("registReply", replyDTO);
	}
	//상품 소감(댓글) 리스트
	@Override
	public List<ReplyListDTO> replyList(int gdsnum) throws Exception {
		return sqlSession.selectList("replyList", gdsnum);
	}
	//상품소감삭제
	@Override
	public void deleteReply(ReplyDTO replyDTO) throws Exception {
		sqlSession.delete("deleteReply", replyDTO);
		
	}
	//아이디체크
	@Override
	public String idCheck(int repnum) throws Exception {
		return sqlSession.selectOne("replyMem_idCheck", repnum);
	}
	//상품소감수정
	@Override
	public void modifyReply(ReplyDTO replyDTO) throws Exception {
		sqlSession.update("modifyReply", replyDTO);
	}
	//카트담기
	@Override
	public void addCart(CartDTO cartDTO) throws Exception {
		sqlSession.insert("addCart", cartDTO);
	}
	//카트리스트
	@Override
	public List<CartListDTO> cartList(String mem_id) throws Exception {
		return sqlSession.selectList("cartList", mem_id);
	}
	//카트삭제
	@Override
	public void deleteCart(CartDTO cartDTO) throws Exception {
		sqlSession.delete("deleteCart", cartDTO);
	}
	//주문정보
	@Override
	public void orderInfo(OrderDTO orderDTO) throws Exception {
		sqlSession.insert("orderInfo", orderDTO);
	}
	//주문상세정보
	@Override
	public void orderInfo_Details(OrderDetailDTO orderDetailDTO) throws Exception {
		sqlSession.insert("orderInfo_Details", orderDetailDTO);
	}
	//카트 비우기
	@Override
	public void cartAllDelete(String mem_id) throws Exception {
		sqlSession.delete("cartAllDelete", mem_id);
	}
	//주문 목록
	@Override
	public List<OrderDTO> orderList(OrderDTO orderDTO) throws Exception {
		return sqlSession.selectList("orderList", orderDTO);
	}
	//특정주문목록
	@Override
	public List<OrderListDTO> orderView(OrderDTO orderDTO) throws Exception {
		return sqlSession.selectList("orderView", orderDTO);
	}
	@Override
	public GoodsReviewDTO goodsReview(int gdsnum) throws Exception {
		return sqlSession.selectOne("goodsReview", gdsnum);
	}
	
}
