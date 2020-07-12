package CallBacks;

import java.util.function.Consumer;

interface ISalaryDataSource {
    void getSalaryFromWeb (String month , Consumer<Double> salary);
}
