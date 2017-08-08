package lsw.guichange.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import lsw.guichange.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Bundle extra = getIntent().getExtras();
        String s = extra.getString("category");
        TextView title_view = (TextView) findViewById(R.id.category_activity_title);
        title_view.setText(s);
    }
}
