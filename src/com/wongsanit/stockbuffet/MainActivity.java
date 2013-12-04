package com.wongsanit.stockbuffet;

import java.math.BigDecimal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText epsPastEdt;
	private EditText epsFutureEdt;
	private EditText epsPeriodEdt;

	private EditText epsCurrentEdt;
	private EditText currentPriceEdt;
	private EditText epsNperiodEdt;
	private EditText peMinEdt;
	private EditText peMaxEdt;

	private EditText resultEdt;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		
		epsPastEdt = (EditText) findViewById(R.id.epsPastEdt);
		epsFutureEdt = (EditText) findViewById(R.id.epsFutureEdt);
		epsPeriodEdt = (EditText) findViewById(R.id.epsPeriodEdt);

		epsCurrentEdt = (EditText) findViewById(R.id.epsCurrentEdt);
		currentPriceEdt = (EditText) findViewById(R.id.currentPriceEdt);
		epsNperiodEdt = (EditText) findViewById(R.id.epsNperiodEdt);

		peMinEdt = (EditText) findViewById(R.id.peMinEdt);
		peMaxEdt = (EditText) findViewById(R.id.peMaxEdt);
		resultEdt = (EditText) findViewById(R.id.resultEdt);

		Button startBtn = (Button) findViewById(R.id.startCalBtn);

		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("Start click");

				// start calculator process
				try{
					
				
				double epsPast = Double.parseDouble(epsPastEdt.getText()
						.toString());
				double epsFuture = Double.parseDouble(epsFutureEdt.getText()
						.toString());
				double epsPeriod = Double.parseDouble(epsPeriodEdt.getText()
						.toString());
				
				double epsCurrent = Double.parseDouble(epsCurrentEdt.getText()
						.toString());
				
				double currentPrice = Double.parseDouble(currentPriceEdt
						.getText().toString());
				
				double epsNperiod = Double.parseDouble(epsNperiodEdt.getText()
						.toString());

				double peMin = Double
						.parseDouble(peMinEdt.getText().toString());
				double peMax = Double
						.parseDouble(peMaxEdt.getText().toString());
				
				double peMid = (peMin+peMax)/2.0;
				
				double growthRate = Math.pow((epsFuture / epsPast),
						(1.0 / epsPeriod))   - 1;
				
				double epsN = epsCurrent*Math.pow( (1.0 + growthRate),epsNperiod);
				
				double priceMin = epsN*peMin;
				double priceMid = epsN*peMid;
				double priceMax = epsN*peMax;

				double profitMin = Math.pow((priceMin / currentPrice),
						(1.0 / epsNperiod))   - 1;
				
				double profitMid = Math.pow((priceMid / currentPrice),
						(1.0 / epsNperiod))   - 1;
				
				double profitMax = Math.pow((priceMax / currentPrice),
						(1.0 / epsNperiod))   - 1;
				
				//Round for beautiful display
				
				growthRate = round(growthRate, 4);
				epsN = round(epsN, 4);
				
				priceMin = round(priceMin, 4);
				priceMid = round(priceMid, 4);
				priceMax = round(priceMax, 4);
				
				profitMin = round(profitMin, 4);
				profitMid = round(profitMid, 4);
				profitMax = round(profitMax, 4);









				System.out.println("growthRate: " + growthRate);
				
				resultEdt.setText("Growth: " + growthRate+"\n"
								+"NextEPS: "+epsN+"\n"
								+"PriceMin: "+priceMin+"\n"
								+"PriceMid: "+priceMid+"\n"
								+"PriceMax: "+priceMax+"\n"
								+"profitMin: "+profitMin+"\n"
								+"profitMid: "+profitMid+"\n"
								+"profitMax: "+profitMax+"\n"



				
				);
				
				
				}
				catch(Exception e){
					Toast.makeText(mContext, "Invaid number input", Toast.LENGTH_LONG).show();
				}
				
				
			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
	
}
