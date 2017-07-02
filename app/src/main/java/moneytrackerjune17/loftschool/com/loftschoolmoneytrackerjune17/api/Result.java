package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.api;

import android.text.TextUtils;

/**
 * Created by andreysinetskiy on 01.07.17.
 */

public class Result {
    String status;

    public boolean isSuccess() {
        return TextUtils.equals(status, "success");
    }
}
