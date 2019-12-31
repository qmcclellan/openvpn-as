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
 * Servlet implementation class PrimalServlet
 */
@WebServlet("/PrimalServlet")
public class PrimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @Resource(name="jdbc/cutting_test")
       private DataSource dataSource;
       private PrimalDbUtil primalDbUtil;
       
       
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
    	super.init();
    	
    	try {
    		primalDbUtil = new PrimalDbUtil(dataSource);
    	}catch(Exception exc){
    		throw new ServletException();
    		
    	}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String theCommand = request.getParameter("COMMAND");
		try {
			if(theCommand != null) {
				theCommand= "LIST";	
				}
			switch(theCommand) {
			
			case "LIST":
				listPrimal(request,response);
				break;
			
			case "ADD":
				addPrimal(request,response);
				break;
				
			case "GET":
				getPrimal(request,response);
				break;
				
			case "DELETE":
				deletePrimal(request,response);
				break;
				
			case "LOAD":
				loadPrimal(request,response);
				break;
				
			case "UPDATE":
				updatePrimal(request, response);
				break;
			
			default :
				listPrimal(request,response);
			}
			}catch(Exception exc) {
				exc.printStackTrace();
			}
	}
	

	private void updatePrimal(HttpServletRequest request, HttpServletResponse response) {
		try {
			
		Primal thePrimal;
		
		// read the data from form
		
		 String name = "primalName";
		 String category = "category";
		 Double costLb = Double.parseDouble("costLb");
		 Double retailLb = Double.parseDouble("retailLb");
		
		//create a new primal object
		
		thePrimal = new Primal(name, category, costLb, retailLb);
		
		//update primal from database
		
	
			primalDbUtil.updatePrimal(thePrimal);
		
		
		//return to primal list page
			
			listPrimal(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadPrimal(HttpServletRequest request, HttpServletResponse response) {
		// read primalID from form
		
		String thePrimalId = request.getParameter("primalId");
		
		//get primal from database
		
		try {
			Primal thePrimal = primalDbUtil.getPrimal(thePrimalId);
		
		//put primal in request attribute
			request.setAttribute("THE_PRIMAL",thePrimal);
		
		//set dispatcher to update page via jsp
		
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/PrimalList.jsp");
		//forward request
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void deletePrimal(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		// Read primal id from form
		
		String thePrimalId = request.getParameter("primalId");
	
		
			
		//delete
		
			primalDbUtil.deletePrimal(thePrimalId);
			
			
		//return list
			
			listPrimal(request, response);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getPrimal(HttpServletRequest request, HttpServletResponse response) {
		//get id from form
		
				String PrimalId =request.getParameter("PrimalId");
				
				//get primal from database
				
				Primal thePrimal;
				try {
					thePrimal = primalDbUtil.getPrimal(PrimalId);
				
				//place student in a request
				
				request.setAttribute("THE_PRIMAL", thePrimal);
				
				//send request to jsp
				
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("/Get_Primal.jsp");
				
				dispatcher.forward(request, response);
			
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}

	private void addPrimal(HttpServletRequest request, HttpServletResponse response) {
		//create object
		
		Primal thePrimal= null;
		
		//get information from the field
		
		 String name = "primalName";
		 String category = "category";
		 Double costLb = Double.parseDouble("costLb");
		 Double retailLb = Double.parseDouble("retailLb");
		
		
		
		//create primal
		
		thePrimal = new Primal(name, category, costLb, retailLb);
		
		//add to database
		
		primalDbUtil.addPrimal(thePrimal);
		
		//return to list
		
		listPrimal(request, response);
	}

	private void listPrimal(HttpServletRequest request, HttpServletResponse response) {
		// get list
		
		List<Primal> primalList;
		try {
			primalList = primalDbUtil.getPrimals();		
		
		//get parameter
		
		request.setAttribute("PRIMAL_LIST", primalList);
		
		//get request
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/Primal_List.jsp");
		
		//forwad request
		
		dispatcher.forward(request, response);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
