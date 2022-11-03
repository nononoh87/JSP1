package board_ex.service;

import java.sql.SQLException;
import java.util.List;

import board_ex.model.*;
import guest.model.Message;
import guest.model.MessageDao;
import guest.model.MessageException;

public class ListArticleService {
	private int totalRecCount;		// 전체 레코드 수	
	private int pageTotalCount;		// 전체 페이지 수
	private double countPerPage = 5.0;	// 한페이지당 레코드 수
	private static ListArticleService instance;
	public static ListArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ListArticleService();
		}
		return instance;
	}
	
	public List <BoardVO> getArticleList(String pNum) throws BoardException, SQLException, MessageException
	{
		
		int pageNum = 1;
		if(pNum != null) pageNum = Integer.parseInt(pNum);
		/*page number	startRecordNumber	endRecordNumber
		 * 		1 				1				3
		 * 		2				4				6 ...*/
		int startRow = (int) (pageNum*countPerPage - (countPerPage-1));
		int endRow = (int) (pageNum * countPerPage);
		// 전체 레코드를 검색해 온다면
		List <BoardVO> mList = BoardDao.getInstance().selectList(startRow,endRow);		  	

		System.out.println(pageNum+":"+startRow +":"+ endRow);
		return mList; 
	}
	
	public int getTotalPage() throws MessageException, SQLException, BoardException{
		//전체 레코드 수
		totalRecCount = BoardDao.getInstance().getTotalCount();
	
		pageTotalCount = (int)Math.ceil(totalRecCount/countPerPage);
		return pageTotalCount; //페이지 수 리턴
		
	}
		
}
