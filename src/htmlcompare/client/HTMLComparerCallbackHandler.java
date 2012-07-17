
/**
 * HTMLComparerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package htmlcompare.client;

    /**
     *  HTMLComparerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class HTMLComparerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public HTMLComparerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public HTMLComparerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for hTMLCompare method
            * override this method for handling normal response from hTMLCompare operation
            */
           public void receiveResulthTMLCompare(
                    htmlcompare.client.HTMLComparerStub.HTMLCompareResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from hTMLCompare operation
           */
            public void receiveErrorhTMLCompare(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for hTMLDetailedCompare method
            * override this method for handling normal response from hTMLDetailedCompare operation
            */
           public void receiveResulthTMLDetailedCompare(
                    htmlcompare.client.HTMLComparerStub.HTMLDetailedCompareResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from hTMLDetailedCompare operation
           */
            public void receiveErrorhTMLDetailedCompare(java.lang.Exception e) {
            }
                


    }
    