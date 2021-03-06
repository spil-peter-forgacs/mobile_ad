package com.example.myfirstapp;

import java.io.IOException;
import java.net.URL;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayMessageActivity extends ActionBarActivity {

    private AdView adView;

    private static final String LOG_TAG = "BannerAdListener";
    
    private TextView sampleTextView;
    private Spanned spannedValue;
    private String stringWithHtml;
    
    public String deviceId = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        
        // It needed for loading images in HTML from net.
        // Later development:
        // http://stackoverflow.com/questions/7424512/android-html-imagegetter-as-asynctask
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        // Get the message from the intent
        Bundle bundle = getIntent().getExtras();
        Boolean message_banner = true;
        Boolean message_large_banner = true;
        Boolean message_medium_rectangle = true;
        Boolean message_full_banner = true;
        Boolean message_leaderboard = true;
        Boolean message_smart_banner = true;
        if (bundle!=null){
            message_banner = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_BANNER);
            message_large_banner = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_LARGE_BANNER);
            message_medium_rectangle = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_MEDIUM_RECTANGLE);
            message_full_banner = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_FULL_BANNER);
            message_leaderboard = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_LEADERBOARD);
            message_smart_banner = bundle.getBoolean(MainActivity.EXTRA_MESSAGE_SMART_BANNER);
            deviceId = bundle.getString(MainActivity.EXTRA_MESSAGE_DEVICE_ID);
        }

        // Long HTML
        stringWithHtml = getString(R.string.htmlFormattedText1);
        spannedValue = Html.fromHtml(stringWithHtml,getImageHTML(),null);
        sampleTextView = (TextView)findViewById(R.id.sampleText1);
        sampleTextView.setText(spannedValue);

        // Long HTML
        stringWithHtml = getString(R.string.htmlFormattedText2);
        spannedValue = Html.fromHtml(stringWithHtml,getImageHTML(),null);
        sampleTextView = (TextView)findViewById(R.id.sampleText2);
        sampleTextView.setText(spannedValue);

        if (message_banner) {
            // Create an ad.
            createAd(AdSize.BANNER, R.id.linearLayoutBanner);
        }
        if (message_large_banner) {
            // Create an ad.
            createAd(AdSize.LARGE_BANNER, R.id.linearLayoutLargeBanner);
        }        
        if (message_medium_rectangle) {
            // Create an ad.
            createAd(AdSize.MEDIUM_RECTANGLE, R.id.linearLayoutMediumRectangle);
        }
        if (message_full_banner) {
            // Create an ad.
            createAd(AdSize.FULL_BANNER, R.id.linearLayoutFullBanner);
        }
        if (message_leaderboard) {
            // Create an ad.
            createAd(AdSize.LEADERBOARD, R.id.linearLayoutLeaderboard);
        }
        if (message_smart_banner) {
            // Create an ad.
            createAd(AdSize.SMART_BANNER, R.id.linearLayoutSmartBanner);
        }

    }
    
    private void createAd(AdSize adSize, int layoutId) {
        adView = new AdView(this);
        adView.setAdSize(adSize);
        adView.setAdUnitId(MainActivity.AD_UNIT_ID);

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
        LinearLayout layoutRectangle = (LinearLayout) findViewById(layoutId);
        layoutRectangle.addView(adView);
        
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .addTestDevice(deviceId)
            .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
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
                try {
                    Drawable drawable = Drawable.createFromStream(new URL(source).openStream(), "src name");
                    drawable.setBounds(0, 0, 6 * drawable.getIntrinsicWidth(), 6 * drawable.getIntrinsicHeight());
                    return drawable;
                } catch(IOException exception) {
                    Log.v("IOException",exception.getMessage());
                    return null;
                }
            }
        };
        return imageGetter;
    }
}
