package com.example.myfirstapp;

import java.io.IOException;
import java.net.URL;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
//import com.google.example.gms.ads.banneradlistener.BannerAdListener;
//import com.google.example.gms.ads.banneradlistener.R;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends ActionBarActivity {

	private AdView adView;
	private static final String AD_UNIT_ID = "ca-mb-app-pub-6675826664759732/6959510322";	  

	/** The log tag. */
	private static final String LOG_TAG = "BannerAdListener";
    
	private TextView sampleTextView;
	private Spanned spannedValue;
	private String stringWithHtml;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		stringWithHtml = getString(R.string.htmlFormattedText1);
		spannedValue = Html.fromHtml(stringWithHtml,getImageHTML(),null);
		sampleTextView = (TextView)findViewById(R.id.sampleText1);
		sampleTextView.setText(spannedValue);
		
		stringWithHtml = getString(R.string.htmlFormattedText2);
		spannedValue = Html.fromHtml(stringWithHtml,getImageHTML(),null);
		sampleTextView = (TextView)findViewById(R.id.sampleText2);
		sampleTextView.setText(spannedValue);

		
		// Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Set the AdListener.
	    adView.setAdListener(new AdListener() {
	      /** Called when an ad is clicked and about to return to the application. */
	      @Override
	      public void onAdClosed() {
	        Log.d(LOG_TAG, "onAdClosed");
	        Toast.makeText(DisplayMessageActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
	      }

	      /** Called when an ad failed to load. */
	      @Override
	      public void onAdFailedToLoad(int error) {
	        String message = "onAdFailedToLoad: " + getErrorReason(error);
	        Log.d(LOG_TAG, message);
	        Toast.makeText(DisplayMessageActivity.this, message, Toast.LENGTH_SHORT).show();
	      }

	      /**
	       * Called when an ad is clicked and going to start a new Activity that will
	       * leave the application (e.g. breaking out to the Browser or Maps
	       * application).
	       */
	      @Override
	      public void onAdLeftApplication() {
	        Log.d(LOG_TAG, "onAdLeftApplication");
	        Toast.makeText(DisplayMessageActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
	      }

	      /**
	       * Called when an Activity is created in front of the app (e.g. an
	       * interstitial is shown, or an ad is clicked and launches a new Activity).
	       */
	      @Override
	      public void onAdOpened() {
	        Log.d(LOG_TAG, "onAdOpened");
	        Toast.makeText(DisplayMessageActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
	      }

	      /** Called when an ad is loaded. */
	      @Override
	      public void onAdLoaded() {
	        Log.d(LOG_TAG, "onAdLoaded");
	        Toast.makeText(DisplayMessageActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
	      }
	    });    
	    // Add the AdView to the view hierarchy.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
	    layout.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .addTestDevice("C7780C08B0538F034D630D3AC89DF5E8")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
	    
	    
	    
	    
		
		// Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Set the AdListener.
	    adView.setAdListener(new AdListener() {
	      /** Called when an ad is clicked and about to return to the application. */
	      @Override
	      public void onAdClosed() {
	        Log.d(LOG_TAG, "onAdClosed");
	        Toast.makeText(DisplayMessageActivity.this, "onAdClosed", Toast.LENGTH_SHORT).show();
	      }

	      /** Called when an ad failed to load. */
	      @Override
	      public void onAdFailedToLoad(int error) {
	        String message = "onAdFailedToLoad: " + getErrorReason(error);
	        Log.d(LOG_TAG, message);
	        Toast.makeText(DisplayMessageActivity.this, message, Toast.LENGTH_SHORT).show();
	      }

	      /**
	       * Called when an ad is clicked and going to start a new Activity that will
	       * leave the application (e.g. breaking out to the Browser or Maps
	       * application).
	       */
	      @Override
	      public void onAdLeftApplication() {
	        Log.d(LOG_TAG, "onAdLeftApplication");
	        Toast.makeText(DisplayMessageActivity.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
	      }

	      /**
	       * Called when an Activity is created in front of the app (e.g. an
	       * interstitial is shown, or an ad is clicked and launches a new Activity).
	       */
	      @Override
	      public void onAdOpened() {
	        Log.d(LOG_TAG, "onAdOpened");
	        Toast.makeText(DisplayMessageActivity.this, "onAdOpened", Toast.LENGTH_SHORT).show();
	      }

	      /** Called when an ad is loaded. */
	      @Override
	      public void onAdLoaded() {
	        Log.d(LOG_TAG, "onAdLoaded");
	        Toast.makeText(DisplayMessageActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
	      }
	    });    
	    // Add the AdView to the view hierarchy.
	    LinearLayout layoutRectangle = (LinearLayout) findViewById(R.id.linearLayoutRectangle);
	    layoutRectangle.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequestRectangle = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .addTestDevice("C7780C08B0538F034D630D3AC89DF5E8")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequestRectangle);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	  
    /** Gets a string error reason from an error code. */
    private String getErrorReason(int errorCode) {
      String errorReason = "";
      switch(errorCode) {
        case AdRequest.ERROR_CODE_INTERNAL_ERROR:
          errorReason = "Internal error";
          break;
        case AdRequest.ERROR_CODE_INVALID_REQUEST:
          errorReason = "Invalid request";
          break;
        case AdRequest.ERROR_CODE_NETWORK_ERROR:
          errorReason = "Network Error";
          break;
        case AdRequest.ERROR_CODE_NO_FILL:
          errorReason = "No fill";
          break;
      }
      return errorReason;
    }

    public ImageGetter getImageHTML() {
		ImageGetter imageGetter = new ImageGetter() {
			public Drawable getDrawable(String source) {
				
		        int id;
		        //if (ource.equals("hughjackman.jpg")) {
		               id = R.drawable.ic_launcher;
		        //}
		        //else {
		        //    return null;
		        //}

		       Drawable d = getResources().getDrawable(id);
		       d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
		       return d;

				/*
				try {
					Drawable drawable = Drawable.createFromStream(new URL(source).openStream(), "src name");
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
					return drawable;
				} catch(IOException exception) {
					Log.v("IOException",exception.getMessage());
					return null;
				}
		       */
			}
		};
		return imageGetter;
	}
}
