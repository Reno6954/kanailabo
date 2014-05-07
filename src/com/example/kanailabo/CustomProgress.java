package com.example.kanailabo;

import android.app.Dialog;
import android.content.Context;

public class CustomProgress extends Dialog {

	public CustomProgress(Context context) {
		super(context, R.style.Theme_CustomProgress);
		// TODO 自動生成されたコンストラクター・スタブ
		// レイアウトを決定  
	    setContentView(R.layout.custom_progress);
	}
}
