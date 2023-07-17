package com.bigmoney.testproject.item.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.bigmoney.testproject.item.service.ItemService;
import com.bigmoney.testproject.item.vo.ItemVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ItemController {

	@Autowired
	ItemService itemService;
	
	/**
	 * 상품등록화면 조회
	 * @return "/WEB-INF/views/item/create.jsp"
	 */
	@RequestMapping(value = "/createItem" ,method = RequestMethod.GET)
	public ModelAndView createItem(@ModelAttribute("itemVO")ItemVO itemVO) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("item/create");
		return mv;
	}

	/**
	 * 상품등록 처리
	 * @param map
	 * @return "/WEB-INF/views/item/lists.jsp"
	 */
	@RequestMapping(value = "/createItem" ,method = RequestMethod.POST)
	public ModelAndView createItem(@ModelAttribute("itemVO")ItemVO itemVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		//상품등록
		try {
			itemService.insert(itemVO, request);
			mav.setViewName("redirect:/selectItemList");
		} catch (Exception e) {
			//등록실패
			mav.setViewName("redirect:/createItem");
			return mav;
		}
		return mav;
	}

	/**
	 * 상품 상세보기 화면조회
	 * @param searchMap
	 * @return "/WEB-INF/views/item/detail.jsp"
	 */
	@GetMapping("/detailItem")
	public ModelAndView detailItem(@ModelAttribute("itemVO")ItemVO itemVO) {
		//상품 상세정보조회
		ItemVO item =  itemService.detail(itemVO);

		//조회수증가
        itemService.hitUp(item);

		ModelAndView mv = new ModelAndView();
		mv.addObject("item", item);
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
    public ModelAndView modifyItemView(@ModelAttribute("itemVO")ItemVO itemVO)throws Exception{
		//상품 상세정보조회
    	ItemVO item =  itemService.detail(itemVO);

        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
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
	public ModelAndView selectItemList(@ModelAttribute("itemVO") ItemVO itemVO) {
		if(itemVO.getViewType().equals("photo")){
			itemVO.setPageSize(8);
		}

		//상품목록조회
		List<ItemVO> itemList =  itemService.selectItemList(itemVO);

		//상품 총 카운터수
		int totalCnt = itemService.getcount(itemVO);
		
		int totalPage = (int)Math.ceil((double)totalCnt/itemVO.getPageSize());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemList", itemList);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("totalPage", totalPage);
		mv.addObject("currentPage", itemVO.getPage());

        if(itemVO.getViewType().equals("list")){
            mv.setViewName("/item/lists");
        }else if(itemVO.getViewType().equals("photo")){
            mv.setViewName("/item/photoLists");
        }else{
            mv.setViewName("/item/lists");
        }

		return mv;
	}

}
