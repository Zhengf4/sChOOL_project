/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.UserBean;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Operio
 */
@WebServlet(name = "UserLogin", urlPatterns = {"/UserLogin"})
public class UserLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            UserBean userBean = new UserBean();
            userBean.setUserId(request.getParameter("userId"));
            userBean.setPassword(request.getParameter("password"));
            userBean.setSessionId(request.getParameter("sessionId"));
            userBean.setAnnouncement(request.getParameter("announcement"));
            
            UserDAO userDAO = new UserDAO();
           
            boolean userexist = userDAO.login(userBean);
            String profession = userDAO.validatePerson(userBean.getUserId());
            String userId = userBean.getUserId();
            String sessionId = userBean.getSessionId();
            String announcement = userBean.getAnnouncement();
            
            
             if(userexist && profession.toLowerCase().equals("student")) 
           {
               userDAO.addSession(userId,sessionId);
                 HttpSession session = request.getSession(true);

        
               session.setAttribute("studentSessionUser",userBean);
                session.setMaxInactiveInterval(15*60);
                
                Cookie userID = new Cookie("studentSessionUser",userBean.getUserId());
                userID.setMaxAge(15*60);
                response.addCookie(userID);
                response.sendRedirect("student.jsp");
           }
            else if(userexist && profession.toLowerCase().equals("faculty"))
           {
               userDAO.addSession(userId,sessionId);
                 HttpSession session = request.getSession(true);

        
                session.setAttribute("facultySessionUser",userBean);
                session.setMaxInactiveInterval(15*60);
                
                Cookie userID = new Cookie("facultySessionUser",userBean.getUserId());
                userID.setMaxAge(15*60);
                response.addCookie(userID);
                response.sendRedirect("faculty.jsp");
   

           }
            else if(userexist && profession.toLowerCase().equals("admin"))
           {
              userDAO.addSession(userId,sessionId);
                 HttpSession session = request.getSession(true);

        
                session.setAttribute("adminSessionUser",userBean);
                session.setMaxInactiveInterval(15*60);
                
                Cookie userID = new Cookie("adminSessionUser",userBean.getUserId());
                userID.setMaxAge(15*60);
                response.addCookie(userID);
                response.sendRedirect("admin.jsp");
   
           }
           
            else{
                out.println("<script>");
                out.println("alert(\"INVALID ACCESS!\");");
                out.println("window.location=\"index.jsp\";");
                out.println("</script>");
               
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
