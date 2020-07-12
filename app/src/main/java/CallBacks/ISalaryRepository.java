package CallBacks;

import java.util.function.Consumer;

interface ISalaryRepository {
    void getSalary(String month , Consumer<Double> callback);
}
