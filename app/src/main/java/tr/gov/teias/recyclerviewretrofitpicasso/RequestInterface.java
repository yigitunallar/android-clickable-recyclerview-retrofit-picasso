package tr.gov.teias.recyclerviewretrofitpicasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pronious on 3.10.2017.
 */

public interface RequestInterface {

    @GET("photos")
    Call<List<AlbumItem>> getPostList();

}
