package Servlet.adminajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Beans.Clearance;
import Beans.Student;
import DAO.UserDAO;

/**
 * Servlet implementation class ShowStudentClearance
 */
@WebServlet("/ShowStudentClearance")
public class ShowStudentClearance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentClearance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		ArrayList<Student> studentList = userDao.getStudentAccounts();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		
		PrintWriter pw = response.getWriter();
		String showStudentClearanceString = "<h4>Clearances</h4>"
				+ "<table><tr><th>Student</th><th>Clearances</th><th>Date Issued</th><th>Date Resolved</th></tr>";
		for(int i = 0; i < studentList.size(); i++){
			Student student = studentList.get(i);
			showStudentClearanceString += "<tr id=" + student.getStudentId() + ">"
					+ "<td>" + student.getName() + "</td>";
			
				for(int j = 0; j < student.getClearanceList().size(); j++){
					Clearance clearance = student.getClearanceList().get(j);
					String button = "<input type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Edit' "
										+ "onclick=editClearance('" + clearance.getClearanceId() + "') />"
								  + "<input type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Delete' "
								  		+ "onclick=deleteClearance('" + clearance.getClearanceId() + "') />";
					String dateIssued = clearance.getDateTimeIssued().toString(fmt);
					String dateResolved = clearance.getDateTimeResolved() == null ? "-" : clearance.getDateTimeResolved().toString(fmt);
					
					if(j > 0){
						showStudentClearanceString += "<tr><td></td>";
					}
					showStudentClearanceString += 
							  "<td><textArea id=" + clearance.getClearanceId() + " rows='2' cols='30'>" + clearance.getClearance() + "</textArea></td>"
							+ "<td>" + dateIssued + "</td>"
							+ "<td>" + dateResolved + "</td><td>" + button + "</td></tr>";
				}
		}
		showStudentClearanceString += "</table>";
		pw.write(showStudentClearanceString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
