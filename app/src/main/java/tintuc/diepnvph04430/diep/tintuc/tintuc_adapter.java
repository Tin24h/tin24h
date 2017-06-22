package tintuc.diepnvph04430.diep.tintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tuandeptrai on 15/10/2016.
 */

public class tintuc_adapter extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<TinTuc> list;

    public tintuc_adapter(Context context, int resource, ArrayList<TinTuc> list) {
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.listview_txt);

        TextView date = (TextView) convertView.findViewById(R.id.listview_txt1);



        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("dd-MM-yyyy");

        date.setText(ft.format(dNow));

        if (list.get(position).getTitle() != null) {
            name.setText(list.get(position).getTitle());

        } else {

            name.setText(" Tin da bi xoa");
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.listview_imageView);
        Picasso.with(context).load(list.get(position).getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(imageView);


        return convertView;
    }

    public void setFilter(ArrayList<TinTuc> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }


}


