package tr.gov.teias.recyclerviewretrofitpicasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pronious on 26/09/2017.
 */

// In order to add ItemClickListener to RecyclerView, follow the steps numbered below!

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<AlbumItem> albumItems;
    private Context context;

    // 3. Create a final private ListItemClickListener
    private ListItemClickListener mOnClickListener;


    // 1. Add an interface called ListItemClickListener
    // 2. Within that interface, define a void method called onLi... that takes an integer as a param
    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);


    }
    // 4. Add it to a constructor
    public RecyclerAdapter(List<AlbumItem> albumItems, Context context, ListItemClickListener mOnClickListener) {
        this.albumItems = albumItems;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.tv_android.setText(albumItems.get(position).getTitle());
        Picasso.with(context).load(albumItems.get(position).getThumbnailUrl()).resize(90, 90).into(holder.img_android);

    }

    @Override
    public int getItemCount() {
        return albumItems.size();
    }

    // 5. Implement onClick... in the Viewholder class

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_android = itemView.findViewById(R.id.tv_android);
            img_android = itemView.findViewById(R.id.img_android);

            // 7. Call setOn.. on the view passed into the constructor
            itemView.setOnClickListener(this);
        }

        // 6. Override onClick, passing the clicked item's position
        @Override
        public void onClick(View view) {
            int clickedPosition  = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}