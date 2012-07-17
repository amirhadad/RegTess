package htmlcompare.servlet;



import htmlcompare.client.HTMLComparerStub;
import htmlcompare.client.HTMLComparerStub.HTMLCompare;
import htmlcompare.client.HTMLComparerStub.HTMLCompareResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;

/*
import org.apache.axis2.AxisFault;

import com.ctc.wstx.io.BufferRecycler;
*/
/**
 * Servlet implementation class HTMLComparerServlet
 */
public class HTMLComparerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HTMLComparerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	String context;
	public void init(final ServletConfig config) {
		context = config.getServletContext().getRealPath("/");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        String reportDate = dateFormat.format(calendar.getTime());
        String reportTime = timeFormat.format(calendar.getTime());
 

		String sNew = request.getParameter("newhtml");
		String sOld = request.getParameter("oldhtml");
		
		String Destination = request.getParameter("report");
		
		String Info = request.getParameter("info");
		
		String locationtype = request.getParameter("locationtype");

		
		if(Info == null)
			Info = "";
		else
			Info = " for " + Info + " page";
		
		if(Destination == null)
			Destination = "DefaultRegressionReport_"+reportDate.replace('/', '-')+"_"+reportTime.replace(':', '|')+".html";
			
		String outputPath;
		if (locationtype != null && locationtype.compareToIgnoreCase("absolute")>=0) 
			outputPath = Destination;

		else
			outputPath = context + Destination;
		
		try {
			HTMLComparerStub stub = new HTMLComparerStub();
			//sOld,String sNew,String OutputDest
			HTMLCompare htmlCompare = new HTMLCompare();
			htmlCompare.setOutputDest(outputPath);
			HTMLCompareResponse resp = new HTMLCompareResponse();

			htmlCompare.setSNew(sNew);
			htmlCompare.setSOld(sOld);

			resp = stub.hTMLCompare(htmlCompare);
			String theReport = resp.get_return();
			theReport = theReport.replace("/Content", "Content");
			out.println(theReport);
			out.close();

		} catch (Exception e) {
			out.println("Error 404 - Not Found");
		}
		finally
		{
			out.close();
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
