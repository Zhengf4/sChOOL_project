package Servlet.adminajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import DAO.UserDAO;

/**
 * Servlet implementation class EditAnnouncement
 */
@WebServlet("/EditAnnouncement")
public class EditAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAnnouncement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String announcement = request.getParameter("message");
		int announcementId = Integer.valueOf(request.getParameter("announcementId"));
		
		UserDAO userDao = new UserDAO();
		userDao.updateAnnouncement(announcementId, announcement);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
