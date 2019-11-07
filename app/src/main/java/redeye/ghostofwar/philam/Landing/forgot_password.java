package redeye.ghostofwar.philam.Landing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import redeye.ghostofwar.philam.R;

public class forgot_password extends AppCompatActivity {
Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(forgot_password.this, password_confirm.class);
                startActivity(a);
            }
        });



    }
}
