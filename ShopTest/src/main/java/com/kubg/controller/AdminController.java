package com.kubg.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.kubg.dto.CategoryDTO;
import com.kubg.dto.GoodsDTO;
import com.kubg.dto.GoodsViewDTO;
import com.kubg.dto.OrderDTO;
import com.kubg.dto.OrderListDTO;
import com.kubg.dto.ReplyDTO;
import com.kubg.dto.ReplyListDTO;
import com.kubg.service.AdminService;
import com.kubg.utils.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 관리자 화면
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void getIndex() throws Exception {
		logger.info("get index");
	}
	// 상품등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception {
		logger.info("get goods register");
		
		List<CategoryDTO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	// 상품 등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsDTO goodsDTO, MultipartFile file) throws Exception {
	 
	 String imgUploadPath = uploadPath + File.separator + "imgUpload";  // 이미지를 업로드할 폴더를 설정 = /uploadPath/imgUpload
	 String ymdPath = UploadFileUtils.calcPath(imgUploadPath);  // 위의 폴더를 기준으로 연월일 폴더를 생성
	 String fileName = null;  // 기본 경로와 별개로 작성되는 경로 + 파일이름
	   
	 if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
	  // 파일 인풋박스에 첨부된 파일이 없다면(=첨부된 파일이 이름이 없다면)
	  
	  fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

	  // gdsImg에 원본 파일 경로 + 파일명 저장
	  goodsDTO.setGdsimg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
	  // gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
	  goodsDTO.setGdsthumbimg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
	  
	 } else {  // 첨부된 파일이 없으면
	  fileName = File.separator + "images" + File.separator + "none.png";
	  // 미리 준비된 none.png파일을 대신 출력함
	  
	  goodsDTO.setGdsimg(fileName);
	  goodsDTO.setGdsthumbimg(fileName);
	 }
	      
	 adminService.register(goodsDTO);
	 
	 return "redirect:/admin/index";
	}
	//상품목록
	@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
	public void postGoodsList(Model model) throws Exception {
		logger.info("get goods list");
		List<GoodsViewDTO> list = adminService.goodslist();
		model.addAttribute("list", list);
	}
	//상품목록자세히
	@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
	public void getGoodsview(@RequestParam("n") int gdsnum, Model model) throws Exception {
		logger.info("get goods view");
		GoodsViewDTO goods = adminService.goodsView(gdsnum);
		model.addAttribute("goods", goods);
	}
	//상품수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsnum, Model model) throws Exception {
		logger.info("get goods modify");
		
		GoodsViewDTO goods = adminService.goodsView(gdsnum);
		model.addAttribute("goods", goods);
		List<CategoryDTO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category));
	}
	//상품수정
	@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsDTO goodsDTO, MultipartFile file, HttpServletRequest request) throws Exception {
		logger.info("post goods modify");
		//새로운 파일이 등록되었는지 확인
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			//기존파일삭제
			new File(uploadPath + request.getParameter("gdsimg")).delete();
			new File(uploadPath + request.getParameter("gdsthumbimg")).delete();
			//새로 첨부한 파일을 등록
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
			
			goodsDTO.setGdsimg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			goodsDTO.setGdsthumbimg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		}else {	//새로운파일이 등록되지 않았다면
			//기본이미지 그대로 사용
			goodsDTO.setGdsimg(request.getParameter("gdsimg"));
			goodsDTO.setGdsthumbimg(request.getParameter("gdsthumbimg"));
		}
		
		adminService.goodsModify(goodsDTO);
		return "redirect:/admin/index";
	}
	// 상품 삭제
	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public String postGoodsDelete(@RequestParam("n") int gdsnum) throws Exception {
	 logger.info("post goods delete");
	 adminService.goodsDelete(gdsnum);
	 return "redirect:/admin/index";
	}
	// ck 에디터에서 파일 업로드
	@RequestMapping(value = "/goods/ckUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest req,
	          HttpServletResponse res,
	          @RequestParam MultipartFile upload) throws Exception {
	 logger.info("post CKEditor img upload");
	 
	 // 랜덤 문자 생성 이미지파일 중복방지(UUID)
	 UUID uid = UUID.randomUUID();
	 
	 OutputStream out = null;
	 PrintWriter printWriter = null;
	 JsonObject json = new JsonObject();
	 // 인코딩
	 res.setCharacterEncoding("utf-8");
	 res.setContentType("text/html;charset=utf-8");
	 
	 try {
	  
	  String fileName = upload.getOriginalFilename();  // 파일 이름 가져오기
	  byte[] bytes = upload.getBytes();
	  
	  // 업로드 경로
	  String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  //goodsDTO.setGdsimg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
	  
	  out = new FileOutputStream(new File(ckUploadPath));
	  out.write(bytes);
	  out.flush();  // out에 저장된 데이터를 전송하고 초기화
	  
	  String callback = req.getParameter("CKEditorFuncNum");
	  printWriter = res.getWriter();
	  String fileUrl = "/resources/ckUpload/" + uid + "_" + fileName;  // 작성화면
	  
	  // 업로드시 메시지 출력
	  //printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
	  json.addProperty("uploaded", 1);
	  json.addProperty("fileName", fileName);
	  json.addProperty("url", fileUrl);
	  printWriter.println(json);
	  
	  System.out.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
	  //	  printWriter.println("<script type='text/javascript'>"
//	     + "window.parent.CKEDITOR.tools.callFunction("
//	     + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
//	     +"</script>");
	  
	  printWriter.flush();
	  
	 } catch (IOException e) {
		 e.printStackTrace();
	 } finally {
	  try {
	   if(out != null) {
		   out.close();
		   }
	   if(printWriter != null) { 
		   printWriter.close();
		   }
	  } catch(IOException e) {
		  e.printStackTrace();
		  }
	 }
	 return; 
	}
	//주문목록
	@RequestMapping(value = "/shop/adminOrderList", method = RequestMethod.GET)
	public void getAdminOrderList(Model model) throws Exception {
		logger.info("get order list");
		List<OrderDTO> adminOrderList = adminService.adminOrderList();
		model.addAttribute("adminOrderList", adminOrderList);
	}
	//주문상세목록
	@RequestMapping(value = "/shop/adminOrderView", method = RequestMethod.GET)
	public void getAdminOrderView(@RequestParam("n") String orderid,
			OrderDTO orderDTO, Model model) throws Exception {
		logger.info("get order view");
		orderDTO.setOrderid(orderid);
		List<OrderListDTO> adminOrderView = adminService.adminOrderView(orderDTO);
		model.addAttribute("adminOrderView", adminOrderView);
	}
	//주문상세목록 - 배송상태변경
	@RequestMapping(value = "/shop/adminOrderView", method = RequestMethod.POST)
	public String delivery(OrderDTO orderDTO) throws Exception {
		logger.info("post order view");
		adminService.delivery(orderDTO);
		List<OrderListDTO> adminOrderView = adminService.adminOrderView(orderDTO);
		GoodsDTO goodsDTO = new GoodsDTO();
		for (OrderListDTO orderListDTO : adminOrderView) {
			goodsDTO.setGdsnum(orderListDTO.getGdsnum());
			goodsDTO.setGdsstock(orderListDTO.getCartstock());
			adminService.changeStock(goodsDTO);
		}
		return "redirect:/admin/shop/adminOrderView?n=" + orderDTO.getOrderid();
			}
	//모든댓글
	@RequestMapping(value = "/shop/allReply", method = RequestMethod.GET)
	public void getAllReply(Model model) throws Exception {
		logger.info("get all reply");
		List<ReplyListDTO> replyListDTO = adminService.allReply();
		model.addAttribute("reply", replyListDTO);
	}
	//모든댓글
	@RequestMapping(value = "/shop/allReply", method = RequestMethod.POST)
	public String postAllReply(ReplyDTO replyDTO) throws Exception {
		logger.info("post all reply");
		adminService.adminDeleteReply(replyDTO.getRepnum());
		return "redirect:/admin/shop/allReply";
	}
}
