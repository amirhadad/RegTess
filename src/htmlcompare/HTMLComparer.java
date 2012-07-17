package htmlcompare;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;


import org.apache.jasper.tagplugins.jstl.core.Url;
import org.outerj.daisy.diff.DaisyDiff;
import org.outerj.daisy.diff.HtmlCleaner;
import org.outerj.daisy.diff.XslFilter;
import org.outerj.daisy.diff.html.HTMLDiffer;
import org.outerj.daisy.diff.html.HtmlSaxDiffOutput;
import org.outerj.daisy.diff.html.TextNodeComparator;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import sun.misc.BASE64Encoder;




public class HTMLComparer {

	private String ReadReport(String sOutputSource,String sOutputDest)
	{
		String sResponce = "";
		String line = "";
		File fResponce = new File(sOutputSource);
		File fDest = new File(sOutputDest);
		BufferedReader bf = null;
		BufferedWriter bfw = null;
		try {
			bf = new BufferedReader(new FileReader(fResponce));
			bfw = new BufferedWriter(new FileWriter(fDest));
			line = bf.readLine();

			while(line != null)
			{
				sResponce += line;
				line = bf.readLine();
			}
			
			bfw.write(sResponce);
			//bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				bf.close();
				bfw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sResponce;
	}
	private InputStream AutheticateForTestMyhospitalsCordelta(String sURL) throws IOException
	{
		BASE64Encoder encoder = new BASE64Encoder();
		URL url = null;
		try {
			url = new URL(sURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String encoding = Base64.encode("Cordelta:C0rd3lta!".getBytes());
		String encoded = new sun.misc.BASE64Encoder().encode ("Cordelta:C0rd3lta!".getBytes());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//connection.setRequestMethod("POST");
		//connection.setDoOutput(true);
		connection.setRequestProperty  ("Authorization", "Basic " + encoded);
		InputStream oldStream = (InputStream)connection.getInputStream();
		return oldStream;
	}
	public synchronized String HTMLCompare(String sOld,String sNew,String OutputDest) {

		boolean htmlOut = true;
		String outputFile = "temp.html";
		String[] css = new String[]{};
		InputStream oldStream = null;
		InputStream newStream = null;

		InputSource oldSource = null;
		InputSource newSource = null;


		SAXTransformerFactory tf = (SAXTransformerFactory) TransformerFactory
				.newInstance();

		TransformerHandler result = null;
		StreamResult resultStream = null;
		try {
			result = tf.newTransformerHandler();
			resultStream = new StreamResult(new File(outputFile));
			result.setResult(resultStream);

			if (sOld.startsWith("http://")) {
				// Needs authontication
				if(sOld.toLowerCase().contains("test.myhospitals"))
					oldStream = AutheticateForTestMyhospitalsCordelta(sOld);
				// doesn't need authontication
				else
					oldStream = new URI(sOld).toURL().openStream();
			}
			else {
				oldStream = new FileInputStream(sOld);
			}
			if (sNew.startsWith("http://")) {
				if(sNew.toLowerCase().contains("test.myhospitals"))
					newStream = AutheticateForTestMyhospitalsCordelta(sNew);
				// doesn't need authentication
				else
					newStream = new URI(sNew).toURL().openStream();
			}
			else {
				newStream = new FileInputStream(sNew);
			}

			XslFilter filter = new XslFilter();

			//HTML report generation:
			ContentHandler postProcess = htmlOut? filter.xsl(result,
					"org/outerj/daisy/diff/htmlheader.xsl"):result;

			Locale locale = Locale.getDefault();
			String prefix = "diff";

			HtmlCleaner cleaner = new HtmlCleaner();


			oldSource = new InputSource(oldStream);
			newSource = new InputSource(newStream);

			DomTreeBuilder oldHandler = new DomTreeBuilder();
			cleaner.cleanAndParse(oldSource, oldHandler);
			System.out.print(".");
			TextNodeComparator leftComparator = new TextNodeComparator(
					oldHandler, locale);

			DomTreeBuilder newHandler = new DomTreeBuilder();
			cleaner.cleanAndParse(newSource, newHandler);
			System.out.print(".");
			TextNodeComparator rightComparator = new TextNodeComparator(
					newHandler, locale);

			postProcess.startDocument();
			postProcess.startElement("", "diffreport", "diffreport",
					new AttributesImpl());
			doCSS(css, postProcess);
			postProcess.startElement("", "diff", "diff",
					new AttributesImpl());
			HtmlSaxDiffOutput output = new HtmlSaxDiffOutput(postProcess,
					prefix);

			HTMLDiffer differ = new HTMLDiffer(output);
			differ.diff(leftComparator, rightComparator);
			System.out.print(".");
			postProcess.endElement("", "diff", "diff");
			postProcess.endElement("", "diffreport", "diffreport");
			postProcess.endDocument();

			//return  result.toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			System.out.println("TransformerConfigurationException error occured! " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException error occured! " + e.getMessage());
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("SAXException error occured! " + e.getMessage());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("URISyntaxException error occured! " + e.getMessage());
		}
		finally {
			try {
				if(oldStream != null) 
					oldStream.close();
			} catch (IOException e) {
			}
			try {
				if(newStream != null) 
					newStream.close();
			} catch (IOException e) {
			}
		}
		return ReadReport(outputFile,OutputDest);
	}
	public String HTMLDetailedCompare(String sOld,String sNew,String OutputDest) 
	{
		boolean htmlOut = true;
		String outputFile = "tempDetails.html";
		String[] css = new String[]{};
		InputStream oldStream = null;
		InputStream newStream = null;

		InputStreamReader oldReader = null;
		BufferedReader oldBuffer = null;

		InputStreamReader newISReader = null;
		BufferedReader newBuffer = null;

		SAXTransformerFactory tf = (SAXTransformerFactory) TransformerFactory
				.newInstance();

		TransformerHandler result;

		try
		{
			result = tf.newTransformerHandler();

			result.setResult(new StreamResult(new File(outputFile)));

			if (sOld.startsWith("http://")) {
				oldStream = new URI(sOld).toURL().openStream();
			}
			else {
				oldStream = new FileInputStream(sOld);
			}
			if (sNew.startsWith("http://")) {
				newStream = new URI(sNew).toURL().openStream();
			}
			else {
				newStream = new FileInputStream(sNew);
			}

			XslFilter filter = new XslFilter();

			//Detailed HTML report generation
			ContentHandler postProcess = htmlOut? filter.xsl(result,
					"org/outerj/daisy/diff/tagheader.xsl"):result;
			postProcess.startDocument();
			postProcess.startElement("", "diffreport", "diffreport",
					new AttributesImpl());
			postProcess.startElement("", "diff", "diff",
					new AttributesImpl());
			System.out.print(".");



			oldReader = new InputStreamReader(oldStream);
			oldBuffer = new BufferedReader(oldReader);

			newISReader = new InputStreamReader(newStream);
			newBuffer = new BufferedReader(newISReader);
			DaisyDiff.diffTag(oldBuffer, newBuffer, postProcess);



			System.out.print(".");
			postProcess.endElement("", "diff", "diff");
			postProcess.endElement("", "diffreport", "diffreport");
			postProcess.endDocument();
			/*try {
				oldBuffer.close();
				newBuffer.close();
				oldReader.close();
				newISReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/


			//return  result.toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			System.out.println("TransformerConfigurationException error occured! " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException error occured! " + e.getMessage());
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.println("SAXException error occured! " + e.getMessage());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("URISyntaxException error occured! " + e.getMessage());


		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				oldBuffer.close();
				newBuffer.close();
				oldReader.close();
				newISReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ReadReport(outputFile,OutputDest);
	}
	private static void doCSS(String[] css, ContentHandler handler) throws SAXException {
		handler.startElement("", "css", "css",
				new AttributesImpl());
		for(String cssLink : css){
			AttributesImpl attr = new AttributesImpl();
			attr.addAttribute("", "href", "href", "CDATA", cssLink);
			attr.addAttribute("", "type", "type", "CDATA", "text/css");
			attr.addAttribute("", "rel", "rel", "CDATA", "stylesheet");
			handler.startElement("", "link", "link",
					attr);
			handler.endElement("", "link", "link");
		}

		handler.endElement("", "css", "css");

	}


}
