package tintuc.diepnvph04430.diep.tintuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tintuc.diepnvph04430.diep.tintuc.model.TinTuc;

/**
 * Created by Admin on 23/11/2016.
 */

public class Custom_tonghop extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<TinTuc> ArrTintuc;

    public Custom_tonghop(Context mycontext, int myLayout, List<TinTuc> ArrTintuc) {
        this.mycontext = mycontext;
        this.myLayout = myLayout;
        this.ArrTintuc = ArrTintuc;

    }

    @Override
    public int getCount() {
        return ArrTintuc.size();
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

        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);


        TextView loai = (TextView) convertView.findViewById(R.id.ctLoai);
        loai.setText(ArrTintuc.get(position).getLoaitt());
        TextView ctTieude = (TextView) convertView.findViewById(R.id.ctTieude);
        ctTieude.setText(ArrTintuc.get(position).getTieudett());
//        TextView ctNoidung = (TextView) convertView.findViewById(R.id.ctNoidung);
//        ctNoidung.setText(ArrTintuc.get(position).getNoidungtt());
        TextView ctNgay = (TextView) convertView.findViewById(R.id.ctNgay);
        ctNgay.setText(ArrTintuc.get(position).getNgaytt());
        TextView ctGio = (TextView) convertView.findViewById(R.id.ctGio);
        ctGio.setText(ArrTintuc.get(position).getGiott());


        ImageView imageView = (ImageView) convertView.findViewById(R.id.ctImg);
//              imageView.setImageURI(Uri.parse(ArrPhimhot.get(position).getThumb()));
//        Bitmap bitmap = Bit

        String urllink = ArrTintuc.get(position).getAnhtt();
        Picasso.with(mycontext).load(urllink).into(imageView);


        return convertView;
    }
}