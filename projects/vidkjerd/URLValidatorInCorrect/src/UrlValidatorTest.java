/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.TestCase;

/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest() {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   System.out.println("Starting Manual Tests");
	   
	   System.out.println("Testing null an and empty string URL's");
	   System.out.println(urlVal.isValid(""));
	   System.out.println(urlVal.isValid(null));
	   
	   System.out.println("Testing IPs and LocalHost");
	   System.out.println(urlVal.isValid("http://0.0.0.0"));
	   System.out.println(urlVal.isValid("http://1.1.1.1"));
	   System.out.println(urlVal.isValid("http://255.255.255.255"));
	   System.out.println(urlVal.isValid("http://255.255.255.256"));
	   System.out.println(urlVal.isValid("http://127.0.0.1"));
	   System.out.println(urlVal.isValid("http://localhost"));
	   
	   System.out.println("Testing Schemes");
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   System.out.println(urlVal.isValid("https://www.amazon.com"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com"));
	   System.out.println(urlVal.isValid("ssh://www.amazon.com"));
	   System.out.println(urlVal.isValid("dav://www.amazon.com"));
	   System.out.println(urlVal.isValid("sftp://www.amazon.com"));
	   
	   System.out.println("Testing Ports");
	   System.out.println(urlVal.isValid("http://www.amazon.com:"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:80"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:80"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:80"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:443"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:443"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:443"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:21"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:21"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:21"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:25566"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:25566"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:25566"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:a"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:#"));
	   
	   System.out.println("Testing Queries");
	   System.out.println(urlVal.isValid("http://www.amazon.com?action=text"));
	   System.out.println(urlVal.isValid("https://www.amazon.com?action=test"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com?action=test"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:80?action=test"));
	   System.out.println(urlVal.isValid("https://www.amazon.com:443?action=test"));
	   System.out.println(urlVal.isValid("ftp://www.amazon.com:21?action=test"));
	   
	   System.out.println("Testing Domain Endings");
	   System.out.println(urlVal.isValid("http://www.amazon.uk"));
	   System.out.println(urlVal.isValid("http://www.amazon.co.uk"));
	   System.out.println(urlVal.isValid("http://www.amazon.de"));
	   System.out.println(urlVal.isValid("http://www.amazon.biz"));
	   System.out.println(urlVal.isValid("http://www.amazon.co.biz.com"));
	   
	   System.out.println("Testing Misc");
	   
	   System.out.println("Testing Full URLs");
	   System.out.println(urlVal.isValid("http://www.amazon.com:80"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/homepage.html?action=test"));
	   System.out.println(urlVal.isValid("http://www.amazon.com:80/homepage.html"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/homepage.html:80"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/homepage.html:80?action=test"));
	   System.out.println(urlVal.isValid("http://www.amazon.com/homepage.html:80/test/homepage.html?action=test"));
	   
	   
	   
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   
	   
   }
   
   
   	/*******************
	 * A helper method to validate a URL. 0 is returned on success otherwise 1 is returned.
	 * If the URL is not valid, a message will be printed on the console if requested.
	 ******************/
	public int validateUrl(ResultPair url, int scheme, boolean disp_err_msg){
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		switch(scheme)
		{
			case 2:	
				urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_2_SLASHES); break;
			case 3:
				urlVal = new UrlValidator(null, null, UrlValidator.NO_FRAGMENTS); break;
			case 4:
				urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_LOCAL_URLS); break;			
		}

		if(urlVal.isValid(url.item) == url.valid)
		{
			return 0;
		}
		else
		{
			if (disp_err_msg) {
				String expected_as = url.valid ? "Valid": "Invalid";
				String evaluated_as = !url.valid ? "Valid": "Invalid";
				
				String report = "FAIL: URL='" + url.item + "' EXPECTED='" + expected_as + "'  EVALAUTED='" + evaluated_as + "'";
				
				System.out.println(report); 
			}	
			return 1;
		}  
	}
	
	/******************
	 * This method tests the Scheme part of a URL. 
	 */
	public void testScheme()
	{
		ResultPair[] schemes = {
			new ResultPair("http://", true),
			new ResultPair("https://", true),
			new ResultPair("ftp://", true),
			new ResultPair("", true),
			new ResultPair("httttp://", false),
			new ResultPair("htp://", false),
			new ResultPair("http", false),
			new ResultPair("http:://", false),
			new ResultPair("http:/", false),
			new ResultPair("http:///", false) 
		};
		
		/*
		Run each test individually and count the number of failed results.
		All the other parts of each URL are valid so that the test result 
		depends only on the validity of the Scheme part.
		*/
		
		int num_tests = schemes.length;		
		int num_failed_tests = 0;
		System.out.println("=======================================================");	 
		System.out.println("Running Scheme Partition Tests...");	   
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < num_tests; i++){
			ResultPair url_pair = new ResultPair(schemes[i].item + "www.google.com", schemes[i].valid);
			num_failed_tests = num_failed_tests + validateUrl(url_pair, 1, true);
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("Scheme Partition Test Complete.  Out of the "+ num_tests + " tests run, " + num_failed_tests + " tests failed.");
		System.out.println("=======================================================\n\n");
	} 
	
	/******************
	 * This method tests the Authority part of a URL.  
	 */
	public void testAuthority(){
		ResultPair[] authorities = {
			new ResultPair("www.google.com", true),
			new ResultPair("google.com", true),
			new ResultPair("google.net", true),
			new ResultPair(".net", false),
			new ResultPair("google.tv", true),
			new ResultPair("google.c0m", false),
			new ResultPair("125.125.125.125", true),
			new ResultPair("0.0.0.0.0", false),
			new ResultPair("255.255.255.255", true),
			new ResultPair("256.256.256.256", false),
			new ResultPair("255.255.255", false),
			new ResultPair("255", false),
			new ResultPair("", false)
		};
		
		/*
		Run each test individually and count the number of failed results.
		All the other parts of each URL are valid so that the test result 
		depends only on the validity of the Authority part.
		*/
		
		int num_tests = authorities.length;
		int num_failed_tests = 0;
		
		System.out.println("=======================================================");	 
		System.out.println("Running Authority Partition Tests...");	   
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < num_tests; i++){
			ResultPair url_pair = new ResultPair("http://" + authorities[i].item, authorities[i].valid);
			num_failed_tests = num_failed_tests + validateUrl(url_pair, 1, true);
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Authority Partition Test Complete.  Out of the "+ num_tests + " tests run, " + num_failed_tests + " tests failed.");
		System.out.println("=======================================================\n\n");
	}

	/******************
	 * This method tests the Port part of a URL.  
	 */
	public void testPort(){
		ResultPair[] ports = {
			new ResultPair(":10000", true),
			new ResultPair(":1", true),
			new ResultPair(":0", false),
			new ResultPair(":65535", true),
			new ResultPair(":65536", false),
			new ResultPair(":", false),
			new ResultPair(":port", false),
			new ResultPair(":256.256", false),
			new ResultPair(":500port", false),
			new ResultPair(":999999999999999", false),
			new ResultPair(":-100", false),
			new ResultPair(":1", true),
			new ResultPair(":11", true),
			new ResultPair(":111", true),
			new ResultPair(":1111", true),
			new ResultPair(":11111", true)
		};
		
		/*		
		Run each test individually and count the number of failed results.
		All the other parts of each URL are valid so that the test result 
		depends only on the validity of the Port part.
		*/
		
		int num_tests = ports.length;
		int num_failed_tests = 0;
		
		System.out.println("=======================================================");	 
		System.out.println("Running Port Partition Tests...");	   
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < num_tests; i++){
			ResultPair url_pair = new ResultPair("http://www.google.com" + ports[i].item, ports[i].valid);
			num_failed_tests = num_failed_tests + validateUrl(url_pair, 1, true);
		}
		
		System.out.println("-------------------------------------------------------");
		System.out.println("Port Partition Test Complete.  Out of the "+ num_tests + " tests run, " + num_failed_tests + " tests failed.");
		System.out.println("=======================================================\n\n");
	}
	
	/******************
	 * This method tests the Path part of a URL.  
	 */
	public void testPath(){
		ResultPair[] paths = {
			new ResultPair("/dir", true),
			new ResultPair("/dir/dir", true),
			new ResultPair("/dir/index.html", true),
			new ResultPair("/index.html", true),
			new ResultPair("//", false),
			new ResultPair("/", true),
			new ResultPair("/dir/index.html/dir", false),
			new ResultPair("", true),
			new ResultPair("/12345", true),
			new ResultPair("/~!_", true)
		};

		/*		
		Run each test individually and count the number of failed results.
		All the other parts of each URL are valid so that the test result 
		depends only on the validity of the Path part.
		*/
		
		int num_tests = paths.length;
		int num_failed_tests = 0;
		
		System.out.println("=======================================================");	 
		System.out.println("Running Path Partition Tests...");	   
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < num_tests; i++){
			//Generate new URL with selected Port, and test.
			ResultPair url_pair = new ResultPair("http://www.google.com" + paths[i].item, paths[i].valid);
			num_failed_tests = num_failed_tests + validateUrl(url_pair, 1, true);
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("Path Partition Test Complete.  Out of the "+ num_tests + " tests run, " + num_failed_tests + " tests failed.");
		System.out.println("=======================================================\n\n");
	}


	/******************
	 * This method tests the Query part of a URL.	 
	 */

	public void testQuery(){
		ResultPair[] Queries = {
			new ResultPair("?param=valid", true),
			new ResultPair("?param=valid&param2=valid", true),
			new ResultPair("?param=valid&param=valid&param=valid&param=valid&param=valid&param=valid&param=valid&param=valid&param=valid&param=valid", true),
			new ResultPair("?", true),
			new ResultPair("?param", true),
			new ResultPair("??", false),
			new ResultPair("?param=valid&&", false),
			new ResultPair("?param=valid?param=valid", false),
			new ResultPair("", true)
		};
	
		/*		
		Run each test individually and count the number of failed results.
		All the other parts of each URL are valid so that the test result 
		depends only on the validity of the Query part.
		*/
		
		int num_tests = Queries.length;		
		int num_failed_tests = 0;
		
		System.out.println("=======================================================");	 
		System.out.println("Running Query Partition Tests...");	   
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < num_tests; i++){
			//Generate new URL with selected Query, and test.
			ResultPair url_pair = new ResultPair("http://www.google.com" + Queries[i].item, Queries[i].valid);
			num_failed_tests = num_failed_tests + validateUrl(url_pair, 1, true);
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("Query Partition Test Complete.  Out of the "+ num_tests + " tests run, " + num_failed_tests + " tests failed.");
		System.out.println("=======================================================\n\n");
	}

   /*
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   *
   
   public void testIsValid(Object[] testObjects, long options) {
   
   UrlValidator urlVal = new UrlValidator(null, null, options);
   String basic_scheme = "http://";
   String[] basic_urls = {"www.google.com","localhost","3.141.52.96","[FEDC:BA98:7654:3210:FEDC:BA98:7654:3210]","[1080:0:0:0:8:800:200C:4171]", "[3ffe:2a00:100:7031::1]","[1080::8:800:200C:417A]","[::192.9.5.5]","[::FFFF:129.144.52.38]","[2010:836B:4179::836B:4179]"};
   //IPv6 addressing in accordance with http://www.ietf.org/rfc/rfc2732.txt
   
   String basic_user = "user";
   String basic_password = "password";
   String basic_uri = "search";
   String basic_port = "8080";
   String basic_anchor = "#anchor";
   String loc_url = "";
   String basic_url = "";
   String loc_txt = "";
   String[] valid_delimeters = {":","/","@","#"};
   String[] invalid_host_signs = {"\"","'","`","\\",",","_","%","?","!","=","[","]","{","}","&"," "};
   String[] mainvalidschemes = {"bittorrent://","callto://","callto:","feed://","file://","ftp://","gopher://","http://","https://","h3t://","LDAP://","magnet://","mailto://","mailto:","MMS://","mql5buy://","res://","skype://","tel://","viber://"};
   
	   for(int i = 0;i<mainvalidschemes.length;i++)
	   {
	   for(int j= 0;j<basic_urls.length;j++)
	   {
		 basic_url=basic_urls[j];
		 
		 loc_url=mainvalidschemes[i].basic_url;
		 System.out.println("Testing correct protocol handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol handling with correct anchoring: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol handling with incorrect anchoring: ".loc_url.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url.basic_anchor), false);
		 
		 System.out.println("Testing correct protocol and URI handling: ".loc_url."/".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri), true);
		 
		 System.out.println("Testing correct protocol and URI with anchor handling: ".loc_url."/".basic_uri.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, URI with incorrect anchor handling: ".loc_url."/".basic_uri."#".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."#".basic_anchor), false); //multiple sequential "#" are not allowed
		 
		 System.out.println("Testing correct protocol and incorrect URI handling: ".loc_url."/ ".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/ ".basic_uri), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol and port handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol, port and anchor handling: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, port and incorrect anchor handling: ".loc_url."/".basic_anchor.basic_anchor); 
		 //sequential anchors not allowed!
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor.basic_anchor), false);
		 
		 System.out.println("Testing correct protocol, port and URI handling: ".loc_url."//".basic_uri); //multiple sequential "/" in URI are allowed!!!
         assertEquals("Failure ", urlVal.isValid(loc_url."//".basic_uri), true);
		 
		 System.out.println("Testing correct protocol, port, URI and anchor handling: ".loc_url."/".basic_uri.basic_anchor); 
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, port, URI and incorrect anchor handling: ".loc_url."/".basic_uri."?".basic_anchor); 
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."?".basic_anchor), false); 
		 // "?" request should be followed by variables with values set, not the constant definition like structure.
		 
		 System.out.println("Testing correct protocol, port and incorrect URI handling: ".loc_url."#".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."#".basic_uri), false);
		 
		 loc_url=mainvalidschemes[i];
		 loc_url[2]="0";
		 loc_url.=basic_port;
		 System.out.println("Testing incorrect protocol handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing incorrect protocol and correct port handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url=mainvalidschemes[i].replace("/","");
		 loc_txt=loc_url.replace(":","");
		 loc_url=mainvalidschemes[i].loc_txt."@".basic_url;
		 System.out.println("Testing correct protocol and correct login handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol, login and anchor handling: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, login and incorrect anchor handling: ".loc_url.":".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url.":".basic_anchor), false);
		 
		 System.out.println("Testing correct protocol, login and URI handling: ".loc_url."///".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."///".basic_uri), true);
		 
		 System.out.println("Testing correct protocol, login, URI and anchor handling: ".loc_url."/".basic_uri.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, login, URI and incorrect anchor handling: ".loc_url."/".basic_uri.basic_anchor."?".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor."?".basic_uri), false);
		 //no other request processing after anchor!
		 
		 System.out.println("Testing correct protocol, login and incorrect URI handling: ".loc_url."/ /".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/ /".basic_uri), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol, correct port and correct login  handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol, port login and anchor  handling: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, port login and incorrect anchor  handling: ".loc_url."/#:");
         assertEquals("Failure ", urlVal.isValid(loc_url."/#:"), false);
		 
		 System.out.println("Testing correct protocol, correct port,login and URI  handling: ".loc_url."/".basic_uri."//".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."//".basic_uri), true);
		 
		 System.out.println("Testing correct protocol, correct port,login, URI and anchor  handling: ".loc_url."/".basic_uri."/".basic_uri.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, correct port,login, URI and incorrect anchor  handling: ".loc_url."/".basic_uri."/:".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."/:".basic_anchor), false);
		 
		 loc_url=mainvalidschemes[i].loc_txt.":".basic_url;
		 System.out.println("Testing correct protocol and incorrect login handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 System.out.println("Testing correct protocol,URI and incorrect login handling: ".loc_url."/".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol, port and incorrect login handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 System.out.println("Testing correct protocol, port, URI and incorrect login handling: ".loc_url."/".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri), false);
		
		 loc_url=mainvalidschemes[i].loc_txt.":".loc_txt."@".basic_url;
		 System.out.println("Testing correct protocol and correct login and password handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol and correct login and password with anchor handling: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol and correct login and password with incorrect anchor handling: ".loc_url."/.".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/.".basic_anchor), false);
		 
		 System.out.println("Testing correct protocol, login and password, URI handling: ".loc_url."/".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri), true);
		 
		 System.out.println("Testing correct protocol, login and password, URI, anchor handling: ".loc_url."/".basic_uri.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, login and password, URI, incorrect anchor handling: ".loc_url."/".basic_uri."?a=".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri."?a=".basic_anchor), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol, port, login and password handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), true);
		 
		 System.out.println("Testing correct protocol, port, login and password with anchor handling: ".loc_url."/".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, port, login and password with incorrect anchor handling: ".loc_url.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url.basic_anchor), false);
		 
		 System.out.println("Testing correct protocol, port, URI, login and password handling: ".loc_url."/".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri), true);
		 
		 System.out.println("Testing correct protocol, port, URI, login and password with anchor handling: ".loc_url."/".basic_uri.basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor), true);
		 
		 System.out.println("Testing correct protocol, port, URI, login and password with incorrect anchor handling: ".loc_url."/".basic_uri.basic_anchor."/a");
         assertEquals("Failure ", urlVal.isValid(loc_url."/".basic_uri.basic_anchor,"/a"), false);
		 
		 System.out.println("Testing correct protocol, port, login, password and incorrect URI handling: ".loc_url.":".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url.":".basic_uri), false);
		 
		 loc_url=mainvalidschemes[i].loc_txt."@".loc_txt."@".basic_url;
		 System.out.println("Testing correct protocol and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol, port and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url=mainvalidschemes[i].loc_txt."@".loc_txt.":".basic_url;
		 System.out.println("Testing correct protocol and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol,port and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 loc_url=mainvalidschemes[i].loc_txt.":".loc_txt.":".basic_url;
		 System.out.println("Testing correct protocol and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 System.out.println("Testing correct protocol and incorrect URI, login&password pair handling: ".loc_url.":".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url.":".basic_uri), false);
		 
		 loc_url.=basic_port;
		 System.out.println("Testing correct protocol,port and incorrect login&password pair handling: ".loc_url);
         assertEquals("Failure ", urlVal.isValid(loc_url), false);
		 
		 System.out.println("Testing correct protocol,port and incorrect URI, login&password pair handling (IPv6 like appearance): ".loc_url.":".basic_uri);
         assertEquals("Failure ", urlVal.isValid(loc_url.":".basic_uri), false);
		 
		 System.out.println("Testing correct protocol,port and incorrect URI&anchor, login&password pair handling (IPv6 like appearance): ".loc_url.":".basic_uri.":".basic_anchor);
         assertEquals("Failure ", urlVal.isValid(loc_url.":".basic_uri.":".basic_anchor), false);
		 
		}//j loop
	   }//i loop 
   } //testIsValid method
   
   /*public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   */
   
   public void testAnyOtherUnitTest() {
	   
   }
   
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */

	public static void main(String[] argv) 
	{

      UrlValidatorTest fct = new UrlValidatorTest("url test");
      System.out.println("Running Manual Tests");
      fct.testManualTest();
      
      System.out.println("Running Partition Tests");
      fct.testScheme();
      fct.testQuery();
      fct.testPort();
      fct.testPath();
      fct.testAuthority();
      
      System.out.println("Running Unit Tests");
      //fct.testIsValid();
      
      //fct.testIsValidScheme();
      
	}

}
