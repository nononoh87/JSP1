package guest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDao {

   // Single Pattern 
   private static MessageDao instance;
   
   // DB 연결시  관한 변수 
   private static final String    dbDriver   =   "oracle.jdbc.driver.OracleDriver";
   private static final String      dbUrl      =   "jdbc:oracle:thin:@192.168.0.12:1521:xe";
   private static final String      dbUser      =   "scott";
   private static final String      dbPass      =   "tiger";
   
   
   
   //--------------------------------------------
   //#####    객체 생성하는 메소드 
   public static MessageDao   getInstance() throws MessageException //
   {
      if( instance == null )
      {
         instance = new MessageDao();
      }
      return instance;
   }
   
   private MessageDao() throws MessageException
   {
   
      try{
         
         /********************************************
            1. 오라클 드라이버를 로딩
               ( DBCP 연결하면 삭제할 부분 )
         */
         
         Class.forName(dbDriver);
         System.out.println("드라이버 연결 완료");
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB 연결시 오류  : " + ex.toString() );   
      }
      
   }
   
   
   /*
    * 메세지를 입력하는 함수
    */
   public void insert(Message rec) throws MessageException
   {
      Connection        con = null;
      PreparedStatement ps = null;
      try{

         // 1. 연결객체(Connection) 얻어오기
         con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            System.out.println("2. 연결 성공");
         // 2. sql 문장 만들기
         String sql = "INSERT INTO guesttb(message_id,guest_name,password ,message) "
               + "   VALUES(seq_guestTb_messageId.nextval, ?,?,?)";
         // 3. 전송객체 얻어오기
           ps = con.prepareStatement(sql);
            
            ps.setString(1, rec.getGuestName());
            ps.setString(2, rec.getPassword());
            ps.setString(3, rec.getMessage());
            
         // 4. 전송하기
            ps.executeUpdate(); //1. 수행결과로 Int 타입의 값을 반환.
            					//2. SELECT 구문을 제외한 다른 구문을 수행할 때 사용되는 함수.
            					// -> INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
            					// -> CREATE / DROP 관련 구문에서는 -1 을 반환
            
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB에 입력시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }
   
   }
   
   /*
    * 메세지 목록 전체를 얻어올 때
    */
   public List<Message> selectList() throws MessageException
   {
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<Message> mList = new ArrayList<Message>();
      boolean isEmpty = true;
      
      try{
         con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
         String sql = "SELECT * FROM guesttb";
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();	//1. 수행결과로 ResultSet 객체의 값을 반환.
									//2. SELECT 구문을 수행할 때 사용되는 함수.
									// -> ResultSet 객체에 결과값을 담을 수 있다.    
          
         while(rs.next()) {
            Message m = new Message();
            m.setMessageId(rs.getInt("MESSAGE_ID"));
            m.setGuestName(rs.getString("GUEST_NAME"));
            m.setPassword(rs.getNString("password"));
            m.setMessage(rs.getString("message"));
            
            mList.add(m);
            isEmpty = false;
             
         }
         
         
         if( isEmpty ) return Collections.emptyList();
         
         return mList;
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }
   

   /* -------------------------------------------------------
    * 현재 페이지에 보여줄 메세지 목록  얻어올 때
    */
   public List<Message> selectList(int firstRow, int endRow) throws MessageException
   {
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<Message> mList = new ArrayList<Message>();
      boolean isEmpty = true;
      
      try{
         con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
         String sql = "select *"
               + " from Guesttb"
               + " where  message_id  IN (select  message_id"
               + " from (select rownum as rnum,  message_id"
               + " from (select  message_id   from GUESTTB  order by  message_id  desc))"
               + " where rnum>=? And rnum<=?)"
               + " ORDER by  message_id Desc";
          ps = con.prepareStatement(sql);
          ps.setInt(1, firstRow);
          ps.setInt(2, endRow);
          	
          rs = ps.executeQuery();	//1. 수행결과로 ResultSet 객체의 값을 반환.
          							//2. SELECT 구문을 수행할 때 사용되는 함수.
          							// -> ResultSet 객체에 결과값을 담을 수 있다.   
          
         while(rs.next()) {
            Message m = new Message();
            m.setMessageId(rs.getInt("MESSAGE_ID"));
            m.setGuestName(rs.getString("GUEST_NAME"));
            m.setPassword(rs.getNString("password"));
            m.setMessage(rs.getString("message"));
            
            mList.add(m);
            isEmpty = false;
             
         }
         if( isEmpty ) return Collections.emptyList();
         
         return mList;
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }
   
   
   
   /* -------------------------------------------------------
    * 메세지 전체 레코드 수를 검색
    */
   
   public int getTotalCount() throws MessageException{
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int count = 0;

      try{
         con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
         String sql = "Select count(*) as cnt From guesttb";
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         if(rs.next()) {
         count = rs.getInt("cnt");
         }
         return  count;
         
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }         
   }
   
   /*
    * 메세지 번호와 비밀번호에 의해 메세지 삭제
    */
   public int delete( int messageId, String password ) throws MessageException
   {
      int result = 0;
      Connection con = null;
      PreparedStatement ps = null;
      try{
         // 1. 연결객체(Connection) 얻어오기
           con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            System.out.println("2. 연결 성공");
         
           String sql = "Delete from guesttb where message_id =? and password =?";

           ps = con.prepareStatement(sql);
           
           ps.setInt(1, messageId);
           ps.setString(2, password);
           
          result = ps.executeUpdate(); 	//1. 수행결과로 Int 타입의 값을 반환.
										//2. SELECT 구문을 제외한 다른 구문을 수행할 때 사용되는 함수.
										// -> INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
										// -> CREATE / DROP 관련 구문에서는 -1 을 반환

         return result;
      }catch( Exception ex ){
         throw new MessageException("방명록 ) DB에 삭제시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }
}