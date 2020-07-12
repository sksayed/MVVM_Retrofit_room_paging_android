package CallBacks;

public class SalaryDataSource implements ISalaryDataSource {
    public SalaryDataSource() {
    }

    @Override
    public Double getSalaryFromWeb(String month) {
        /*
         * here i am behaving as if
         * its been taking 5 sec for geting that value
         * */
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         double sal = 0.0;
        switch (month) {
            case "jan":
                sal = 800.0;
                break;
            case "feb":
                sal = 500.0;
                break;
            case "march":
                sal = 905.0;
                break;
        }

        return sal ;
    }
}
