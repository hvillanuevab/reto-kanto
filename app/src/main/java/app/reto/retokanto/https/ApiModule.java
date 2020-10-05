package app.reto.retokanto.https;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarRecord;

import app.reto.retokanto.utils.SugarExclusionStrategy;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public final String BASE_KANTO="https://www.mocky.io/v2/";

    @Provides
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    public SugarExclusionStrategy provideSugarExclusionStrategy(){
        return new SugarExclusionStrategy(SugarRecord.class);
    }

    @Provides
    public Gson provideGson(){
        return new GsonBuilder()
                .addDeserializationExclusionStrategy(provideSugarExclusionStrategy())
                .addSerializationExclusionStrategy(provideSugarExclusionStrategy())
                .create();
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public KantoApi providesCimaApi(){
        return provideRetrofit(BASE_KANTO,provideOkHttpClient(),provideGson()).create(KantoApi.class);
    }
}
