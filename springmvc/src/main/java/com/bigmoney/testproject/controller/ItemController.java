package com.bigmoney.testproject.controller;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bigmoney.testproject.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemService;

	/**
	 * 상품등록화면 조회
	 * @return "/WEB-INF/views/item/create.jsp"
	 */
	@RequestMapping(value = "/createItem" ,method = RequestMethod.GET)
	public ModelAndView createItem(@RequestParam Map<String,Object> searchMap) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchMap", searchMap);
		mv.setViewName("item/create");
		return mv;
	}

	/**
	 * 상품등록 처리
	 * @param map
	 * @return "/WEB-INF/views/item/lists.jsp"
	 */
	@RequestMapping(value = "/createItem" ,method = RequestMethod.POST)
	public ModelAndView createItem(@RequestParam Map<String,Object> map, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		String location = "D:\\LHW\\MAINTENANCE\\intelliJProject\\springmvc\\src\\main\\webapp\\resources\\userImageData";
		int maxSize = 1024 * 1024 * 5; // 키로바이트 * 메가바이트 * 기가바이트 5G
		MultipartRequest multi = new MultipartRequest(request,
				location,
				maxSize,
				"utf-8",
				new DefaultFileRenamePolicy());

		Enumeration<?> files = multi.getFileNames();

		String element = ""; //
		String filesystemName = "";
		String originalFileName = "";
		String contentType = "";
		long length = 0;

		if(null != multi.getFile(element)){
            if (files.hasMoreElements()) { // 다음 정보가 있으면 Like rs.next()

                element = (String)files.nextElement(); // file을 반환

                filesystemName 			= multi.getFilesystemName(element); // 서버에 업로드된 파일명을 반환
                originalFileName 		= multi.getOriginalFileName(element); // 사용자가 업로드한 파일명을 반환
                contentType 			= multi.getContentType(element);	// 업로드된 파일의 타입을 반환
                length 					= multi.getFile(element).length(); // 파일의 크기를 반환 (long타입)

                System.out.println(element);
                System.out.println(filesystemName);
                System.out.println(originalFileName);
                System.out.println(contentType);
                System.out.println(length);
            }
        }

		String desc = multi.getParameter("f_id");
		String itemName = multi.getParameter("item_name");
		String price = multi.getParameter("price");

		map.put("f_id",desc);
		map.put("item_name",itemName);
		map.put("price",price);
		map.put("originalFileName",originalFileName);

		//상품등록
		String id = itemService.insert(map);


        if(id == null){
			//등록실패 시 등록화면으로 이동
			mav.setViewName("redirect:/createItem");
		}else{
			//등록성공 시 목록화면으로 이동
			mav.setViewName("redirect:/selectItemList");
		}
		return mav;
	}

	/**
	 * 상품 상세보기 화면조회
	 * @param searchMap
	 * @return "/WEB-INF/views/item/detail.jsp"
	 */
	@GetMapping("/detailItem")
	public ModelAndView detailItem(@RequestParam Map<String, Object> searchMap) {
		//상품 상세정보조회
		Map<String, Object> detailMap =  itemService.detail(searchMap);

		//조회수증가
        itemService.hitUp(detailMap);

		ModelAndView mv = new ModelAndView();
		mv.addObject("data", detailMap);
		mv.addObject("searchMap", searchMap);
		mv.setViewName("/item/detail");
		return mv;

	}

	/**
	 * 수정 화면조회
	 * @param searchMap
	 * @return "/WEB-INF/views/item/modify.jsp"
	 * @throws Exception
	 */
    @RequestMapping(value = "/modifyItem" ,method = RequestMethod.GET)
    public ModelAndView modifyItemView(@RequestParam Map<String, Object> searchMap)throws Exception{
		//상품 상세정보조회
        Map<String, Object> detailMap =  itemService.detail(searchMap);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", detailMap);
        mv.addObject("searchMap", searchMap);
        mv.setViewName("/item/modify");
        return mv;


    }

	/**
	 * 상품수정 처리
	 * @param map
	 * @return "success"
	 * @throws Exception
	 */
	@PostMapping("/modifyItem")
	@ResponseBody
	public String modifyItem(@RequestParam Map<String, Object> map) throws Exception {
		try {
			//상품수정
			itemService.update(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/**
	 * 상품 삭제처리
	 * @param map
	 * @return redirect:/selectItemList
	 * @throws Exception
	 */
	@GetMapping("/deleteItem")
	public ModelAndView deleteItemGet(@RequestParam Map<String, Object> map) throws Exception {
		try {
			//상품삭제
			itemService.delete(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/selectItemList");
		return mav;
	}

	/**
	 * 상품 삭제처리(ResponseBody)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/deleteItem")
	@ResponseBody
	public String deleteItem(@RequestParam Map<String, Object> map) throws Exception {
		try {
			//상품삭제
			itemService.delete(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 상품목록 화면
	 * @param page
	 * @param searchMap
	 * @return
	 */
	@GetMapping("/selectItemList")
	public ModelAndView selectItemList(@RequestParam(defaultValue = "1") int page, @RequestParam Map<String, Object> searchMap) {
		int pageSize = 10;

		String viewType = (String)searchMap.get("viewType");
		if(viewType == null){
			viewType = "";
		}

		if(viewType.equals("photo")){
			pageSize = 8;
		}

		int offset = (page-1)*pageSize;
		searchMap.put("pageSize",pageSize);
		searchMap.put("offset",offset);

		//상품목록조회
		List<Map<String, Object>> pageItems =  itemService.selectItemList(searchMap);

		int totalCount = itemService.getcount(searchMap);
		int totalPage = (int)Math.ceil( (double)totalCount/pageSize);
		ModelAndView mv = new ModelAndView();

        mv.addObject("offset", offset);
		mv.addObject("allItems", pageItems);
		mv.addObject("totalCount", totalCount);
		mv.addObject("totalPage", totalPage);
		mv.addObject("currentPage", page);
        mv.addObject("searchMap", searchMap);



        if(viewType.equals("list")){
            mv.setViewName("/item/lists");
        }else if(viewType.equals("photo")){
            mv.setViewName("/item/photoLists");
        }else{
            mv.setViewName("/item/lists");
        }

		return mv;
	}

}
