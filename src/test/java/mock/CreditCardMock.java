package mock;

import org.apache.commons.lang3.StringUtils;
import com.jayway.jsonpath.JsonPath;
import spark.Spark;

                           //for Ecommerce domain 
public class CreditCardMock {
	
	public static void main(String[] args) {
		
		Spark.port(8889);     //PORT NO --> where this mocking server will run i.e Local Pc Port:8889 
		
		Spark.post("/credit-card", (req,res) ->{        //MOCKING SERVER/STUB [for Post Request and validating creditcardno from request payLoad]
		String response= "";
			   String card =JsonPath.read(req.body().toString(), "$.creditcard"); //capturing the creditcard from the request json payload 
			                                                                      // using Gson so Jsonpath start with $
			   
			   
			   if(StringUtils.equalsAny(card,"1234567891234","1234567891235")) {  //MOCK DATA FOR CREDITCARD
				    response = "{ \"status\" : \"Payment Success\" }";
				    res.status(200);    
			   }else {
				   response ="{ \"status\" : \"Payment Failed\" }";
				   res.status(404);
			   }
			   res.type("application/json");
			   return response;
		});
		
		System.out.println("======>CREDIT CARD MOCKING SERVER RUNNING<===="); //TO GET TO KNOW MOCKING SERVER IS RUNNING
	}

}

/* we have created a mocking solution only  for validation of credit card where this mocking solution is getting request
 * and from request payload the creditcard key data is capture using Gson json path start with $ and
 * this mocking solution is accepting only POST request which need two argument first is the end point and second is 
 * (req,res) req is the request obj variable which will recieve the request and res is the response object variable to response back
 */


/* convert this PaymentMockingServer to .Jar and Run it through command Prompt using 
 * command : java -jar " jar file path(drag and drop)"  and then hit the API POST request
 * 
 * The Api POST request is written in BackEndtestingProject PancardMock package and class Payment_creditCardTest
 * 
 *  **** ALWAYS REMEMBER TO CLOSE/SHUTDOWN THIS MOCKING SERVER FROM THAT OPEN COMMAND PROMPT WHERE MY JAR EXIST ***
 *   */
