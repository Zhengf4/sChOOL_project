package Servlet.facultyajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Student;
import DAO.UserDAO;

/**
 * Servlet implementation class ShowStudentList
 */
@WebServlet("/ShowStudentList")
public class ShowStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classCode = request.getParameter("classCode");
		String subjectCode = request.getParameter("subjectCode");
		
		UserDAO userDao = new UserDAO();
		ArrayList<String> studentList = userDao.FetchStudentList(classCode);
		
		PrintWriter pw = response.getWriter();
		String studentListString = 
				  "<table><tr><td><h4>Student/s:</h4></td>"
				+ "<td><input type='button' class='btn btn-success' style='width:90px;height:30px;font-size:10px' onclick=showStudentGrades('" + classCode + "','" + subjectCode + "') value='Edit Grades'/>"
				+ "</td></tr></table>"
				+ "<ul style='list-style-type:none'>";
		for(int i=0; i < studentList.size(); i++){
			studentListString += "<li class='list'>" + studentList.get(i) + "</li>";
		}
		studentListString += "</ul>";
		pw.write(studentListString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
