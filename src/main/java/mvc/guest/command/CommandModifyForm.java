package mvc.guest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.guest.model.Message;
import mvc.guest.model.MessageDao;
import mvc.guest.model.MessageException;

public class CommandModifyForm implements Command{
	
		private String next;

		public CommandModifyForm( String _next ){
			next = _next;
		}

		public String execute( HttpServletRequest request , HttpServletResponse response  ) throws CommandException{
			try{
				int messageId = Integer.parseInt(request.getParameter("id"));
								
			    Message result = MessageDao.getInstance().selectById(messageId);	
			    request.setAttribute("param", result );

			}catch( MessageException ex ){
				throw new CommandException("CommandList.java < 목록보기시 > " + ex.toString() ); 
			}
			
			return next;
		}
}
