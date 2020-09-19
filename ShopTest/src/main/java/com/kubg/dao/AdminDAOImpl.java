package com.kubg.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kubg.dto.CategoryDTO;
import com.kubg.dto.GoodsDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyListDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession sqlSession;
	//카테고리
	@Override
	public List<CategoryDTO> category() throws Exception {
		return sqlSession.selectList("category");
	}
	//상품등록
	@Override
	public void register(GoodsDTO goodsDTO) throws Exception {
		sqlSession.insert("register", goodsDTO);
	}
	//상품목록
	@Override
	public List<GoodsViewDTO> goodslist() throws Exception {
		return sqlSession.selectList("goodsList");
	}
	//상품자세히
	@Override
	public GoodsViewDTO goodsView(int gdsnum) throws Exception {
		return sqlSession.selectOne("goodsView", gdsnum);
	}
	//상품수정
	@Override
	public void goodsModify(GoodsDTO goodsDTO) throws Exception {
		sqlSession.update("goodsModify", goodsDTO);
	}
	//상품삭제
	@Override
	public void goodsDelete(int gdsnum) throws Exception {
		sqlSession.delete("goodsDelete", gdsnum);
	}
	//주문목록
	@Override
	public List<OrderDTO> adminOrderList() throws Exception {
		return sqlSession.selectList("adminOrderList");
	}
	//특정주문목록
	@Override
	public List<OrderListDTO> adminOrderView(OrderDTO orderDTO) throws Exception {
		return sqlSession.selectList("adminOrderView", orderDTO);
	}
	//배송상태
	@Override
	public void delivery(OrderDTO orderDTO) throws Exception {
		sqlSession.update("delivery", orderDTO);
	}
	//상품수량조절
	@Override
	public void changeStock(GoodsDTO goodsDTO) throws Exception {
		sqlSession.update("changeStock", goodsDTO);
	}
	//모든댓글
	@Override
	public List<ReplyListDTO> allReply() throws Exception {
		return sqlSession.selectList("allReply");
	}
	//댓글삭제
	@Override
	public void adminDeleteReply(int repnum) throws Exception {
		sqlSession.delete("adminDeleteReply", repnum);
	}

}
