package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by andreysinetskiy on 29.06.17.
 */

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.
                Builder(getActivity());
        builder.setMessage(R.string.dialog)
                .setPositiveButton(R.string.fire, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // ...
                            }
                        })
                .setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // ...
                            }
                        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
