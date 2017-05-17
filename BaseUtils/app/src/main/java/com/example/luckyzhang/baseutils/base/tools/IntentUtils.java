package com.example.luckyzhang.baseutils.base.tools;

import android.content.Context;
import android.content.Intent;

/**
 * Created by luckyzhang on 2017/5/16.
 */

public class IntentUtils {

    public static void goIntent(Context context, Class<?> clazz) {
        if (clazz == null) return;
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
