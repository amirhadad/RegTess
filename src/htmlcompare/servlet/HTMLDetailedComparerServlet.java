package htmlcompare.servlet;

import htmlcompare.client.HTMLComparerStub;
import htmlcompare.client.HTMLComparerStub.HTMLDetailedCompare;
import htmlcompare.client.HTMLComparerStub.HTMLDetailedCompareResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class HTMLDetailedComparerServlet
 */
public class HTMLDetailedComparerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String context;
	public void init(final ServletConfig config) {
		context = config.getServletContext().getRealPath("/");

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HTMLDetailedComparerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		File f;


		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

		String reportDate = dateFormat.format(calendar.getTime());
		String reportTime = timeFormat.format(calendar.getTime());


		String sNew = request.getParameter("newhtml");
		String sOld = request.getParameter("oldhtml");
		String Destination = request.getParameter("report");
		String locationtype = request.getParameter("locationtype");

		String Info = request.getParameter("info");
		if(Info == null)
			Info = "";
		else
			Info = " for " + Info + " page";

		if(Destination == null || Destination == "")
		{
			Destination = "DefaultRegressionReport_"+reportDate.replace('/', '-')+"_"+reportTime.replace(':', '-')+".html";
			(new File(Destination)).createNewFile();
		}

		String outputPath;
		if (locationtype != null && locationtype.compareToIgnoreCase("absolute")>=0) 
			outputPath = Destination;

		else
			outputPath = context + Destination;
		try {

			HTMLComparerStub stub = new HTMLComparerStub();
			//sOld,String sNew,String OutputDest
			HTMLDetailedCompare htmlCompare = new HTMLDetailedCompare();
			htmlCompare.setOutputDest(outputPath);

			htmlCompare.setSNew(sNew);
			htmlCompare.setSOld(sOld);

			HTMLDetailedCompareResponse resp = stub.hTMLDetailedCompare(htmlCompare);

			String theReport = resp.get_return();
			out.println(theReport);
			out.close();


		} catch (Exception e) {
			out.println(e.getMessage());
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
		// TODO Auto-generated method stub
	}

}
