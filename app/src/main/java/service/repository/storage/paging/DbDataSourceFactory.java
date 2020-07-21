package service.repository.storage.paging;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import service.repository.storage.database.MovieDao;
import service.repository.storage.database.MovieDatabase;

public class DbDataSourceFactory extends DataSource.Factory {
    private static final String TAG = DbDataSourceFactory.class.getSimpleName();
    private final DbPageKeyDataSource dbPageKeyDataSource ;

    public DbDataSourceFactory(MovieDao dao) {
        this.dbPageKeyDataSource = new DbPageKeyDataSource(dao);
    }

    @NonNull
    @Override
    public DataSource create() {
        return dbPageKeyDataSource;
    }
}
