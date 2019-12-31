package cuttingClasses;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CuttingTestServlet
 */
@WebServlet("/CuttingTestServlet")
public class CuttingTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuttingTestDbUtil cutTestDbUtil;
	
	@Resource(name ="jdbc/cutting_test" )
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		//create cutting test with datasource pass connection pool
		super.init();
		try {
			cutTestDbUtil = new CuttingTestDbUtil(dataSource);
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//read the command parameter
		String theCommand = request.getParameter("COMMAND");
		
		//if command is missing default to index
		
		if(theCommand == null) {
			theCommand = "LIST";
		}
	
		//route to the appropriate method
		
		switch(theCommand) {
		
		case"LIST":
			listCuttingTest(request,response);
			break;
			
		case"ADD":
			addCuttingTest(request,response);
			break;
			
		case"GET":
			getCuttingTest(request,response);
			break;
			
		case"DELETE":
			deleteCuttingTest(request,response);
			break;
			
		case"LOAD":
			loadCuttingTest(request, response);
			
		default:
			listCuttingTest(request, response);
		}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	private void loadCuttingTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read cuttingTestId  from form
		
		String cuttingTestId = request.getParameter("cuttingTestId");
		
		//get cuttingTest from database (db util)
		
		CuttingTest cuttingTest = cutTestDbUtil.getTest(cuttingTestId);
		
		//place cutting test in request attribute
		
		request.setAttribute("cuttingTest", cuttingTest);
		
		//send to jsp update-student-form.jsp 
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/updateCuttingTest.jsp");
		
		dispatcher.forward(request, response);
				
	}

	private void deleteCuttingTest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		//read cutting test id from form
		
		String cuttingTestId = request.getParameter("cuttingTestId");
		
		//delete cutting test from database
		
		cutTestDbUtil.deleteTest(cuttingTestId);
		
		//return list 
		listCuttingTest(request,response);
		
	}

	private void getCuttingTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get id from form
		
		String cuttingTestId =request.getParameter("cuttingTestId");
		
		//get cutting test from database
		
		CuttingTest theCuttingTest = cutTestDbUtil.getTest(cuttingTestId);
		
		//place student in a request
		
		request.setAttribute("cuttingTest", theCuttingTest);
		
		//send request to jsp
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/get_cutting_test.jsp");
		
		dispatcher.forward(request, response);
	}

	private void addCuttingTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//create a cutting test object
		
		CuttingTest cuttingTest;
		
		// read new cutting test information from form
		
		int primalId = Integer.parseInt("primalId");
		double weight = Double.parseDouble("weight");
		double trim = Double.parseDouble("trim");
		double profitMargin = Double.parseDouble("profitMargin");
		double wholeCost = Double.parseDouble("wholeCost");
		double yield = Double.parseDouble("yield");
		double epWeight = Double.parseDouble("epWeight");
		double epCost = Double.parseDouble("epCost");
		double epRetail = Double.parseDouble("epRetail");
		double valueOfUsableProduct = Double.parseDouble("valueOfUsableProduct");
		double costOfUsableProduct = Double.parseDouble("CostOfUsableProduct");
		
		
		cuttingTest = new CuttingTest(primalId, weight,  trim,  profitMargin,
				 wholeCost,  yield,  epWeight,  epCost, epRetail,
				 valueOfUsableProduct,  costOfUsableProduct);
		
		
		//add object to database
		
		cutTestDbUtil.addTest(cuttingTest);
		
		//return to the main page
		
		listCuttingTest(request, response);
		
		
	}

	private void listCuttingTest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		//Create list
		List<CuttingTest> cutTestList = cutTestDbUtil.getTestList();
		
		//set parameter
		
		request.setAttribute( "cutTestList",cutTestList);
		
		//get request
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cutting_test_list.jsp");
				
		//forward dispatcher
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
