package lsw.guichange.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import lsw.guichange.R;

public class CustomBulletinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bulletin);

        Bundle extra = getIntent().getExtras();
        String s = extra.getString("BulletinName");
        int i = extra.getInt("BulletinImg");

        TextView currentBulletinName = (TextView)findViewById(R.id.currentBulletinName);
        ImageView currentBulletinImg = (ImageView)findViewById(R.id.currentBulletinImage);

        currentBulletinName.setText(s);
        currentBulletinImg.setImageResource(i);
    }
}
