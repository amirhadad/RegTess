package htmlcompare.client;


import java.rmi.RemoteException;

import htmlcompare.client.HTMLComparerStub.HTMLCompare;
import htmlcompare.client.HTMLComparerStub.HTMLCompareResponse;
import htmlcompare.client.HTMLComparerStub.HTMLDetailedCompare;
import htmlcompare.client.HTMLComparerStub.HTMLDetailedCompareResponse;

import org.apache.axis2.AxisFault;


public class Runner {
	public static void main(String[] args) {
		try {
			HTMLComparerStub stub = new HTMLComparerStub();
			//sOld,String sNew,String OutputDest
			HTMLCompare htmlCompare = new HTMLCompare();
			HTMLDetailedCompare htmlDetailCompare = new HTMLDetailedCompare();
			
			
			htmlCompare.setOutputDest("C:\\test7\\NormalReport4.html");
			htmlDetailCompare.setOutputDest("C:\\test7\\DetailedReport4.html");
			htmlCompare.setSNew("http://localhost/1.html");
			htmlDetailCompare.setSNew("http://localhost/1.html");
			htmlCompare.setSOld("http://localhost/1.html");
			htmlDetailCompare.setSOld("http://localhost/2.html");
			
			HTMLCompareResponse resp;
			
			resp = stub.hTMLCompare(htmlCompare);
			
			System.out.println(resp.get_return());
			
			HTMLDetailedCompareResponse respDetialed;
			respDetialed = stub.hTMLDetailedCompare(htmlDetailCompare);
			//stub.hTMLDetailedCompare(htmlDetailCompare);
			System.out.println(respDetialed.get_return());
			stub = null;
			//System.out.println(res.getHTMLCompareReturn());
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("Error occored: " + e.getMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
