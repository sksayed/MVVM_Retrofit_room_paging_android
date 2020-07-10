package Switch;

import android.content.Context;
import android.widget.Button;

import com.sayed.learnigretrofitlearning.R;

public class ButtonLight implements Switchable {
   private Button button ;

    public ButtonLight(Button button) {
        this.button = button;
    }

    @Override
    public void turnedOn() {
        this.button.setBackgroundColor(button.getResources().getColor(R.color.Aqua , null));
    }

    @Override
    public void turnedOff() {
        this.button.setBackgroundColor(button.getResources().getColor(R.color.DarkOrchid , null));
    }
}
