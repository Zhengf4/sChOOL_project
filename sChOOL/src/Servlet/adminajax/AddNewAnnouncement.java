package Servlet.adminajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import Beans.Announcement;
import DAO.UserDAO;

/**
 * Servlet implementation class AddNewAnnouncement
 */
@WebServlet("/AddNewAnnouncement")
public class AddNewAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		String message = request.getParameter("announcement");
		UserDAO userDao = new UserDAO();
		Announcement announcement = new Announcement();
		announcement.setAnnouncement(message);
		announcement.setDateIssued(new DateTime());
		userDao.addAnnouncement(announcement, adminId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
