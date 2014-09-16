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
import android.widget.CheckBox;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.provider.Settings.Secure;


public class MainActivity extends ActionBarActivity {
    
    /** The log tag. */
    private static final String LOG_TAG = "InterstitialSample";

    /** Ad unit id. */
    public static final String AD_UNIT_ID = "ca-mb-app-pub-6675826664759732/6959510322";

    /** The interstitial ad. */
    private InterstitialAd interstitialAd;
    
    private CheckBox chkBanner, chkLargeBanner, chkMediumRectangle, chkFullBanner, chkLeaderboard, chkSmartBanner;
    
    public final static String EXTRA_MESSAGE_BANNER = "com.example.myfirstapp.MESSAGE_BANNER";
    public final static String EXTRA_MESSAGE_LARGE_BANNER = "com.example.myfirstapp.MESSAGE_LARGE_BANNER";
    public final static String EXTRA_MESSAGE_MEDIUM_RECTANGLE = "com.example.myfirstapp.MESSAGE_MEDIUM_RECTANGLE";
    public final static String EXTRA_MESSAGE_FULL_BANNER = "com.example.myfirstapp.MESSAGE_FULL_BANNER";
    public final static String EXTRA_MESSAGE_LEADERBOARD = "com.example.myfirstapp.MESSAGE_LEADERBOARD";
    public final static String EXTRA_MESSAGE_SMART_BANNER = "com.example.myfirstapp.MESSAGE_SMART_BANNER";
    public final static String EXTRA_MESSAGE_DEVICE_ID = "com.example.myfirstapp.MESSAGE_DEVICE_ID";

    public String android_id = "";
    public String deviceId = "";

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        chkBanner = (CheckBox) findViewById(R.id.checkbox_banner);
        chkLargeBanner = (CheckBox) findViewById(R.id.checkbox_large_banner);
        chkMediumRectangle = (CheckBox) findViewById(R.id.checkbox_medium_rectangle);
        chkFullBanner = (CheckBox) findViewById(R.id.checkbox_full_banner);
        chkLeaderboard = (CheckBox) findViewById(R.id.checkbox_leaderboard);
        chkSmartBanner = (CheckBox) findViewById(R.id.checkbox_smart_banner);
        
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE_BANNER, chkBanner.isChecked());
        intent.putExtra(EXTRA_MESSAGE_LARGE_BANNER, chkLargeBanner.isChecked());
        intent.putExtra(EXTRA_MESSAGE_MEDIUM_RECTANGLE, chkMediumRectangle.isChecked());
        intent.putExtra(EXTRA_MESSAGE_FULL_BANNER, chkFullBanner.isChecked());
        intent.putExtra(EXTRA_MESSAGE_LEADERBOARD, chkLeaderboard.isChecked());
        intent.putExtra(EXTRA_MESSAGE_SMART_BANNER, chkSmartBanner.isChecked());
        intent.putExtra(EXTRA_MESSAGE_DEVICE_ID, deviceId);
        startActivity(intent);
    }
    
    public void onCheckboxBannerClicked(View view) {
    }
    
    public void onCheckboxMediumRectangleClicked(View view) {
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        deviceId = md5(android_id).toUpperCase();

        
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
          .addTestDevice(deviceId)
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
    
    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
}
