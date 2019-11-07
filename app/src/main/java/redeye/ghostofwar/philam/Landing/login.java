package redeye.ghostofwar.philam.Landing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import redeye.ghostofwar.philam.R;

public class login extends AppCompatActivity {
TextView forgotpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    forgotpass = (TextView) findViewById(R.id.forgotpass);

    forgotpass.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent a = new Intent (login.this, forgot_password.class);
            startActivity(a);
        }
    });

    }
}
