package cuttingClasses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CutterServlet
 */
@WebServlet("/CutterServlet")
public class CutterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CutterDbUtil  cutterDbUtil;
    private DataSource dataSource;
    
    @Resource(name = "jdbc/cutting_test")
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
    	super.init();
    	try {
    		cutterDbUtil = new CutterDbUtil(dataSource);
    			
    		
    	}catch(Exception exc) {
    		throw new ServletException();
    		
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command");
		
		if(theCommand == null) {
			theCommand = "DEFAULT";
		}
		switch(theCommand) {
		case "GET":
			getCutter(request, response);
			break;
			
		case"ADD":
			addCutter(request, response);
			break;
			
		case"DELETE":
			deleteCutter(request, response);
			break;
			
		case"LOAD":
			loadCutter(request, response);
			break;
		
		case"UDATE":
			updateCutter(request, response);
			break;
			
		case"LIST":
		
				listCutters(request, response);
			
			break;
			
		default:
			listCutters(request,response);
		}
	}

	private void updateCutter(HttpServletRequest request, HttpServletResponse response) {
		try {
		// get information from form
		
		String firstName = "firstName";
		String lastName = "lastName";
		int numOfYears = Integer.parseInt("numOfYears");

		//create Cutter
		
		Cutter theCutter = new Cutter(firstName, lastName, numOfYears);
		
		//update Cutter in database
		
		
			cutterDbUtil.updateCutter(theCutter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return list
		
		listCutters(request, response);
		
		
		
	}

	private void listCutters(HttpServletRequest request, HttpServletResponse response)  {
		// create a list from database
		
		List<Cutter> cuttersList;
		try {
			cuttersList = cutterDbUtil.getCutterList();
	
		//set request attribute
		
		request.setAttribute("CUTTERS_LIST", cuttersList);
		
		//create dispatcher
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/Cutters_List.jsp");
		
		//forward dispatcher
		
		
			dispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadCutter(HttpServletRequest request, HttpServletResponse response) {
		try {
		//get id from form
		
		String theCutterId = request.getParameter("CUTTER_ID");
		
		//get cutter from the database
		
		Cutter theCutter = cutterDbUtil.getCutter(theCutterId);
		
		//set request attribute 
		
		request.setAttribute("THE_CUTTER", theCutter);
		
		//create dispatcher
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/Load_Cutters.jsp");
		
		//forward request
		
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteCutter(HttpServletRequest request, HttpServletResponse response) {
		// get id from form
		
		String theCutterId = request.getParameter("CUTTER_ID");
		
		//delete from database
		
		try {
			cutterDbUtil.deleteCutter(theCutterId);
		
		
		//return list
			
			listCutters(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addCutter(HttpServletRequest request, HttpServletResponse response) {
		Cutter theCutter = null;
		
		// get information from field
		String firstName = "firstName";
		String lastName = "lastName";
		int numOfYears = Integer.parseInt("numOfYears");
		
		//create cutter object
		theCutter = new Cutter(firstName, lastName, numOfYears);
		
		//add cutter to database
		cutterDbUtil.addCutter(theCutter);
		
		//return list
		
		listCutters(request, response);
		
	}

	private void getCutter(HttpServletRequest request, HttpServletResponse response) {
		try {
		
		//get id from form
		
		String theCutterId = request.getParameter("CUTTER_ID");
		
		// get primal from database
		
		Cutter theCutter =cutterDbUtil.getCutter(theCutterId);
		
		//set attribute
		
		request.setAttribute("THE_CUTTER", theCutter);
		
		//get request dispatcher
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/Get_Cutter.jsp");
		
		//forward request
		
		
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
