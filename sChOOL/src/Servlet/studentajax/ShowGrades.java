package Servlet.studentajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Subject;
import DAO.UserDAO;

/**
 * Servlet implementation class ShowGrades
 */
@WebServlet("/ShowGrades")
public class ShowGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGrades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		
		UserDAO userDao = new UserDAO();
		
		ArrayList<Subject> subjectList = userDao.FetchReportCard(studentId);
		
		PrintWriter pw = response.getWriter();
		String showGradesString = 
				  "<h4>Report Card:</h4>"
				+ "<table><tr><th>Subject</th><th>1st Quarter grade</th><th>2nd Quarter grade</th>"
									+ "<th>3rd Quarter grade</th><th>4th Quarter grade</th></tr>";
		HashMap<String,Integer> gradeMap;
		for(int i = 0; i < subjectList.size(); i++){
			gradeMap = subjectList.get(i).getGradeMap();
			showGradesString += "<tr>";
			showGradesString += "<td>" + subjectList.get(i).getSubjectCode() + "</td>";
			showGradesString += "<td>" + (gradeMap.get("first").equals(Integer.valueOf(-1)) ? "-" : gradeMap.get("first")) + "</td>";
			showGradesString += "<td>" + (gradeMap.get("second").equals(Integer.valueOf(-1)) ? "-" : gradeMap.get("second")) + "</td>";
			showGradesString += "<td>" + (gradeMap.get("third").equals(Integer.valueOf(-1)) ? "-" : gradeMap.get("third")) + "</td>";
			showGradesString += "<td>" + (gradeMap.get("fourth").equals(Integer.valueOf(-1)) ? "-" : gradeMap.get("fourth")) + "</td>";
			showGradesString += "</tr>";
		}
		showGradesString += "</table>";
		pw.write(showGradesString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
