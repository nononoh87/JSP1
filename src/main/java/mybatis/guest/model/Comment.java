package mybatis.guest.model;
import java.io.*;

/*  직렬화
 *  - 클래스 뒤에 implements Serializable
 *  - 통신상에 전송하는 객체에는 직렬화를 해야 함
 */

public class Comment {

   private int commentNo;
   private String userId;
   private String commentContent;
   private String regDate;
   
   
   public int getCommentNo() {
      return commentNo;
   }
   public void setCommentNo(int commentNo) {
      this.commentNo = commentNo;
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getCommentContent() {
      return commentContent;
   }
   public void setCommentContent(String commentContent) {
      this.commentContent = commentContent;
   }
   public String getRegDate() {
      return regDate;
   }
   public void setRegDate(String regDate) {
      this.regDate = regDate;
   }
   
   
   
   
   
}