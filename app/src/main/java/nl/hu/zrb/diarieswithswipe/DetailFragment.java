package nl.hu.zrb.diarieswithswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class DetailFragment extends Fragment {
    String key;
    TextView tv1, tv2, tv3;
    DatabaseReference ref;

    public DetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        tv1 = (TextView)v.findViewById(R.id.titleView);
        tv2 = (TextView) v.findViewById(R.id.contentView);
        tv3 = (TextView) v.findViewById(R.id.dateView);
        //ToDo
        return v;
    }

    public void setKey(String key){
        this.key = key;

    }
}
