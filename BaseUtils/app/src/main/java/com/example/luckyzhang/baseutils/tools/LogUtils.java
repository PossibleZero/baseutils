package com.example.luckyzhang.baseutils.tools;

import android.util.Log;

/**
 * Log日志打印工具类
 * <p>debug模式下启用，release下不打印
 * <p>拉勾中所有日志打印均须通过该类来进行
 */
public class LogUtils {

    private static boolean isDebug = true;

    static String mTag = "zhangyn";

    //for error log
    public static void error(String msg) {
        if (isDebug) {
            Log.e(mTag, msg);
        }
    }

    //for warming log
    public static void warn(String msg) {
        if (isDebug) {
            Log.w(mTag, msg);
        }
    }

    //for info log
    public static void info(String msg) {
        if (isDebug) {
            Log.i(mTag, msg);
        }
    }

    //for debug log
    public static void debug(String msg) {
        if (isDebug) {
            Log.d(mTag, msg);
        }
    }

    //for verbose log
    public static void verbose(String msg) {
        if (isDebug) {
            Log.v(mTag, msg);
        }
    }

    //for error log
    public static void e(String msg) {
        if (isDebug) {
            Log.e(mTag, msg);
        }
    }

    //for warming log
    public static void w(String msg) {
        if (isDebug) {
            Log.w(mTag, msg);
        }
    }

    //for info log
    public static void i(String msg) {
        if (isDebug) {
            Log.i(mTag, msg);
        }
    }

    //for debug log
    public static void d(String msg) {
        if (isDebug) {
            Log.d(mTag, msg);
        }
    }

    //for verbose log
    public static void v(String msg) {
        if (isDebug) {
            Log.v(mTag, msg);
        }
    }


    //for warming log
    public static void w(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            Log.w(tag, msg);
        }
    }

    //for info log
    public static void i(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            Log.i(tag, msg);
        }
    }

    //for debug log
    public static void d(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            Log.d(tag, msg);
        }
    }

    //for verbose log
    public static void v(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            Log.v(tag, msg);
        }
    }

    //for verbose log
    public static void e(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            Log.e(tag, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 点击Log跳转到指定源码位置
     *
     * @param tag
     * @param msg
     */
    public static void showLog(String tag, String msg) {
        if (isDebug) {
            if (tag == null || "".equalsIgnoreCase(tag.trim())) {
                tag = mTag;
            }
            if (msg == null) {
                return;
            }
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();//获取当前的堆栈跟踪元素
            int currentIndex = -1;
            for (int i = 0; i < stackTraceElement.length; i++) {
                if (stackTraceElement[i].getMethodName().compareTo("showLog") == 0) {//找到走后一个调用showLog方法的地方，即当然调用showlog的地方
                    currentIndex = i + 1;
                    break;
                }
            }
            if (currentIndex >= 0) {
                String fullClassName = stackTraceElement[currentIndex].getClassName();//获取当前的堆栈跟踪元素的类名
                String className = fullClassName.substring(fullClassName
                        .lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();//获取当前的堆栈跟踪元素的方法名
                String lineNumber = String
                        .valueOf(stackTraceElement[currentIndex].getLineNumber());//获取当前的堆栈跟踪元素的代码行数
                Log.i(tag, msg + "\n  ---->at " + className + "." + methodName + "("
                        + className + ".java:" + lineNumber + ")");//打印log,(.java: 数字) 会直接在控制台给定跳转链接
            } else {
                Log.i(tag, msg);
            }

        }
    }
}
