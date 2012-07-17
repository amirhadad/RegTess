

/**
 * HTMLComparerTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package htmlcompare;

    /*
     *  HTMLComparerTest Junit test case
    */

    public class HTMLComparerTest extends junit.framework.TestCase{

     
          /**
          * Auto generated test method
          */
          public  void testhTMLCompare() throws java.lang.Exception{

          htmlcompare.client.HTMLComparerStub stub =
          new htmlcompare.client.HTMLComparerStub();//the default implementation should point to the right endpoint
          htmlcompare.client.HTMLComparerStub.HTMLCompare hTMLCompare4=
                  (htmlcompare.client.HTMLComparerStub.HTMLCompare)getTestObject(htmlcompare.client.HTMLComparerStub.HTMLCompare.class);
                      // TODO : Fill in the hTMLCompare4 here
                  

                  //There is no output to be tested!
                  stub.hTMLCompare(
                  hTMLCompare4);

              
          }
      
          /**
          * Auto generated test method
          */
          public  void testhTMLDetailedCompare() throws java.lang.Exception{

          htmlcompare.client.HTMLComparerStub stub =
          new htmlcompare.client.HTMLComparerStub();//the default implementation should point to the right endpoint
          htmlcompare.client.HTMLComparerStub.HTMLDetailedCompare hTMLDetailedCompare5=
                  (htmlcompare.client.HTMLComparerStub.HTMLDetailedCompare)getTestObject(htmlcompare.client.HTMLComparerStub.HTMLDetailedCompare.class);
                      // TODO : Fill in the hTMLDetailedCompare5 here
                  

                  //There is no output to be tested!
                  stub.hTMLDetailedCompare(
                  hTMLDetailedCompare5);

              
          }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    