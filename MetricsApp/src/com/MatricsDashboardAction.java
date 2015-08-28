
package com;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class MatricsDashboardAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatricsDashboardAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside MatricsDashboardAction");
		/*String title=request.getParameter("title")==null?"":request.getParameter("title");
		String wrNum=request.getParameter("wrNum")==null?"":request.getParameter("wrNum");
		String fileName=request.getParameter("fileName")==null?"":request.getParameter("fileName");
		String filter=request.getParameter("filter")==null?"":request.getParameter("filter");
		String date=request.getParameter("date")==null?"":request.getParameter("date");*/
		JSONObject inputObj = new JSONObject();
		try{
			inputObj.put("title", request.getParameter("title")==null?"":request.getParameter("title"));
			inputObj.put("wrNum", request.getParameter("wrNum")==null?"":request.getParameter("wrNum"));
			inputObj.put("fileName", request.getParameter("fileName")==null?"":request.getParameter("fileName"));
			inputObj.put("filter", request.getParameter("filter")==null?"":request.getParameter("filter"));
			inputObj.put("date", request.getParameter("date")==null?"":request.getParameter("date"));
		}catch(Exception e){}
		
		JSONObject outputObj = (new ReadStringFromFile()).logProcess(inputObj);
		//int lineCount = readStringFromFile.logProcess(title,wrNum,fileName,filter,date);
		//System.out.println("title="+title+"::wrNum="+wrNum+"::fileName="+fileName+"::filter="+filter+"::date="+date+"::lineCount="+lineCount+"::");
		PrintWriter out = response.getWriter();
		out.print(outputObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
