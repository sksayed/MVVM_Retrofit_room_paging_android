package DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class HttpDataSourceImpl implements HttpDataSource {

    private ExecutorService executorService ;

    public HttpDataSourceImpl() {
        executorService = Executors.newFixedThreadPool(5);
    }

    /*Making Http Request Involves
    * 1)Declare a URL Connection
    2)Open InputStream to connection
    3)Download and decode based on data type
    4)Wrap in AsyncTask and execute in background thread
    * */

    private String makeHttp() throws IOException {
        URL url = new URL("http://www.google.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 2. Open InputStream to connection
        conn.connect();
        InputStream in = conn.getInputStream();
        // 3. Download and decode the string response using builder
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString() ;
    }
    @Override
    public void getDataAsync (Consumer<String> callBack) {
        executorService.submit(()->{
            String s = " " ;
            try {
                callBack.accept(makeHttp());

            }catch (Exception e ){
                e.printStackTrace();
            }

        });
    }

}
