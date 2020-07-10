package Switch;

import android.util.Log;

public class Light implements Switchable {
    private final String TAG = this.getClass().getSimpleName();
    private final String name;

    public Light(String name) {
        this.name = name;
    }

    @Override
    public void turnedOn() {
        Log.e(TAG, this.name + "  Light is turned on");
    }

    @Override
    public void turnedOff() {
        Log.e(TAG, this.name + " Light is turned off");
    }


}
