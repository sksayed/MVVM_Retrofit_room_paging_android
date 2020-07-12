package CallBacks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class SalaryDataSource implements ISalaryDataSource {
    private ExecutorService executorService ;
    public SalaryDataSource() {
        executorService = Executors.newSingleThreadExecutor();
    }

    private Double getSalaryFromWebAsync(String month) {
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

    @Override
    public void getSalaryFromWeb(String month, Consumer<Double> salary) {
        executorService.submit(()->{
            salary.accept(getSalaryFromWebAsync(month));
        });
    }
}
