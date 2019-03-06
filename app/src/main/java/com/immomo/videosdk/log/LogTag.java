package com.immomo.videosdk.log;

public interface LogTag {
    String COMMON = "MomoVideoSDK";

    interface RECORDER {
        String MUSIC = "MUSIC";
        String FACE = "VideoFaceUtils";

        String VIDEO_CUT = "VideoCut";

        String RECORD = "Record";
    }

    String IMAGE = "Image";


    interface PROCESSOR {
        String MUSIC = "MUSIC";
        String FACE = "VideoFaceUtils";

        String VIDEO_CUT = "VideoCut";

        String PROCESS = "Process";
    }
}
