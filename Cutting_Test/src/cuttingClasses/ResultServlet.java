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
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ResultsDbUtil resultsDbUtil;
	
	@Resource(name="jdbc/cutting_test")
       
	DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException{
        super.init();
        try {
        	resultsDbUtil = new ResultsDbUtil(dataSource);
        }catch(Exception exc) {
        	throw new ServletException(exc);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String theCommand = request.getParameter("command");
		
		if(theCommand == null) {
			theCommand = "LIST";
		}
		switch(theCommand) {
		case "LIST":
			listResults(request, response);
			break;
			
		case "GET":
			getResult(request, response);
			break;
			
		case "LOAD":
			loadResults(request, response);
			break;
			
		case"ADD":
			addResults(request, response);
			break;
			
		case"DELETE":
			deleteResults(request, response);
			break;
			
		default:
			listResults(request, response);
		}
		
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteResults(HttpServletRequest request, HttpServletResponse response) {
		// get id from form
		
		String theResultId = request.getParameter("RESULTID");
		
		//delete result from database
		
		resultsDbUtil.deleteResults(theResultId);
		
		//return list from database
		
		listResults(request,response);
		
	}

	private void addResults(HttpServletRequest request, HttpServletResponse response) {
		// read parameters from form
		
		int testId = Integer.parseInt(request.getParameter("testId"));
		double wholesaleCost = Double.parseDouble(request.getParameter("wholesaleCost"));
		double profitMargin = Double.parseDouble(request.getParameter("profitMargin"));
		double yield = Double.parseDouble(request.getParameter("yield"));
		double epWeight = Double.parseDouble(request.getParameter("epWeight"));
		double epCost = Double.parseDouble(request.getParameter("epCost"));
		double epRetail = Double.parseDouble(request.getParameter("epRetail"));
		double valueOfUsableProduct = Double.parseDouble(request.getParameter("valueOfUsableProduct"));
		double costOfUsableProduct = Double.parseDouble(request.getParameter("costOfUsableProduct"));
		
		//create object
		Results theResult = new Results(testId, wholesaleCost, profitMargin, yield, epWeight, epCost, epRetail, valueOfUsableProduct, costOfUsableProduct);
		
		//add to database
		
		resultsDbUtil.addResults(theResult);
		
		//send updated list
		
		listResults(request, response);
		
		
	}

	private void loadResults(HttpServletRequest request, HttpServletResponse response) {
		// get id from form
		
		String theResultId = request.getParameter("RESULTID");
		
		//retrieve result from database
		
		Results theResult;
		try {
			theResult = resultsDbUtil.getResult(theResultId);
		
		//set attribute
		
		request.setAttribute("THERESULT", theResult );
		
		//create dispatcher
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/Update_Result.jsp");
		
		//forward request
		
		dispatcher.forward(request, response);
		
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void getResult(HttpServletRequest request, HttpServletResponse response) {
		try {
		
		
		// get id from form
		
		String theResultId = request.getParameter("RESULTID");
		
		//get object from database
		
		Results theResult = resultsDbUtil.getResult(theResultId);
		
		request.setAttribute("THE_RESULT", theResult);
		
		//create request dispatcher
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("Get_Results.jsp");
		
		//forward request
		
		dispatcher.forward(request, response);
		
		}catch(SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void listResults(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		// create list from database
		
			List <Results> resultList = resultsDbUtil.getResultList();
			
		//set attribute	
		request.setAttribute("Results_List", resultList);
		
		//create dispatcher
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/List_Results.jsp");
		
		//forward response
		dispatcher.forward(request, response);
		
		
			
		} catch (SQLException | ServletException | IOException e) {
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
