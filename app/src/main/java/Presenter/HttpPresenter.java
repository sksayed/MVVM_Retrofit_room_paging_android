package Presenter;

import java.util.function.Consumer;

import Repository.HttpDataRepo;
import Repository.HttpDataRepoImpl;

public class HttpPresenter {
    private HttpDataRepo httpDataRepo ;

    public HttpPresenter() {
        this.httpDataRepo = new HttpDataRepoImpl();
    }

    public void getDataAsync (Consumer<String> callBack) {
        httpDataRepo.getData(val -> callBack.accept(val) );
    }
}
