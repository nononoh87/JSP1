package board_ex.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import guest.model.Message;
import guest.model.MessageException;

public class BoardDao
{

   // Single Pattern 
   private static BoardDao instance;

   // DB 연결시  관한 변수  
   private static final String    dbDriver   =   "oracle.jdbc.driver.OracleDriver";
   private static final String      dbUrl      =   "jdbc:oracle:thin:@192.168.0.12:1521:xe";
   private static final String      dbUser      =   "scott";
   private static final String      dbPass      =   "tiger";


   private Connection          con;   

   //--------------------------------------------
   //#####    객체 생성하는 메소드 
   public static BoardDao   getInstance() throws BoardException
   {
      if( instance == null )
      {
         instance = new BoardDao();
      }
      return instance;
   }

   private BoardDao() throws BoardException
   {

      try{

         /********************************************
         1. 오라클 드라이버를 로딩
            ( DBCP 연결하면 삭제할 부분 )
          */

         Class.forName( dbDriver );   
      }catch( Exception ex ){
         throw new BoardException("DB 연결시 오류  : " + ex.toString() );   
      }

   }

   /************************************************
    * 함수명 : insert
    * 역할 :   게시판에 글을 입력시 DB에 저장하는 메소드 
    * 인자 :   BoardVO
    * 리턴값 : 입력한 행수를 받아서 리턴
    */
   public int insert( BoardVO rec ) throws BoardException
   {

      ResultSet rs = null;
      Statement stmt   = null;
      PreparedStatement ps = null;
      PreparedStatement ps2 = null;
      try{
         // 1. 연결객체(Connection) 얻어오기
         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );

         //2. sql 문장 만들기
         String putQuery      = "INSERT INTO board_ex(seq,title,writer,content,regdate,cnt,pass) "
               + " VALUES(seq_board.nextval,?,?,?,sysdate,0,?)";  
         //3. 전송객체 얻어오기   

         ps      = con.prepareStatement( putQuery );

         //4. sql 문장의 ? 지정하기
         ps.setString(1, rec.getTitle());
         ps.setString(2, rec.getWriter());
         ps.setString(3, rec.getContent());
         ps.setString(4, rec.getPass());

         // 5. 전송하기
         ps.executeUpdate();      
         //현재의 입력하려는 글번호로 보내는 sql작성
         String sql2= "SELECT seq_board.currval as seq FROM dual";
         ps2   = con.prepareStatement( sql2 );
         rs= ps2.executeQuery();
         if(rs.next()) {
            //컬럼 이름이 seq인 것을 반환하겠다
            return rs.getInt("seq");
         }

         return 0;


      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 입력시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( stmt != null ) { try{ stmt.close(); } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }

   }


   /************************************************
    * 함수명 : selectList
    * 역할 :   전체 레코드를 검색하는 함수
    * 인자 :   없음
    * 리턴값 : 테이블의 한 레코드를 BoardVO 지정하고 그것을 ArrayList에 추가한 값
    * @param endRow 
    * @param startRow 
    */

   public List<BoardDao> selectList() throws BoardException
   {
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BoardVO> mList = new ArrayList<BoardVO>();
      boolean isEmpty = true;

      try{


         // 1. 연결객체(Connection) 얻어오기
         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );

         //2. sql 문장 만들기
         String sql = "SELECT SEQ,TITLE,WRITER,REGDATE,CNT FROM BOARD_EX";

         //3. 전송객체 얻어오기   
         ps      = con.prepareStatement( sql );

         //4. 전송하기
         rs = ps.executeQuery();

         //5. 결과 받아 List<BoardVO> 변수 mList에 지정하기
         while(rs.next()) {
            BoardVO vo = new BoardVO();
            vo.setSeq(rs.getInt("seq"));
            vo.setTitle(rs.getString("title"));
            vo.setWriter(rs.getString("Writer"));
            vo.setRegdate(rs.getString("regdate"));
            vo.setCnt(rs.getInt("cnt"));

            mList.add(vo);
            isEmpty =false;
         }

         if( isEmpty ) return Collections.emptyList();

         return  null;
      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }
   
