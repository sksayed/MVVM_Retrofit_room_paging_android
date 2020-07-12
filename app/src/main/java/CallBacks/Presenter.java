package CallBacks;

import java.util.function.Consumer;

public class Presenter {
    private ISalaryRepository repository;

    public Presenter() {
        this.repository = new SalaryRepository();
    }

    public void getSalary(String month, Consumer<String> doubleConsumer) {
        if (isValidString(month)) {
            this.repository.getSalary(month, callback -> {
                //presenter will modify the value required for
                //showing in the view
                Double value = callback;
                String modifier = "The Salary of "+month+" is "+value+ " Bdt";
                doubleConsumer.accept(modifier);
            });
        }
    }

    private boolean isValidString(String s) {
        if (s.isEmpty() || s == "") {
            return false;
        } else {
            return true;
        }
    }
}
