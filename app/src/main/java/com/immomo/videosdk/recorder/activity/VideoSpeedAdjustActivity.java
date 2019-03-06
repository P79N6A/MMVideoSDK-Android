package com.immomo.videosdk.recorder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.immomo.moment.mediautils.cmds.EffectModel;
import com.immomo.videosdk.R;
import com.immomo.videosdk.recorder.MediaConstants;
import com.immomo.videosdk.recorder.view.VideoSpeedAdjustFragment;

/**
 * Project momodev
 * Package com.immomo.momo.moment.activity
 * Created by tangyuchun on 2/16/17.
 */

public class VideoSpeedAdjustActivity extends BaseFullScreenActivity {
    public static void start(Activity context, String videoPath, EffectModel lastEffectModel, int requestCode) {
        Intent intent = new Intent(context, VideoSpeedAdjustActivity.class);
        intent.putExtra(MediaConstants.KEY_VIDEO_PATH, videoPath);
        if (lastEffectModel != null) {
            intent.putExtra(MediaConstants.KEY_VIDEO_SPEED_PARAMS, lastEffectModel);
        }
        context.startActivityForResult(intent, requestCode);
    }

    private VideoSpeedAdjustFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_speed_adjust);

        Intent intent = getIntent();
        Bundle args = intent != null ? intent.getExtras() : new Bundle();
        fragment = new VideoSpeedAdjustFragment();
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }
}
