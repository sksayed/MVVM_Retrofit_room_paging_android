package Switch;

import java.util.ArrayList;
import java.util.List;

public class SwitchBoard {
    private List<Switchable> switchList = new ArrayList<>();

    public void registerToSwitchBoard ( Switchable switchable) {
        this.switchList.add(switchable);
    }

    public void unRegisterToSwitchBoard (Switchable switchable) {
        if(switchList.contains(switchable)){
            switchList.remove(switchable);
        }
        return;
    }

    public void turnOn () {
        for (Switchable switchable : switchList){
            switchable.turnedOn();
        }
    }

    public void turnOff () {
        for (Switchable switchable : switchList){
            switchable.turnedOff();
        }
    }


}
