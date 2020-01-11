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
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private TestDbUtil testDbUtil;
       
       @Resource(name="jdbc/cutting_test")
       DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
    	super.init();
    	try {
    		testDbUtil = new TestDbUtil(dataSource);
    	}catch(Exception exc) {
    		throw new ServletException(exc);
    	}
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
		String theCommand = request.getParameter("COMMAND");
		
		if(theCommand == null) {
			theCommand = "LIST";
		}
		
		//switch methods for servlet
		
		switch(theCommand) {
		
		case "LIST":
			listTests(request, response);
			break;
		
		case"UPDATE":
			updateTests(request, response);
			break;
			
		case"ADD":
			addTests(request, response);
			break;
		
		case"LOAD":
			loadTest(request, response);
			break;
			
		case"DELETE":
			delete(request, response);
			break;
		}
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String theTestId = request.getParameter("testId");
		try {
		testDbUtil.deleteTest(theTestId);
		
		listTests(request,response);
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	private void loadTest(HttpServletRequest request, HttpServletResponse response) {
		// get request from form
		
		String theTestId = 	request.getParameter("TESTID");
		
		// get test from database
		
		try {
		Test theTest= testDbUtil.getTest(theTestId);
			
		//set request attribute
		request.setAttribute("THE_TEST", theTest);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/Update_Test.jsp");
		
		//forward request
		
		dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addTests(HttpServletRequest request, HttpServletResponse response) {
		try {
		// get parameters
			String cutterName = request.getParameter("cutterName");
			int cutterId = Integer.parseInt(request.getParameter("cutterId"));
			String primalName = request.getParameter("primalName");
			int primalId = Integer.parseInt(request.getParameter("primalId"));
			double weight = Double.parseDouble(request.getParameter("weight"));
			double trim = Integer.parseInt(request.getParameter("trim"));
			double waste = Integer.parseInt(request.getParameter("waste"));
		//create test
		Test theTest = new Test(cutterName, cutterId, primalName, primalId, weight, trim, waste);
		//add test to database
		
		testDbUtil.addTest(theTest);
		//return new list
		
		listTests(request, response);
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
	}

	private void updateTests(HttpServletRequest request, HttpServletResponse response) {
		try {
		
		// get test id
		
		String theTestId = request.getParameter("TESTID");
		
		//get test from database
		
		Test theTest = testDbUtil.getTest(theTestId);	
		
		//place test in request attribute
		
		request.setAttribute("THETEST", theTest);
		
		//send to jsp
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/Update_Test");
		
		//send dispatcher 
			
		dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
	}


	private void listTests(HttpServletRequest request, HttpServletResponse response) {
		// CREATE A LIST from database
		try {
			List <Test> testList = testDbUtil.getTests();
			
			//set list object to be sent to  viewer
			request.setAttribute("Test_List", testList);
			
			//read request from jsp
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("Test_List.jsp");
			//send responsee
			dispatcher.forward(request, response);
		} catch (SQLException | ServletException | IOException e ) {
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
