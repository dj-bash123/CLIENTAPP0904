package in.co.snapqa.clientapp0903.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OLA on 10/03/17.
 */

public class APIClient {
    public static Retrofit retrofit = null;
    private static String url = "http://ec2-52-54-173-224.compute-1.amazonaws.com:7000/api/chat/";


    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return null;
    }
}
