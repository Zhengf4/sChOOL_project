/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.UserBean;
import DatabaseConnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

                     announcement=resultSet.getString("announcement"); 
  
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
    
    
}
