package gigigo.com.orchextra.data.datasources.api.interceptors;

import com.gigigo.ggglib.network.retrofit.RetrofitNetworkInterceptor;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by rui.alonso on 24/5/17.
 */

public class Logging extends RetrofitNetworkInterceptor {

  HttpLoggingInterceptor loggingInterceptor;

  public Logging() {
    this.loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    return loggingInterceptor.intercept(chain);
  }
}
