package top.trumeet.mipushframework.settings;

import android.os.Bundle;
import android.util.Log;

import com.xiaomi.xmsf.R;

import moe.shizuku.preference.Preference;
import moe.shizuku.preference.PreferenceFragment;
import top.trumeet.mipushframework.push.PushController;
import top.trumeet.mipushframework.push.PushServiceAccessibility;

import static top.trumeet.mipushframework.Constants.TAG;

/**
 * Created by Trumeet on 2017/8/27.
 * Main settings
 * @see MainActivity
 * @author Trumeet
 */

public class SettingsFragment extends PreferenceFragment {
    private Preference mDozePreference;
    private Preference mCheckServicePreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
        mDozePreference = getPreferenceScreen()
                .findPreference("key_remove_doze");
        mCheckServicePreference = getPreferenceScreen()
                .findPreference("key_check_service");
    }

    @Override
    public void onStart () {
        super.onStart();
        long time = System.currentTimeMillis();
        mDozePreference.setVisible(!PushServiceAccessibility.isInDozeWhiteList(getActivity()));
        mCheckServicePreference.setVisible(!(PushController.isPrefsEnable(getActivity()) &&
                PushController.isServiceRunning(getActivity())));
        Log.d(TAG, "rebuild UI took: " + String.valueOf(System.currentTimeMillis() -
                time));
    }
}
