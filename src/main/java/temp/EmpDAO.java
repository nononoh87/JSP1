package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmpDAO {
   private EmpDAO() throws Exception {
      // 1. 드라이버 로딩 -> ojdbc6.jar
      Class.forName("oracle.jdbc.driver.OracleDriver");
      System.out.println("EmpDAO 객체 생성 - 드라이버로딩 성공");
   }
   
   static EmpDAO empDAO = null;
   public static EmpDAO getInstance() throws Exception{ //statict선언이유 : server에서 (3. DB에 입력) 객체 없이 접근하기 위해
	   if(empDAO == null) empDAO = new EmpDAO();
	   return empDAO; 
   }
   
   
   public void insert(EmpVO vo) throws Exception {
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
         String sql = "INSERT INTO EMP(EMPNO,ENAME,DEPTNO,JOB,SAL) VALUES(?,?,?,?,?)";
         // 4. 전송 객체 얻어오기
         ps = con.prepareStatement(sql);
         ps.setInt(1, vo.getEmpno());
         ps.setString(2, vo.getEname());
         ps.setInt(3, vo.getDeptno());
         ps.setString(4, vo.getJob());
         ps.setInt(5, vo.getSal());
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