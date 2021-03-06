package com.jayfeng.lesscode.core;

import android.text.TextUtils;
import android.util.Log;

/**
 * support for custom log printing out in case
 * adb shell setprop log.tag.YOUR_LOG_TAG LEVEL
 * for thinking of perfomence, it need restart your app
 *
 * Example (from system):
 * adb shell setprop log.tag.SQLiteLog V
 */
public class LogLess {

    public static final boolean DEBUG_TAG_LOG = Log.isLoggable($.sTAG, Log.VERBOSE);

    public static void v(String str) {
        if ($.sDebug || DEBUG_TAG_LOG) {
            Log.v(getTag(), buildLogString(str));
        }
    }

    public static void $d(String str) {
        if ($.sDebug || DEBUG_TAG_LOG) {
            Log.d(getTag(), buildLogString(str));
        }
    }

    public static void $i(String str) {
        if ($.sDebug || DEBUG_TAG_LOG) {
            Log.i(getTag(), buildLogString(str));
        }
    }

    public static void $w(String str) {
        if ($.sDebug || DEBUG_TAG_LOG) {
            Log.w(getTag(), buildLogString(str));
        }
    }

    public static void $e(String str) {
        if ($.sDebug || DEBUG_TAG_LOG) {
            Log.e(getTag(), buildLogString(str));
        }
    }

    private static String getTag() {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        if (TextUtils.isEmpty($.sTAG)) {
            return caller.getFileName();
        }
        return $.sTAG;
    }

    private static String buildLogString(String str) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty($.sTAG)) {
            stringBuilder.append(caller.getMethodName())
                    .append("():")
                    .append(caller.getLineNumber())
                    .append(":")
                    .append(str);
        } else {
            stringBuilder
                    .append(caller.getFileName())
                    .append(".")
                    .append(caller.getMethodName())
                    .append("():")
                    .append(caller.getLineNumber())
                    .append(":")
                    .append(str);
        }
        return stringBuilder.toString();
    }
}
