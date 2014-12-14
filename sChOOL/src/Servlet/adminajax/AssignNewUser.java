package Servlet.adminajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AssignNewUser
 */
@WebServlet("/AssignNewUser")
public class AssignNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignNewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String assignNewUserString = "<h4>Add new user: </h4><table>";
			  assignNewUserString += "<tr><td>User Name:</td><td><input type='text' name='userName'/></td></tr>";
			  assignNewUserString += "<tr><td>User Id:</td><td><input type='text' name='userId'/></td></tr>";
			  assignNewUserString += "<tr><td>Profession:</td><td><select name='profession'><option value='admin'>admin</option>"
			  								+ "<option value='student'>student</option>"
			  								+ "<option value='faculty'>faculty</option></select></td></tr>";
			  assignNewUserString += "<tr><td>password:</td><td><input type='password' name='password'/></td></tr>";
			  assignNewUserString += "<tr><td colspan=2><input id='addNewUser' type='button' class='btn btn-success' "
			  		+ "style='width:50px;height:25px;font-size:10px' value='Submit' /></td></tr></table>";
		pw.write(assignNewUserString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
