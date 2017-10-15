package com.example.anastasiaafanasyeva.floop;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.ConditionVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private static Button mButton;
    static final String ORIENTATION_PORTRAIT = "Portret mode";
    static final String ORIENTATION_LANDSCAPE = "Landscape mode";

    boolean mState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.buttonO);
        mButton.setText(ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private String getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return "Portret orientation";
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "Album orientation";
        else
            return "";
    }

    private String getRorateOrientation() {
        int rotate = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotate) {
            case Surface.ROTATION_0:
                return "No rotation";
            case Surface.ROTATION_90:
                return "Rotated 90 degrees to the left";
            case Surface.ROTATION_180:
                return "Rotated 180 degrees";
            case Surface.ROTATION_270:
                return "Rotated 90 degrees to the right";
            default:
                return "Dunno, frankly...";
        }
    }

    public void onClick(View view) {
        mInfoTextView = (TextView) findViewById(R.id.textView);
        mInfoTextView.setText(getRorateOrientation());
    }

    public void onClickO(View view) {
        // state FALSE: -> landscape
        if (mButton != null) {
            if (!mState) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mButton.setText(ORIENTATION_PORTRAIT);
            }
            //state TRUE -> portrait
            else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mButton.setText(ORIENTATION_LANDSCAPE);
            }
            mState = !mState;
        }
    }

    /* old functions

    private boolean isLandscapeMode(Activity activity)
    {
        int width =
                activity.getWindowManager().getDefaultDisplay().getWidth();
        int height =
                activity.getWindowManager().getDefaultDisplay().getHeight();

        boolean isLandscape = width > height;

        if(isLandscape)
            mOrientation = "Альбомная";
        else
            mOrientation = "Портретная";

        return isLandscape;
    }
    */
}
