package Switch;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;

import com.sayed.learnigretrofitlearning.R;

import java.util.Random;

public class BlinkingLightOff extends ButtonLight implements Runnable {
    private CountDownTimer timer;
    private Random random;
    private volatile boolean isOn;
    private Thread thread;
    private volatile int[] ColorArray = new int[]{
            R.color.Bisque, R.color.SlateBlue, R.color.CadetBlue, R.color.BlanchedAlmond, R.color.LightSkyBlue, R.color.Brown, R.color.Blue
    };

    public BlinkingLightOff(Button button) {
        super(button);
        random = new Random();
        isOn = false;
        thread = new Thread(this::run);
        setUp();
    }

    private void setUp() {
        timer = new CountDownTimer(2000 , 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                run();
            }

            @Override
            public void onFinish() {
                if(isOn){
                    timer.start();
                }else {
                    timer.cancel();
                }
            }
        };
    }

    @Override
    public void turnedOff() {
        isOn = false;
        this.thread.interrupt();
        timer.onFinish();
        this.button.setBackgroundColor(this.button.getResources().getColor(R.color.DarkGray, null));
    }

    @Override
    public void turnedOn() {
        isOn = true;
        timer.start();
       // this.thread.start();
    }

    @Override
    public void run() {
        int val = random.nextInt(ColorArray.length - 1);
        {
            new Handler(Looper.getMainLooper()).post(() -> {
                Log.e("sayed", " inside the handler " + Thread.currentThread().getName());
                button.setBackgroundColor(button.getResources().getColor(ColorArray[val], null));
            });
        }
    }
}



/*
*this code will be executed in this thread
*  int val = random.nextInt(ColorArray.length - 1);
            new Handler().post(() -> {
                Log.e("sayed", " inside the handler " + Thread.currentThread().getName());
                button.setBackgroundColor(button.getResources().getColor(ColorArray[val], null));
            });
            *
            *
            * */