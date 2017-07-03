package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddItemActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    public static final String RESULT_ITEM = "item";
    public static final int RC_ADD_ITEM = 99;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        type = getIntent().getStringExtra(EXTRA_TYPE);

        final TextView add = (TextView) findViewById(R.id.add);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                add.setEnabled(!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(price.getText()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(RESULT_ITEM, new Item(name.getText().toString(), Integer.parseInt(price.getText().toString()), type));
                setResult(RESULT_OK,result);
                finish();
            }
        });

        name.addTextChangedListener(textWatcher);
        price.addTextChangedListener(textWatcher);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
