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

import Beans.ClassBean;
import Beans.Student;
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

	public ArrayList<Student> FetchStudentList(String classCode) {
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connection = connectionFactory.getConnection();
        
        try {
          	 String query = 
          			  "SELECT studentId, name FROM enroll a "
          	 		+ "INNER JOIN user b on a.studentId=b.userId "
          	 		+ "WHERE a.classCode = ?";

          	 preparedStatement = connection.prepareStatement(query);
          	 preparedStatement.setString(1, classCode);
          	 ResultSet rs = preparedStatement.executeQuery();
          	 ArrayList<Student> studentList = new ArrayList<Student>();
          	 
          	 while(rs.next()){
          		 String studentId = rs.getString("studentId");
          		 String name = rs.getString("name");
          		 Student student = new Student(studentId, name);
          		 studentList.add(student);
          	 }
          	 
          	 closeAll();
          	 return studentList;
          	 
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return null;
	}
    
    
}
