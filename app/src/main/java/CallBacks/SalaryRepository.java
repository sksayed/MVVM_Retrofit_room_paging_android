package CallBacks;

import java.util.function.Consumer;

public class SalaryRepository implements ISalaryRepository {
    private ISalaryDataSource dataSource;
    private double salary ;

    public SalaryRepository() {
        this.dataSource = new SalaryDataSource();
    }

    public void getSalary(String month , Consumer<Double> callback) {
         dataSource.getSalaryFromWeb(month.toLowerCase(), xyz ->{
            callback.accept(xyz);
        });
    }

}
