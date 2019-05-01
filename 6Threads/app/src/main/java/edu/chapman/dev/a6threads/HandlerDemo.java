package edu.chapman.dev.a6threads;


//Menu defined in layout
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import java.util.concurrent.atomic.AtomicBoolean;

public class HandlerDemo extends Activity {
    ProgressBar bar;

    Handler handler=new Handler() {
        //Handle msg
        public void handleMessage(Message msg) {
            bar.incrementProgressBy(5);
            System.out.println("Got Msg:" + Thread.currentThread().getName());
        }
    };
    AtomicBoolean isRunning=new AtomicBoolean(false);

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        bar=(ProgressBar)findViewById(R.id.progress);
    }

    public void onStart() {
        super.onStart();
        bar.setProgress(0);

        //New Thread
        Thread background=new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i=0;i<20 && isRunning.get();i++) {
                        Thread.sleep(1000);
                        //Sendmsg
                        handler.sendMessage(handler.obtainMessage());
                        System.out.println("Send Msg:" + Thread.currentThread().getName());
                    }
                }
                catch (Throwable t) {
                    // just end the background thread
                }
            }
        });

        isRunning.set(true);
        background.start();
    }

    public void onStop() {
        super.onStop();
        isRunning.set(false);
    }
}