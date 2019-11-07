package redeye.ghostofwar.philam.Landing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import redeye.ghostofwar.philam.R;

public class signup extends AppCompatActivity {
TextView signinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        signinbtn = (TextView) findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(signup.this, login.class);
                startActivity(a);
            }
        });

    }
}
