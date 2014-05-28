package com.example.kanailabo;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener{
	private static final String MEMBERS_URL = "http://kanai-lab.herokuapp.com/xml/members.xml";
	private static final String ENCODING = "UTF-8";
	private static final String MEMBER = "member";
	private static final String GRADE = "grade";
	private static final String NAME = "name";
	private static final String STATUS = "status";
	private GridView gridView;
	private CustomProgress progressDialog;
	static final int res=0;
	
	private int color1 = 0;
	private int color2 = 0;
	private int color3 = 0;

	//リストビュー表示のためのデータのリスト0
	private ArrayList<CustomData> customDatas = new ArrayList<CustomData>();
	private CustomAdapter customAdapater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ダイアログ
		progressDialog = new CustomProgress(this);
		
		color1 = getResources().getColor(R.color.color1);
		color2 = getResources().getColor(R.color.color2);
		color3 = getResources().getColor(R.color.color3);
		//リストビューに表示するためのビューをリストから作成するアダプター
        customAdapater = new CustomAdapter(this, 0, customDatas);
        gridView = (GridView)findViewById(R.id.gridView1);
        gridView.setAdapter(customAdapater);
        //非同期でxmlを取得してパースする
        new RosterAcquisition().startTask();
        gridView.setOnItemClickListener(this);
	}
	
	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}
	
	//非同期処理
	public class RosterAcquisition extends AsyncTask<String, Integer, String> {
		public void startTask(){
			progressDialog.show();
			execute();
		}
		
	    @Override
	    protected String doInBackground(String... contents) {
	    	try{
	    		//Thread.sleep(3000);
	    		//xmlをパースしてくれるクラスのインスタンスを取得
	            XmlPullParser xmlPullParser = Xml.newPullParser();
	         
	            URL url = new URL(MEMBERS_URL);
	            URLConnection connection = url.openConnection();
	            //URLからxmlを取得してPullParserにセット
	            xmlPullParser.setInput(connection.getInputStream(), ENCODING);
	            //取得したxmlの最初のイベントタイプを取得
	            int eventType = xmlPullParser.getEventType();
	            
	            CustomData customData = null;
	            
	            while(eventType!=XmlPullParser.END_DOCUMENT){  
	                
	                String tag = null;      //タグ名取得用  
	                switch(eventType){  
	                    //ドキュメントの最初  
	                    case XmlPullParser.START_DOCUMENT:  
	                        //ドキュメントのが始まったら準備  
	                        //今回は何もしていない。  
	                        break;  
	                      
	                    //開始タグ時  
	                    case XmlPullParser.START_TAG:  
	                        tag = xmlPullParser.getName();  
	                        //memberタグ開始時  
	                        //必要なのは、memberタグの中身の子タグのみなので  
	                        //memberのスタートタグでcustomDataクラスインスタンス作成し、  
	                        //作成していなかったら、member内の情報ではない。  
	                        if(tag.equals(MEMBER)){
	                            customData = new CustomData();                      
	                        }else if (customData!=null){  
	                            //memberタグ内の子タグごとの処理  
	                            //タグ名称と取得したいタグ名を比較して  
	                            //同じであったら、nextText()により内容取得。  
	                            if (tag.equals(GRADE)){
	                                customData.setGrade(xmlPullParser.nextText());  
	                            }else if(tag.equals(NAME)){
	                                customData.setName(xmlPullParser.nextText());  
	                            }else if(tag.equals(STATUS)){  
	                            	//Log.e("-----","Status"+xmlPullParser.nextText());
	                                switch (Integer.valueOf(xmlPullParser.nextText())) {
									case 0:
										customData.setStatus(color1);
										break;
									case 1:
										customData.setStatus(color2);
										break;
									case 2:
										customData.setStatus(color3);
										break;
									}
	                            }  
	                        }  
	                        break;  
	                          
	                    //終了タグ時  
	                    case XmlPullParser.END_TAG:  
	                        //memberタグが終わったら、そこで１記事のセットが終了したとして  
	                        //customDatasに追加。  
	                        tag=xmlPullParser.getName();  
	                        if(tag.equals(MEMBER)){  
	                            //Itemタグ終了時に格納。  
	                            customDatas.add(customData);  
	                            customData = null;  
	                        }  
	                        break;  
	                }  
	                //次のイベントへ遷移させループ  
	                eventType = xmlPullParser.next();  
	            }
	        } 
	    	//例外処理
	    	catch (Exception e){
	        	e.printStackTrace();
	        }
	        return null;
	    }
	    
	    
	    //非同期処理が終わったらメインスレッドでビューの更新
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			customAdapater.notifyDataSetChanged();
			progressDialog.dismiss();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自動生成されたメソッド・スタブ
		// クリックされたアイテムを取得する。
				CustomData item = (CustomData) parent.getItemAtPosition(position);
				Intent intent = new Intent(this,PrivateStatus.class);
				intent.putExtra("name", item.getName());
				intent.putExtra("status", item.getStatus());
				intent.putExtra("position", position);
				Log.e("position",String.valueOf(position));
				startActivityForResult(intent,position);
	}
	
	//円形加工
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("main activity", "activity result"+data);
		Bundle status = data.getExtras();
		int bg = status.getInt("status");
		if (bg == 0)
			customDatas.get(requestCode).setStatus(color1);
		else if (bg == 1)
			customDatas.get(requestCode).setStatus(color2);	
		else if (bg == 2)
			customDatas.get(requestCode).setStatus(color3);
		Log.e("requestCode",String.valueOf(requestCode));
		Log.e("resultCode",String.valueOf(resultCode));
		customAdapater.notifyDataSetChanged();
	}
	
}
