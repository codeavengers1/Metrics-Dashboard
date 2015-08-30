
package com;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
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
		String action = request.getParameter("action")==null?"":request.getParameter("action");
		if(action.equalsIgnoreCase("loadFlexBox")){
			JSONObject outputObj = new JSONObject();
			JSONObject flxData = new JSONObject();
			try{
				int instances = 50;
				JSONArray jarr = new JSONArray();
				//filenames
				String[] logTypes = {"DEBUG","INFO","WARNING","ERROR","Error_log"};
				for(int i = 0; i<logTypes.length;i++){
					JSONObject obj = new JSONObject();
					obj.put("id", logTypes[i]);
					obj.put("name", logTypes[i]);
					jarr.put(obj);
					obj = null;
				}
				flxData = new JSONObject();
				flxData.put("total", logTypes.length);
				flxData.put("results", jarr);
				jarr = null;
				outputObj.put("logTypes", flxData);
				//WR#
				String[] wrNames = {"WR98601","WR10512","WR98621","WR98321"};
				jarr = new JSONArray();
				int count = 0;
				for(int i = 0; i<wrNames.length;i++){
					JSONObject obj = new JSONObject();
					obj.put("id", wrNames[i]);
					obj.put("name", wrNames[i]);
					jarr.put(obj);
					obj = null;
					count++;
				}
				
				for(int i = 98701; i<98899;i++){
					if(i%6==0){
						JSONObject obj = new JSONObject();
						obj.put("id", "WR"+i);
						obj.put("name", "WR"+i);
						jarr.put(obj);
						obj = null;
						count++;
					}
				}
				flxData = new JSONObject();
				flxData.put("total", count);
				flxData.put("results", jarr);
				jarr = null;
				outputObj.put("wrNames", flxData);
			}catch(Exception e3){}
			PrintWriter out = response.getWriter();
			out.print(outputObj);
		}else{
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
