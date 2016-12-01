package tintuc.diepnvph04430.diep.tintuc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Joker on 11/28/2016.
 */

public class Custom_chitiet extends BaseAdapter {
    Context mycontext2;
    int myLayout2;
    List<String> ArrChitiet;

    public Custom_chitiet(Context mycontext2, int myLayou2t, List<String> ArrChitiet) {
        this.mycontext2 = mycontext2;
        this.myLayout2 = myLayout2;
        this.ArrChitiet = ArrChitiet;

    }

    @Override
    public int getCount() {
        return ArrChitiet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
