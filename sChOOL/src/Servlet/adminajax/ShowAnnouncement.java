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

import Beans.Announcement;
import DAO.UserDAO;

/**
 * Servlet implementation class ShowAnnouncement
 */
@WebServlet("/ShowAnnouncement")
public class ShowAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		
		UserDAO userDao = new UserDAO();
		ArrayList<Announcement> announcementList = userDao.FetchAnnouncements(adminId);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		
		PrintWriter pw = response.getWriter();
		String showMessageString = ""
						  + "<h4>Announcements</h4>"
						  + "<table><tr><th>Announcement</th><th>Date Issued</th></tr>";
		for(int i = 0; i < announcementList.size(); i++){
			Announcement announcement = announcementList.get(i);
			showMessageString += "<tr id=" + announcement.getAnnouncementId() + ">";
			showMessageString += "<td><textarea rows='4' cols='50' disabled>" + announcement.getAnnouncement() + "</textarea></td>";
			showMessageString += "<td>" + announcement.getDateIssued().toString(fmt) + "</td>";
			showMessageString += "<td><input type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Edit' "
								+ "onclick=editAnnouncement('" + announcement.getAnnouncementId() + "') />"
								   + "<input type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Delete' "
								+ "onclick=deleteAnnouncement('" + announcement.getAnnouncementId() + "') /></td></tr>";
		}
		showMessageString += "</table>";
		showMessageString += "<div id='submitArea'>";
		showMessageString += "<textArea id='announcementBox' rows='3' cols='50'></textArea>"
							+ "<input id='submitAnnounceBtn' type='button' class='btn btn-success' style='width:50px;height:25px;font-size:10px' value='Add' />";
		showMessageString += "</div>";
		pw.write(showMessageString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
