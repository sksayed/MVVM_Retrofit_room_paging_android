package Switch;

import android.os.CountDownTimer;
import android.widget.Button;

import com.sayed.learnigretrofitlearning.R;

import java.util.Random;

public class BlinkingLight extends ButtonLight {
    private CountDownTimer timer ;
    private Random random;
    private int[] ColorArray = new int[] {
            R.color.Bisque,R.color.SlateBlue,R.color.CadetBlue,R.color.BlanchedAlmond,R.color.LightSkyBlue,R.color.Brown,R.color.Blue
    };
    public BlinkingLight(Button button) {
        super(button);
        random = new Random();
    }

    @Override
    public void turnedOff() {
       this.button.setBackgroundColor(this.button.getResources().getColor(R.color.DarkGray , null));
    }

    @Override
    public void turnedOn() {
      timer = new CountDownTimer(2000 , 500) {
          @Override
          public void onTick(long millisUntilFinished) {
              int val = random.nextInt(ColorArray.length-1);
              button.setBackgroundColor(button.getResources().getColor(ColorArray[val] , null));
          }

          @Override
          public void onFinish() {
              timer.start();
          }
      };
      timer.start();
    }
}
