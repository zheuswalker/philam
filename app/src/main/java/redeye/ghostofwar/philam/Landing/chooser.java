package redeye.ghostofwar.philam.Landing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import redeye.ghostofwar.philam.R;

public class chooser extends AppCompatActivity {

    Button signup_btn, login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);

    login_btn =  (Button) findViewById(R.id.login);
    signup_btn =  (Button) findViewById(R.id.signup);

    login_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent a = new Intent(chooser.this, login.class);
            startActivity(a);
        }
    });
signup_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent ak = new Intent(chooser.this, signup.class);
        startActivity(ak);
    }
});





    }
}
