package com.example.kanailabo;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class ButtonPost extends AsyncTask<String, Integer, Integer>{
	private String URL;
	public ButtonPost(String URL) {
		super();
		this.URL = URL;
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO 自動生成されたメソッド・スタブ
		HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
		
        ArrayList <NameValuePair> NameValList = new ArrayList <NameValuePair>();
        
        for(int i = 0;i < params.length;i+=2){
        	NameValList.add( new BasicNameValuePair(params[i],params[i+1]));
        }
		
        HttpResponse res = null;
        
        try {
            post.setEntity(new UrlEncodedFormEntity(NameValList, "utf-8"));
            res = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return Integer.valueOf(res.getStatusLine().getStatusCode());
	}

}
