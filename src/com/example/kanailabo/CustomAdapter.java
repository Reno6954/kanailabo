package com.example.kanailabo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<CustomData>{
	private static final String CORR_URL = "http://kanai-lab.herokuapp.com/corr.php";
	private static final String ID = "id";
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private LayoutInflater layoutInflater;

	private TextView gradeText;
	private TextView nameText;
	private RadioGroup radioGroup;
	private LinearLayout linearLayout;

	public CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects) {
		super(context, textViewResourceId, objects);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 特定の行(position)のデータを得る
		final CustomData item = (CustomData)getItem(position);
		final String id = Integer.toString(position);
		
		// convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.customdata, null);
		}
		setLayout(position,convertView);
		// CustomDataのデータをViewの各Widgetにセットする

		gradeText.setText(item.getGrade());
		nameText.setText(item.getName());

		// ラジオグループのチェック状態が変更された時に呼び出されるコールバックリスナーを登録します
		/*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				// ラジオグループのチェック状態が変更された時に呼び出されます
				// チェック状態が変更されたラジオボタンのIDが渡されます
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String Status = "";
				item.setStatus(checkedId);
				switch (checkedId) {
				case CustomData.LABO:
					Status = ZERO;
					break;
				case CustomData.CAMPUS:
					Status = ONE;
					break;
				case CustomData.HOME:
					Status = TWO;
					break;
				}
				ButtonPost CORR = new ButtonPost(CORR_URL);
				CORR.execute(ID,id,id,Status);
			}
		});
		radioGroup.check(item.getStatus());*/
		return convertView;
	}
	/**
	 * set find view by id
	 */
	private void setLayout(int position,View convertView){
		
		gradeText = (TextView)convertView.findViewById(R.id.grade);
		nameText = (TextView)convertView.findViewById(R.id.name);
		//radioGroup = (RadioGroup)convertView.findViewById(R.id.status);
	}
}
