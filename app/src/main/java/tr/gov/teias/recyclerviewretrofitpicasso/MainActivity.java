package tr.gov.teias.recyclerviewretrofitpicasso;

import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



// 8. Implement ListItemClickListener
public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<AlbumItem> albumItems;
    private ProgressBar pb;

    // 9. Create a toast variable
    private Toast mToast;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    RequestInterface requestService = retrofit.create(RequestInterface.class);
    Call<List<AlbumItem>> list = requestService.getPostList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true); // Means that every item in RV has a fixed size.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        list.enqueue(new Callback<List<AlbumItem>>() {
            @Override
            public void onResponse(Call<List<AlbumItem>> call, Response<List<AlbumItem>> response) {
                pb.setVisibility(View.INVISIBLE);
                List<AlbumItem> jsonString = response.body();
                adapter = new RecyclerAdapter(jsonString,getApplicationContext(),MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<AlbumItem>> call, Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });

    }

    // 10. Override onListItemClick method
    // 11. Cancel the toast if it is not null. Doing so ensures that our new Toast will show immediately, rather than being delayed while other pending Toasts are shown.

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if(mToast != null){
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();
    }
}
