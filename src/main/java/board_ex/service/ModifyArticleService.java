package board_ex.service;

import java.sql.SQLException;

import board_ex.model.*;

public class ModifyArticleService {
	
	private static ModifyArticleService instance;
	public static ModifyArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ModifyArticleService();
		}
		return instance;
	}
	
	public int update( BoardVO rec ) throws BoardException, SQLException{
		
		// DB에서 update
		int result = BoardDao.getInstance().update(rec);
	
		return result;
		
	}
}
