package com.apigee.appservices.android_template;

import java.util.HashMap;
import java.util.Map;

//import org.springframework.http.HttpMethod;

import org.usergrid.android.client.Client;
import org.usergrid.android.client.callbacks.ApiResponseCallback;
//import org.usergrid.android.client.callbacks.ClientCallback;
import org.usergrid.android.client.response.ApiResponse;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	final TextView text = (TextView) findViewById(R.id.mainActivityText);
    	
	/* 
	 	1. Set your account details

        - Enter your ORGNAME below — it’s the username you picked when you signed up at apigee.com
        - Keep the APPNAME as “sandbox”: it’s a context we automatically created for you.
          It’s completely open by default, but don’t worry, other apps you create are not!      */
    	
    	String ORGNAME = "YOUR APIGEE.COM USERNAME";
    	String APPNAME = "sandbox";
    	
    	Client client = new Client(ORGNAME+"/"+APPNAME).withApiUrl("https://api.usergrid.com");
   
    	
	/*
        2. Let’s save an object!

        Great, we know where your account is now!
        Let’s try to create a book in the system and output it on the page.
        
        - Keep the type as “book”
        - Enter some other attributes below.    */
    	
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("type", "book");
    	data.put("title", "the old man and the sea");
    	
    	client.createEntityAsync(data, new ApiResponseCallback() {
            @Override
            public void onException(Exception e) { // Error - the book was not saved properly
            	text.setText("Could not create the book.\n\nDid you enter your username correctly on line 34 of src/com/apigee/appservices/android_template/MainActivity.java ?");
            }

            @Override
            public void onResponse(ApiResponse response) { // Success - the book was created properly
            	try { 
	                if (response != null) { 
	                	// The saved object is returned in the “response” variable
	                	// defined on line 60. The code below outputs it on the page!
	                	String successMessage =	"Success! Here is the object we stored; "
	                						+   "notice the timestamps and unique id we created for you:\n\n"
	                						+ 	response.getEntities().get(0).toString();
	                	text.setText(successMessage);
	                }
            	} catch (Exception e) {
                	text.setText("Could not create the book.\n\nDid you enter your username correctly on line 34 of src/com/apigee/appservices/android_template/MainActivity.java ?");            		
            	}
            }
        });
    	
    	
    /*
        3. Congrats, you’re done!

        - You can try adding more properties after line 44 and reloading the app!
        - You can then see the admin view of this data by logging in at https://apigee.com/usergrid
        - Or you can go explore more advanced examples in our docs: http://apigee.com/docs/usergrid         */
    }
    
}


