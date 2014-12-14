package Servlet.studentajax;

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
import DAO.UserDAO;

/**
 * Servlet implementation class ShowClearance
 */
@WebServlet("/ShowClearance")
public class ShowClearance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowClearance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		
		UserDAO userDao = new UserDAO();
		ArrayList<Clearance> clearanceList = userDao.FetchStudentClearance(studentId);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		
		PrintWriter pw = response.getWriter();
		String clearanceString = "";
		clearanceString += "<h4>Clearance Record:</h4>";
		if(clearanceList != null){
			int ctr = clearanceList.size();
			
			for(int i = 0; i < clearanceList.size(); i++){
				if(i == 0){
					clearanceString += "<table><tr><th>Date Issued</th><th>Issue</th></tr>";
				}
				
				if(clearanceList.get(i).getDateTimeResolved() == null){
					ctr--;
					String dateTime = clearanceList.get(i).getDateTimeIssued().toString(fmt);
					clearanceString += "<tr>";
					clearanceString += "<td>" + dateTime + "</td>";
					clearanceString += "<td>" + clearanceList.get(i).getClearance() + "</td>";
					clearanceString += "</tr>";
				}
			}
			
			if(ctr == clearanceList.size()){
				clearanceString += "Cleared!";
			}
		} else {
			clearanceString += "Cleared!";
		}
		clearanceString += "</table>";
		pw.write(clearanceString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
