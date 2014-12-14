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
 * Servlet implementation class ShowStudentGrades
 */
@WebServlet("/ShowStudentGrades")
public class ShowStudentGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentGrades() {
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
		ArrayList<Student> studentGrades = userDao.FetchStudentGrades(classCode, subjectCode);
		
		PrintWriter pw = response.getWriter();
		String studentListString = 
				  "<h4>Grades</h4>"
				+ "<table id='gradeTable'>"
				+ "<tr><th>Student</th><th>First Quarter</th><th>Second Quarter</th><th>Third Quarter</th><th>Fourth Quarter</th></tr>";
		for(int i=0; i < studentGrades.size(); i++){
			String studentId = studentGrades.get(i).getStudentId();
			String studentName = studentGrades.get(i).getName();
			
			Integer firstQG = studentGrades.get(i).getSubjectList().get(0).getGradeMap().get("first");
			Integer secondQG = studentGrades.get(i).getSubjectList().get(0).getGradeMap().get("second");
			Integer thirdQG = studentGrades.get(i).getSubjectList().get(0).getGradeMap().get("third");
			Integer fourthQG = studentGrades.get(i).getSubjectList().get(0).getGradeMap().get("fourth");
			
			studentListString += "<tr id=" + studentId + ">";
			studentListString += "<td>" + studentName + "</td>";
			if(firstQG == null || firstQG == -1)
				studentListString += "<td><input type='text' name='firstQG' class='gradeText' value='' disabled/></td>";
			else
				studentListString += "<td><input type='text' name='firstQG' class='gradeText' value='" + firstQG + "' disabled/></td>";
			
			if(secondQG == null || secondQG == -1)
				studentListString += "<td><input type='text' name='secondQG' class='gradeText' value='' disabled/></td>";
			else
				studentListString += "<td><input type='text' name='secondQG' class='gradeText' value='" + secondQG + "' disabled/></td>";
			
			if(thirdQG == null || thirdQG == -1)
				studentListString += "<td><input type='text' name='thirdQG' class='gradeText' value='' disabled/></td>";
			else
				studentListString += "<td><input type='text' name='thirdQG' class='gradeText' value='" + thirdQG + "' disabled/></td>";
			
			if(fourthQG == null || fourthQG == -1)
				studentListString += "<td><input type='text' name='fourthQG' class='gradeText' value='' disabled/></td>";
			else
				studentListString += "<td><input type='text' name='fourthQG' class='gradeText' value='" + fourthQG + "' disabled/></td>";
			
			studentListString += "<td><input type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Edit' "
								+ "onclick=editGrades('" + studentId + "','" + subjectCode + "') /></td>";
			studentListString += "</tr>";
		}
		studentListString += "</table>";
		pw.write(studentListString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
