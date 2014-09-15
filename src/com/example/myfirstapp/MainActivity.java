package com.example.myfirstapp;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    /** The log tag. */
	private static final String LOG_TAG = "InterstitialSample";

    /** Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-mb-app-pub-6675826664759732/6959510322";

    /** The interstitial ad. */
    private InterstitialAd interstitialAd;

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		startActivity(intent);
	}
	
	public void onCheckboxLbClicked(View view) {
	}
	
	public void onCheckboxMrClicked(View view) {
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Create an ad.
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID);

        // Set the AdListener.
        interstitialAd.setAdListener(new AdListener() {
          @Override
          public void onAdLoaded() {
            Log.d(LOG_TAG, "onAdLoaded");
            Toast.makeText(MainActivity.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
            
            interstitialAd.show();
          }

          @Override
          public void onAdFailedToLoad(int errorCode) {
            String message = String.format("onAdFailedToLoad (%s)", getErrorReason(errorCode));
            Log.d(LOG_TAG, message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
          }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    private void openSearch() {
    }
    private void openSettings() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /** Called when the Load Interstitial button is clicked. */
    public void loadInterstitial(View unusedView) {
      // Check the logcat output for your hashed device ID to get test ads on a physical device.
      AdRequest adRequest = new AdRequest.Builder()
          .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
          .addTestDevice("C7780C08B0538F034D630D3AC89DF5E8")
          .build();

      // Load the interstitial ad.
      interstitialAd.loadAd(adRequest);
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
}
