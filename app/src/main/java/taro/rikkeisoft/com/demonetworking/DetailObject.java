package taro.rikkeisoft.com.demonetworking;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by VjrutNAT on 11/6/2017.
 */

public class DetailObject extends AppCompatActivity {

    private Object mObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        if (intent != null){
            mObject = (Object) intent.getExtras().getSerializable(Utils.KEY_OBJECT);
        }
        TextView tvUserId = (TextView) findViewById(R.id.tv_user_id);
        TextView tvId = (TextView) findViewById(R.id.tv_id);
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        TextView tvBody = (TextView) findViewById(R.id.tv_body);

        tvUserId.setText(String.valueOf(mObject.getUserId()));
        tvId.setText(String.valueOf(mObject.getId()));
        tvTitle.setText(mObject.getTitle());
        tvBody.setText(mObject.getTitle());
    }
}
