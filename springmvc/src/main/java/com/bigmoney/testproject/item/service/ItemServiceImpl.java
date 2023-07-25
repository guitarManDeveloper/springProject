package com.bigmoney.testproject.item.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigmoney.testproject.common.util.DateUtil;
import com.bigmoney.testproject.common.util.FileUtil;
import com.bigmoney.testproject.item.dao.ItemDao;
import com.bigmoney.testproject.item.vo.ItemVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class ItemServiceImpl implements ItemService {
	//테스트용
	static String ITEM_FILE_PATH = "D:\\LHW\\MAINTENANCE\\intelliJProject\\springmvc\\src\\main\\webapp\\resources\\userImageData"; //파일저장경로
	
	//실서버용
	//static String ITEM_FILE_PATH = "/home/item/webapp/resources/userImageData"; //파일저장경로	
	static int ITEM_FILE_MAX_SIZE = 1024 * 1024 * 5; //파일최대허용용량
	
	@Autowired
	ItemDao itemDao;
	
	@Override
	public void insert(ItemVO itemVO, HttpServletRequest request)throws Exception {
		//파일저장
		MultipartRequest multi = new MultipartRequest(request,
				ITEM_FILE_PATH,
				ITEM_FILE_MAX_SIZE,
				"utf-8",
				new DefaultFileRenamePolicy());

		//파일저장
		boolean fileUploadFlag = FileUtil.fileUpload(request,multi);
		if(fileUploadFlag) {
			itemVO.setOriginalFileName(multi.getOriginalFileName("image"));
		}
		
		//현재시간구하기
		String strNowDate = DateUtil.nowDate("yyyyMMddHHmmss");
		itemVO.setrDate(strNowDate);
		
		itemDao.insert(itemVO);
	}

	@Override
	public ItemVO detail(ItemVO itemVO) {		
		return itemDao.detail(itemVO);
	}

	@Override
	public boolean update(Map<String, Object> map) {
		//현재시간구하기
		String strNowDate = DateUtil.nowDate("yyyyMMddHHmmss");
		map.put("m_date", strNowDate);
		return itemDao.update(map);
	}

	@Override
	public void delete(Map<String, Object> map) {
		itemDao.delete(map);
		
	}

	@Override
	public List<ItemVO> selectItemList(ItemVO itemVO) {
		return itemDao.selectItemList(itemVO);
	}

	@Override
	public int getcount(ItemVO itemVO) {
		return itemDao.getcount(itemVO);
	}

	@Override
	public void hitUp(ItemVO itemVO) {
		//조회수증가
		itemDao.hitUp(itemVO);
	}

}
