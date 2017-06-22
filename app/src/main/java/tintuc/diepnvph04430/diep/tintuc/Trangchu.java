package tintuc.diepnvph04430.diep.tintuc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Trangchu extends Fragment {
    View lichsuview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        lichsuview = inflater.inflate(R.layout.activity_one_fragment, container,false);
        return lichsuview;

    }

}
