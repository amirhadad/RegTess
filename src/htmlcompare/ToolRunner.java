package htmlcompare;

public class ToolRunner {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String OutputDest = "C:\\test8\\NormalReport5.html";
		String dOutputDest = "C:\\test8\\DetailedReport5.html";
		String SNew = "http://localhost/1.html";
		String SOld = "http://localhost/2.html";
		
		htmlcompare.HTMLComparer htmlCompare = new HTMLComparer();
		
		htmlCompare.HTMLCompare(SOld, SNew, OutputDest);
		htmlCompare.HTMLDetailedCompare(SOld, SNew, dOutputDest);
		htmlCompare = null;
		int i  = 0;
		i++;
	}
}