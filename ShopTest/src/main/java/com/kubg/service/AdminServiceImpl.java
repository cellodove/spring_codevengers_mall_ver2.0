package com.kubg.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kubg.dao.AdminDAO;
import com.kubg.dto.CategoryDTO;
import com.kubg.dto.GoodsDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyListDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adminDAO;
	//카테고리
	@Override
	public List<CategoryDTO> category() throws Exception {
		return adminDAO.category();
	}
	//상품등록
	@Override
	public void register(GoodsDTO goodsDTO) throws Exception {
		adminDAO.register(goodsDTO);
	}
	//상품목록
	@Override
	public List<GoodsViewDTO> goodslist() throws Exception {
		System.out.println("service");
		return adminDAO.goodslist();
	}
	//상품조회, 카테고리조인
	@Override
	public GoodsViewDTO goodsView(int gdsnum) throws Exception {
		return adminDAO.goodsView(gdsnum);
	}
	//상품수정
	@Override
	public void goodsModify(GoodsDTO goodsDTO) throws Exception {
		adminDAO.goodsModify(goodsDTO);
	}
	//상품삭제
	@Override
	public void goodsDelete(int gdsnum) throws Exception {
		adminDAO.goodsDelete(gdsnum);
	}
	//주문목록
	@Override
	public List<OrderDTO> adminOrderList() throws Exception {
		return adminDAO.adminOrderList();
	}
	//특정주문목록
	@Override
	public List<OrderListDTO> adminOrderView(OrderDTO orderDTO) throws Exception {
		return adminDAO.adminOrderView(orderDTO);
	}
	//배송상태
	@Override
	public void delivery(OrderDTO orderDTO) throws Exception {
		adminDAO.delivery(orderDTO);
	}
	//상품수량조절
	@Override
	public void changeStock(GoodsDTO goodsDTO) throws Exception {
		adminDAO.changeStock(goodsDTO);
	}
	//모든댓글
	@Override
	public List<ReplyListDTO> allReply() throws Exception {
		return adminDAO.allReply();
	}
	//댓글삭제
	@Override
	public void adminDeleteReply(int repnum) throws Exception {
		adminDAO.adminDeleteReply(repnum);
	}
	
}
