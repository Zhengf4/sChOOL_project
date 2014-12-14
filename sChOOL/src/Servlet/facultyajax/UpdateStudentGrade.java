package Servlet.facultyajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Student;
import Beans.Subject;
import DAO.UserDAO;

/**
 * Servlet implementation class UpdateStudentGrade
 */
@WebServlet("/UpdateStudentGrade")
public class UpdateStudentGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		String subjectCode = request.getParameter("subjectCode");
		String firstQG = request.getParameter("firstQG").trim();
		String secondQG = request.getParameter("secondQG").trim();
		String thirdQG = request.getParameter("thirdQG").trim();
		String fourthQG = request.getParameter("fourthQG").trim();
		
		HashMap<String,Integer> gradeMap = new HashMap<String,Integer>();
		gradeMap.put("firstQG", firstQG.equals("") ? -1: Integer.valueOf(firstQG));
		gradeMap.put("secondQG", secondQG.equals("") ? -1: Integer.valueOf(secondQG));
		gradeMap.put("thirdQG", thirdQG.equals("") ? -1: Integer.valueOf(thirdQG));
		gradeMap.put("fourthQG", fourthQG.equals("") ? -1: Integer.valueOf(fourthQG));
		
		Subject subject = new Subject();
		subject.setSubjectCode(subjectCode);
		subject.setGradeMap(gradeMap);
		
		ArrayList<Subject> subjectList = new ArrayList<Subject>();
		subjectList.add(subject);
		
		Student student = new Student();
		student.setStudentId(studentId);
		student.setSubjectList(subjectList);
		
		UserDAO userDao = new UserDAO();
		userDao.updateStudentGrade(student);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
