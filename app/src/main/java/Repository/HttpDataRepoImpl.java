package Repository;

import java.util.function.Consumer;

import DataSource.HttpDataSource;
import DataSource.HttpDataSourceImpl;

public class HttpDataRepoImpl implements HttpDataRepo {
    private HttpDataSource dataSource ;

    public HttpDataRepoImpl() {
        this.dataSource = new HttpDataSourceImpl();
    }

    @Override
    public void getData(Consumer<String> data) {
        this.dataSource.getDataAsync(callback ->data.accept(callback));
    }
}
