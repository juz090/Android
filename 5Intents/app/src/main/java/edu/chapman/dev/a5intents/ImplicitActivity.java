package edu.chapman.dev.a5intents;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ImplicitActivity extends Activity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn = new Button(this);
        setContentView(btn);
        btn.setText("Implit Intent Example");

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Uri number = Uri.parse("tel:5551234");
                Intent implicitIntent = new Intent(Intent.ACTION_DIAL, number);

                //Uri webpage = Uri.parse("http://www.google.com");
                //Intent implicitIntent = new Intent(Intent.ACTION_VIEW, webpage);


                startActivity(implicitIntent);


            }
        });

    }
}
