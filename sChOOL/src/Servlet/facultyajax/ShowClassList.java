package Servlet.facultyajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ClassBean;
import DAO.UserDAO;

/**
 * Servlet implementation class ShowClassList
 */
@WebServlet("/ShowClassList")
public class ShowClassList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowClassList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String facultyId = request.getParameter("facultyId");
		UserDAO userDao = new UserDAO();
		ArrayList<ClassBean> classList = userDao.FetchClassList(facultyId);
		
		PrintWriter pw = response.getWriter();
		String classListString = "";
		for(int i=0; i < classList.size(); i++){
			classListString += "<a onclick=showStudentList('" + classList.get(i).getClassId() + "') class='list'>" 
					+ classList.get(i).getLevel() + " section: " + classList.get(i).getSection() + "</a></br>";
		}
		pw.write(classListString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
