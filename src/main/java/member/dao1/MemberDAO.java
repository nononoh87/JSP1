package member.dao1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import member.dao1.MemberDAO;
import member.dao1.MemberVO;

public class MemberDAO {
	 private MemberDAO() throws Exception {
	      // 1. 드라이버 로딩 -> ojdbc6.jar
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      System.out.println("MemberDAO 객체 생성 - 드라이버로딩 성공");
	   }
	   
	   static MemberDAO memberDAO = null;
	   public static MemberDAO getInstance() throws Exception{ //statict선언이유 : server에서 (3. DB에 입력) 객체 없이 접근하기 위해
		   if(memberDAO == null) memberDAO = new MemberDAO();
		   return memberDAO; 
	   } 
	   
	   
	   public void insert(MemberVO v) throws Exception {
	      Connection con = null;
	      PreparedStatement ps = null;

	      try {
	         // 2. 연결객체 얻어오기
	         String url = "jdbc:oracle:thin:@192.168.0.69:1521:xe";
	         String user = "scott";
	         String pass = "tiger";
	         con = DriverManager.getConnection(url, user, pass);
	         System.out.println("디비 연결 성공2");
	         // 3. sql 문장 만들기
	         String sql = "INSERT INTO member(realname,nickname,email,age) VALUES(?,?,?,?)";
	         // 4. 전송 객체 얻어오기
	         ps = con.prepareStatement(sql);
	         ps.setString(1, v.getRealname());
	         ps.setString(2, v.getNickname());
	         ps.setString(3, v.getEmail());
	         ps.setInt(4, v.getAge());
	         // 5. 전송
	         //int result = 
	         ps.executeUpdate();

	      } finally {
	         // 6. 닫기
	         ps.close();
	         con.close();
	      }
	   
	}
}