   public List<BoardVO> selectList(int firstRow, int endRow) throws BoardException
   {
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<BoardVO> mList = new ArrayList<BoardVO>();
      boolean isEmpty = true;

      try{
         // 1. 연결객체(Connection) 얻어오기
         con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
         System.out.println("디비 연결 성공2");
         // 2. sql 문장 만들기
         String sql = "select *\r\n"
               + " from BOARD_EX \r\n"
               + " where SEQ in (select SEQ\r\n"
               + "                from(select rownum as rnum, SEQ\r\n"
               + "                    from (select  SEQ from BOARD_EX order by  SEQ desc))\r\n"
               + "                where rnum>=? and rnum<=?)\r\n"
               + " order by SEQ ";
         // 3. 전송객체 얻어오기
         ps=con.prepareStatement(sql);
         ps.setInt(1, firstRow);
         ps.setInt(2, endRow);
         // 4. 전송하기
         rs = ps.executeQuery();

         while(rs.next()) {
            BoardVO m = new BoardVO();
            m.setSeq(rs.getInt("seq"));
            m.setTitle(rs.getString("title"));
            m.setWriter(rs.getString("writer"));
            m.setContent(rs.getString("content"));
            m.setRegdate(rs.getString("regdate"));
            mList.add(m);
            isEmpty =false;
         }



         if( isEmpty ) return Collections.emptyList();

         return mList;
      }catch( Exception ex ){
         throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }

   //--------------------------------------------
   //#####    게시글번호에 의한 레코드 검색하는 함수
   public BoardVO selectById(int seq) throws BoardException
   {
      PreparedStatement ps = null;
      ResultSet rs = null;

      BoardVO rec = new BoardVO();

      try{
         // 1. 연결객체(Connection) 얻어오기
         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );

         //2. sql 문장 만들기
         String sql = "SELECT * FROM BOARD_EX WHERE SEQ=?";
         //3. 전송객체 얻어오기   
         ps      = con.prepareStatement( sql );
         ps.setInt(1, seq);
         //4. 전송하기
         rs = ps.executeQuery();

         // * 결과 받아 BoardVO변수 rec에 지정하기
         if(rs.next()) {
            rec.setCnt(rs.getInt("seq"));
            rec.setTitle(rs.getString("title"));
            rec.setWriter(rs.getString("Writer"));
            rec.setRegdate(rs.getString("regdate"));
            rec.setContent(rs.getString("content"));
            rec.setCnt(rs.getInt("cnt"));
         }

         return rec;
      }catch( Exception ex ){
         throw new BoardException("게시판 ) DB에 글번호에 의한 레코드 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }      
   }

   //--------------------------------------------
   //#####    게시글 보여줄 때 조회수 1 증가
   public void increaseReadCount( int seq ) throws BoardException
   {

      PreparedStatement ps = null;
      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         // * sql 문장만들기
         String sql = "UPDATE BOARD_EX SET CNT = CNT+1 WHERE SEQ = ? ";
         // * 전송객체 얻어오기
         ps      = con.prepareStatement( sql );
         ps.setInt(1, seq);
         // * 전송하기
         ps.executeQuery();

      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 볼 때 조회수 증가시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }

   }
   //--------------------------------------------
   //#####    게시글 수정할 때
   public int update( BoardVO rec ) throws BoardException
   {

      PreparedStatement ps = null;
      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );
         // * sql 문장만들기
         String sql ="UPDATE BOARD_EX SET title=?, content=? WHERE SEQ = ? and pass =? ";
         // * 전송객체 얻어오기
         ps      = con.prepareStatement( sql );
         ps.setString(1, rec.getTitle());
         ps.setString(2, rec.getContent());
         ps.setInt(3, rec.getSeq());
         ps.setString(4, rec.getPass());

         return ps.executeUpdate();

      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }

   }


   //--------------------------------------------
   //#####    게시글 삭제할 때
   public int delete( int seq, String pass ) throws BoardException
   {

      PreparedStatement ps = null;
      try{

         con   = DriverManager.getConnection( dbUrl, dbUser, dbPass );

         // * sql 문장만들기
         String sql ="Delete FROM BOARD_EX WHERE SEQ =? AND PASS =? ";
         // * 전송객체 얻어오기
         ps      = con.prepareStatement( sql );
         ps.setInt(1, seq);
         ps.setString(2, pass);
         //4. 전송하기

         return ps.executeUpdate();

      }catch( Exception ex ){
         throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );   
      } finally{
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }

   }

   public int getTotalCount() throws BoardException {
      Connection          con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int count = 0;

      try{
         // 1. 연결객체(Connection) 얻어오기
         String url = "jdbc:oracle:thin:@192.168.0.12:1521:xe";
         String user = "scott";
         String pass = "tiger";

         con = DriverManager.getConnection(url,user,pass);
         System.out.println("디비 연결 성공2");
         // 2. sql 문장 만들기
         String sql = "SELECT COUNT(*) AS CNT FROM BOARD_EX";
         // 3. 전송객체 얻어오기
         ps = con.prepareStatement(sql);

         // 4. 전송하기
         rs= ps.executeQuery();
         if(rs.next()) {
            count = rs.getInt("CNT");
         }

         return  count;

      }catch( Exception ex ){
         throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );   
      } finally{
         if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
         if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
         if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
      }         
   }



}