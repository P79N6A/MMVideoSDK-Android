package com.immomo.videosdk.recorder.helper;

import com.immomo.mdlog.MDLog;
import com.immomo.videosdk.bean.MomentFace;
import com.immomo.videosdk.log.LogTag;

/**
 * Created by chenwangwang on 2018/3/27.
 * 完整性监测，确保资源文件已经完成下载，并且未被用户修改过。
 */
public class IntegrityDetectionChain {
    private MomentFace mMomentFace;

    public IntegrityDetectionChain(MomentFace momentFace) {
        mMomentFace = momentFace;
    }

    public boolean handle() {
        MDLog.e(LogTag.COMMON, "--->完整性校验<----");
        return MomentFaceUtil.isFaceResourceOK(mMomentFace);
    }

}
