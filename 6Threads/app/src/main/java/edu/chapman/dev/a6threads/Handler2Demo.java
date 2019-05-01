package edu.chapman.dev.a6threads;



//Menu defined in layout
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Handler2Demo extends Activity {
    private ProgressBar bar;

    private Handler handler = new Handler() {
        // Handle msg
        public void handleMessage(Message msg) {
            int val = msg.arg1;
            myEditText.setText(Integer.valueOf(val).toString());
        }
    };
    boolean isRunning = false;

    private EditText myEditText;
    private Button btn;
    private Thread background;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.handler);

        btn = (Button) findViewById(R.id.button1);
        myEditText = (EditText) findViewById(R.id.editText1);

        btn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //myEditText.setText(Integer.valueOf(Math()).toString());
                myEditText.setText("Processing...");
                isRunning = true;
                background.start();

            }
        });

    }

    public int Math() {
        int x = 2;
        int small = 99;
        int large = 99999999;
        for (int i = 0; i < large; ++i) {
            x = x + 1;
            x = x - 1;
            x = x * 2;
            x = x / 2;
            x = x + 1;
            x = x - 1;
            x = x * 2;
            x = x / 2;
        }

        return x;
    }

    public void onStart() {
        super.onStart();

        // New Thread
        background = new Thread(new Runnable() {
            public void run() {
                // Sendmsg
                Message m = handler.obtainMessage();
                m.arg1 = Math();
                handler.sendMessage(m);
                System.out.println("Send Msg:"
                        + Thread.currentThread().getName());

            }
        });

    }

    public void onStop() {
        super.onStop();
        isRunning = false;
    }
}