/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

import Beans.ClassBean;
import Beans.Clearance;
import Beans.Student;
import Beans.Subject;
import Beans.UserBean;
import DatabaseConnection.ConnectionFactory;

/**
 *
 * @author Operio
 */
public class UserDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    
       public boolean login(UserBean userBean)
    {
        ArrayList<UserBean> list = getAccounts();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getUserId().equals(userBean.getUserId()) &&
                    list.get(i).getPassword().equals(userBean.getPassword())){
                return true;
            }
        }
        return false;
    }
    
       public boolean updateAnnouncement(String announcement){
             try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("update announcement set announcement = ? where announcementId = 1");
            preparedStatement.setString(1, announcement);
            preparedStatement.executeUpdate();
            closeAll();
            return true;
             }catch(SQLException e){
            e.printStackTrace();}
            return false;    
       }
     
       public String selectAnnouncement(){  
           String announcement="";
       
       
             try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select announcement from announcement where announcementId = ?");
            preparedStatement.setInt(1,1);
            resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
            	announcement=resultSet.getString("announcement"); 
            }
                     
            closeAll();
             }catch(SQLException e){
            e.printStackTrace();}
            return announcement;    
       }
       
          public boolean deleteSession(String sessionId){
             try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("delete from session where sessionId = ?");
            preparedStatement.setString(1, sessionId);
            preparedStatement.executeUpdate();
            closeAll();
            return true;
             }catch(SQLException e){
            e.printStackTrace();}
            return false;    
       }
       
          public boolean addSession(String userId, String sessionId){
             try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("insert into session(userId, sessionId) "
                    + "values(?, ?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, sessionId);
            preparedStatement.executeUpdate();
            closeAll();
            return true;
             }catch(SQLException e){
            e.printStackTrace();}
            return false;    
       }
        
          
   public String validatePerson(String userId)
    {
        ArrayList<UserBean> list = getAccounts();
        
        for(int i=0; i<list.size(); i++){
            if(userId.equals(list.get(i).getUserId().toLowerCase()))
            switch (list.get(i).getProfession().toLowerCase()) {
                case "student":
                    return "student";
                case "faculty":
                    return "faculty";
                case "admin":
                    return "admin";
            }
        }
        return "none";
        }
   
        public ArrayList<UserBean> getAccounts()
       {  
           ArrayList <UserBean> list = new ArrayList<UserBean>();
        try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select * from user");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserBean userBean = new UserBean();
                userBean.setUserId(resultSet.getString("userId"));
                userBean.setName(resultSet.getString("name"));
                userBean.setPassword(resultSet.getString("password"));
                userBean.setProfession(resultSet.getString("profession"));
                list.add(userBean);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
        
      public ArrayList<UserBean> studentList(){
          ArrayList <UserBean> list = new ArrayList<UserBean>();
             try{
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("select * from user");
             resultSet = preparedStatement.executeQuery();
              while(resultSet.next()){
                UserBean userBean = new UserBean();
                userBean.setUserId(resultSet.getString("userId"));
                  userBean.setName(resultSet.getString("name"));
                list.add(userBean);
            }
            closeAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
     
    public void closeAll(){
        ConnectionFactory.closeConnection(connection);
        ConnectionFactory.closeResultSet(resultSet);
        ConnectionFactory.closeStatement(preparedStatement);
    }

	public UserBean FindUser(String userId, String password) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
       
        try {
        	 String query = "SELECT userId, password, name, profession FROM school.user "
        		+ "where userId= ? AND password= ?";

        	 preparedStatement = connection.prepareStatement(query);
        	 preparedStatement.setString(1, userId);
        	 preparedStatement.setString(2, password);
        	 ResultSet rs = preparedStatement.executeQuery();
        	 UserBean user = null;
        	 
        	 if(rs.next()){
        		 user = new UserBean();
        		 user.setUserId(rs.getString("userId"));
        		 user.setPassword(rs.getString("password"));
        		 user.setName(rs.getString("name"));
        		 user.setProfession(rs.getString("profession"));
        	 }
        	 
        	 closeAll();
        	 return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ClassBean> FetchClassList(String facultyId) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        
        try {
       	 String query = 
       			  "SELECT c.classCode as classCode, level, section, a.subjectCode as subjectCode FROM subject a "
       	 		+ "INNER JOIN schedule b on a.subjectCode=b.subjectCode "
       	 		+ "INNER JOIN class c on b.classCode=c.classCode "
       	 		+ "WHERE teacherId = ?";

       	 preparedStatement = connection.prepareStatement(query);
       	 preparedStatement.setString(1, facultyId);
       	 ResultSet rs = preparedStatement.executeQuery();
       	 ArrayList<ClassBean> classList = new ArrayList<ClassBean>();
       	 
       	 while(rs.next()){
       		String classCode = rs.getString("classCode");
       		String level = rs.getString("level");
       		String section = rs.getString("section");
       		String subjectCode = rs.getString("subjectCode");
       		
       		ClassBean classBean = new ClassBean(classCode,level,section,subjectCode);
       		classList.add(classBean);
       	 }
       	 
       	 closeAll();
       	 return classList;
       	 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> FetchStudentList(String classCode) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        
        try {
          	 String query = 
          			  "SELECT name FROM enroll a "
          	 		+ "INNER JOIN user b on a.studentId=b.userId "
          	 		+ "WHERE a.classCode = ?";

          	 preparedStatement = connection.prepareStatement(query);
          	 preparedStatement.setString(1, classCode);
          	 ResultSet rs = preparedStatement.executeQuery();
          	 ArrayList<String> studentList = new ArrayList<String>();
          	 
          	 while(rs.next()){
          		 studentList.add(rs.getString("name"));
          	 }
          	 
          	 closeAll();
          	 return studentList;
          	 
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return null;
	}

	public ArrayList<Student> FetchStudentGrades(String classCode, String subjectCode) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        
        try {
         	 String query = 
         			  	"SELECT studentId, name, subjectCode, grade, quarter FROM grade a "
         			  + "INNER JOIN user b on a.studentId=b.userId "
         			  + "WHERE subjectCode = ? AND studentId IN "
         			  + "(SELECT studentId FROM enroll a "
         			  + "INNER JOIN user b on a.studentId=b.userId "
         			  + "WHERE a.classCode = ?) ORDER BY studentId ASC";

         	 preparedStatement = connection.prepareStatement(query);
         	 preparedStatement.setString(1, subjectCode);
         	 preparedStatement.setString(2, classCode);
         	
         	 ResultSet rs = preparedStatement.executeQuery();
         	 ArrayList<Student> studentList = new ArrayList<Student>();
         	 int ctr = 0;
         	 String prevStudentId = "";
         	 
         	 while(rs.next()){
         		 String studentId = rs.getString("studentId");
         		 String studentName = rs.getString("name");
         		 String subjCode = rs.getString("subjectCode");
         		 Integer grade = rs.getInt("grade");
         		 String quarter = rs.getString("quarter");
         		 
         		 if(!studentList.isEmpty() & studentId.equals(prevStudentId)){
         			 Student student = studentList.get(ctr-1);
         			 student.getSubjectList().get(0).getGradeMap().put(quarter, grade);
         			 
         		 } else {
         			 Student newStudent = new Student();
         			 newStudent.setStudentId(studentId);
         			 newStudent.setName(studentName);
            		 ArrayList<Subject> subjectList = new ArrayList<Subject>();
            		 
            		 //initialize subject
            		 Subject subject = new Subject();
            		 subject.setSubjectCode(subjCode);
            		 
            		 //insert grade and quarter
            		 HashMap<String,Integer> gradeMap = new HashMap<String,Integer>();
            		 gradeMap.put(quarter, grade);
            		 subject.setGradeMap(gradeMap);
            		 
            		 subjectList.add(subject);
            		 newStudent.setSubjectList(subjectList);
            		 studentList.add(newStudent);
            		 ctr++;
         		 }
         		 
         		 prevStudentId = studentId;
         		 
         	 }
         	
         	 closeAll();
         	 return studentList;
         	 
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
		return null;
	}

	public void updateStudentGrade(Student student) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        
        String studentId = student.getStudentId();
        Subject subject = student.getSubjectList().get(0);
        String subjectCode = subject.getSubjectCode();
        
        ArrayList<Integer> grades = new ArrayList<Integer>();
        grades.add(subject.getGradeMap().get("firstQG"));
        grades.add(subject.getGradeMap().get("secondQG"));
        grades.add(subject.getGradeMap().get("thirdQG"));
        grades.add(subject.getGradeMap().get("fourthQG"));
        
        ArrayList<String> quarters = new ArrayList<String>();
        quarters.add("first");
        quarters.add("second");
        quarters.add("third");
        quarters.add("fourth");
      
        try {
        	for(int i = 0; i < 4; i++){
	        	String query = "SELECT grade FROM grade WHERE studentId= ? AND quarter= ? AND subjectCode= ?";
	        	preparedStatement = connection.prepareStatement(query);
	        	preparedStatement.setString(1, studentId);
	        	preparedStatement.setString(2, quarters.get(i));
	        	preparedStatement.setString(3, subjectCode);
	        	ResultSet rs = preparedStatement.executeQuery();
        	
	        	if(rs.next()){
	        		if(!grades.get(i).equals(Integer.valueOf(rs.getInt("grade")))){
	        			query = "UPDATE grade SET grade= ? WHERE studentId= ? AND subjectCode= ? AND quarter = ?";
		        		preparedStatement = connection.prepareStatement(query);
		        		preparedStatement.setInt(1, grades.get(i));
		        		preparedStatement.setString(2, studentId);
		        		preparedStatement.setString(3, subjectCode);
		        		preparedStatement.setString(4, quarters.get(i));
		        		preparedStatement.executeUpdate();
	        		}
	        		
	        	} else {
	        		query = "INSERT INTO grade (grade, quarter, subjectCode, studentId) VALUE (?,?,?,?)";
	        		preparedStatement = connection.prepareStatement(query);
	        		preparedStatement.setInt(1, grades.get(i) == null ? -1: grades.get(i));
	        		preparedStatement.setString(2, quarters.get(i));
	        		preparedStatement.setString(3, subjectCode);
	        		preparedStatement.setString(4, studentId);
	        		preparedStatement.executeUpdate();
	        	}
        	}
        	
        	closeAll();
         	 
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
	}

	public ArrayList<Clearance> FetchStudentClearance(String studentId) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        ArrayList<Clearance> clearanceList = new ArrayList<Clearance>();
        
        try {
        	String query = "SELECT studentId, description, dateIssued, dateResolved FROM clearance "
            				+ "INNER JOIN user on studentId = userId "
            				+ "WHERE userId = ?";
        	
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, studentId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				Clearance newClearance = new Clearance();
				newClearance.setClearance(rs.getString("description"));
				newClearance.setDateTimeIssued(new DateTime(rs.getTimestamp("dateIssued").getTime()));
				newClearance.setDateTimeResolved(rs.getTimestamp("dateResolved") == null ? null : new DateTime(rs.getTimestamp("dateResolved")));
				clearanceList.add(newClearance);
			}
			
			closeAll();
			return clearanceList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return null;
	}
    
    
}
