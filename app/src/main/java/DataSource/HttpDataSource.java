package DataSource;

import java.util.function.Consumer;

public interface HttpDataSource {
    void getDataAsync (Consumer<String> callBack);
}
