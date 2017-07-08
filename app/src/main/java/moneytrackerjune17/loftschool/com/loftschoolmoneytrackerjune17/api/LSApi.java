package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.api;

import java.util.List;

import moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by andreysinetskiy on 01.07.17.
 */

public interface LSApi {

    @GET("auth")
    Call<AuthResult> auth(@Query("social_user_id") String socialUserId);

    @Headers("Content-Type: application/json")
    @GET("items")
    Call<List<Item>> items(@Query("type") String type);

    @GET("balance")
    Call<BalanceResult> balance();

    @POST("items/add")
    Call<AddResult> add(@Query("name") String name, @Query("price") int pri, @Query("type") String type);

    @POST("items/remove")
    Call<Result> remove(@Query("id") int id);
}
