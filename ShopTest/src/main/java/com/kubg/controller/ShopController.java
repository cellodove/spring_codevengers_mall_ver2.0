package com.kubg.controller;



import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kubg.dto.CartDTO;
import com.kubg.dto.CartListDTO;
import com.kubg.dto.GoodsReviewDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.MemberDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderDetailDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyDTO;
import com.kubg.dto.ReplyListDTO;
import com.kubg.service.ShopService;

@Controller
@RequestMapping("/shop/*")
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Inject
	ShopService shopService;
	
	//카테고리별 상품 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(@RequestParam("c") int catecode, @RequestParam("l") int level, Model model) throws Exception {
		logger.info("get list");
		List<GoodsViewDTO> list = null;
		list = shopService.list(catecode, level);
		model.addAttribute("list", list);
	}
	//상품조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("n") int gdsnum, Model model) throws Exception {
		logger.info("get view");
		
		GoodsViewDTO goodsViewDTO = shopService.goodsview(gdsnum);
		model.addAttribute("view", goodsViewDTO);
		
//		소감 목록을 읽어오는 메서드가 따로 생성되었으니, 
//		상품 조회용 메서드에 있는 소감 목록 부분은 이제 필요가 없으니 주석처리합니다.
//		List<ReplyListDTO> replyListDTO = shopService.replyList(gdsnum);
//		model.addAttribute("reply", replyListDTO);
	}
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public void getReview(@RequestParam("n") int gdsnum, Model model) throws Exception {
		logger.info("get review");
		
		GoodsReviewDTO goodsReviewDTO = shopService.goodsReview(gdsnum);
		model.addAttribute("review", goodsReviewDTO);

	}
	//상품조회 - 소감(댓글) 작성
//	@RequestMapping(value = "/view", method = RequestMethod.POST)
//	public String registReply(ReplyDTO replyDTO, HttpSession httpSession) throws Exception {
//		logger.info("regist reply");
//		MemberDTO memberDTO = (MemberDTO) httpSession.getAttribute("member");
//		replyDTO.setMem_id(memberDTO.getMem_id());
//		shopService.registReply(replyDTO);
//		return "redirect:/shop/view?n=" + replyDTO.getGdsnum();
//	}
	//상품소감(댓글) 작성/view/registReply
	@ResponseBody
	@RequestMapping(value = "/view/registReply", method = RequestMethod.POST)
	public void registReply(ReplyDTO replyDTO, HttpSession httpSession) throws Exception {
		logger.info("regist reply");
		
		MemberDTO memberDTO = (MemberDTO) httpSession.getAttribute("member");
		replyDTO.setMem_id(memberDTO.getMem_id());
		
		shopService.registReply(replyDTO);
	}

	//상품소감(댓글) 목록
	@ResponseBody
	@RequestMapping(value = "/view/replyList", method = RequestMethod.GET)
	public List<ReplyListDTO> getReplyList(@RequestParam("n") int gdsnum) throws Exception {
		logger.info("get reply list");
		List<ReplyListDTO> replyListDTO = shopService.replyList(gdsnum);
		return replyListDTO;
	}
	//상품소감삭제
	@ResponseBody
	@RequestMapping(value = "/view/deleteReply", method = RequestMethod.POST)
	public int getReplyList(ReplyDTO replyDTO, HttpSession httpSession) throws Exception {
		logger.info("post delete reply");
		int result = 0;
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = shopService.idCheck(replyDTO.getRepnum());
		if (memberDTO.getMem_id().equals(mem_id)) {
			replyDTO.setMem_id(memberDTO.getMem_id());
			shopService.deleteReply(replyDTO);
			result = 1;
		}
		return result;
	}
	//상품소감수정
	@ResponseBody
	@RequestMapping(value = "/view/modifyReply", method = RequestMethod.POST)
	public int modifyReply(ReplyDTO replyDTO, HttpSession httpSession) throws Exception {
		logger.info("modify reply");
		int result = 0;
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = shopService.idCheck(replyDTO.getRepnum());
		if (memberDTO.getMem_id().equals(mem_id)) {
			replyDTO.setMem_id(memberDTO.getMem_id());
			shopService.modifyReply(replyDTO);
			result = 1;
		}
		return result;
	}
	//카트담기
	@ResponseBody
	@RequestMapping(value = "/view/addCart", method = RequestMethod.POST)
	public int addCart(CartDTO cartDTO, HttpSession httpSession) throws Exception {
		int result = 0;
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		if (memberDTO != null) {
			cartDTO.setMem_id(memberDTO.getMem_id());
			shopService.addCart(cartDTO);
			result = 1;
		}
		return result;
	}
	//카트목록
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public void getCartList(HttpSession httpSession, Model model) throws Exception {
		logger.info("get cart list");
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = memberDTO.getMem_id();
		List<CartListDTO> cartList = shopService.cartList(mem_id);
		model.addAttribute("cartList", cartList);
	}
	//카트삭제
	@ResponseBody
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
	public int deleteCart(HttpSession httpSession,
			@RequestParam(value = "chbox[]") List<String> chArr, CartDTO cartDTO) throws Exception {
		logger.info("delete cart");
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = memberDTO.getMem_id();
		int result = 0;
		int cartnum = 0;
		if (memberDTO != null) {
			cartDTO.setMem_id(mem_id);
			for (String i : chArr) {
				cartnum = Integer.parseInt(i);
				cartDTO.setCartnum(cartnum);
				shopService.deleteCart(cartDTO);
			}
			result = 1;
		}
		return result;
	}
	//주문
	@RequestMapping(value = "/cartList", method = RequestMethod.POST)
	public String order(HttpSession httpSession, OrderDTO orderDTO, OrderDetailDTO orderDetailDTO) throws Exception {
		logger.info("order");
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = memberDTO.getMem_id();
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		String ymd = ym + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		String subNum = "";
		
		for (int i = 1; i <= 6; i++) {
			subNum += (int)(Math.random() * 10);
		}
		String orderid = ymd + "_" + subNum;
		
		orderDTO.setOrderid(orderid);
		orderDTO.setMem_id(mem_id);
		
		shopService.orderInfo(orderDTO);
		
		orderDetailDTO.setOrderid(orderid);
		shopService.orderInfo_Details(orderDetailDTO);
		
		shopService.cartAllDelete(mem_id);
		
		return "redirect:/shop/orderList";
	}
	//주문 목록
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public void getOrderList(HttpSession httpSession, OrderDTO orderDTO, Model model) throws Exception {
		logger.info("get order list");
		
		MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
		String mem_id = memberDTO.getMem_id();
		
		orderDTO.setMem_id(mem_id);
		
		List<OrderDTO> orderList = shopService.orderList(orderDTO);
		
		model.addAttribute("orderList", orderList);
	}
	// 주문 상세 목록
	@RequestMapping(value = "/orderView", method = RequestMethod.GET)
	public void getOrderList(HttpSession httpSession,
	      @RequestParam("n") String orderid,
	      OrderDTO orderDTO, Model model) throws Exception {
	 logger.info("get order view");
	 
	 MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
	 String mem_id = memberDTO.getMem_id();
	 String delivery = orderDTO.getDelivery();
	 
	 orderDTO.setMem_id(mem_id);
	 orderDTO.setOrderid(orderid);
	 orderDTO.setDelivery(delivery);
	 
	 List<OrderListDTO> orderView = shopService.orderView(orderDTO);
	 
	 model.addAttribute("orderView", orderView);
	}
	
	
	
}
