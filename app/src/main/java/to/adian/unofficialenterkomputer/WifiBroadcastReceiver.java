package to.adian.unofficialenterkomputer;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

public class WifiBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = WifiBroadcastReceiver.class.getName();
    private Activity activity;

    public WifiBroadcastReceiver() {

    }

    public WifiBroadcastReceiver(Activity activity) {
        this.activity = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received WiFi scan result");
        WifiManager manager = (WifiManager) context.getSystemService(Service.WIFI_SERVICE);
        List<ScanResult> results = manager.getScanResults();

        View view = activity.findViewById(R.id.bottom_navigation);
        Snackbar.make(view, String.format(Locale.ENGLISH,
                "Received %d scan results", results.size()),
                Snackbar.LENGTH_LONG).show();
    }
}
