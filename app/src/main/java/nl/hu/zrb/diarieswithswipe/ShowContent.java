package nl.hu.zrb.diarieswithswipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ShowContent extends AppCompatActivity {
    String key;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv1 = (TextView)findViewById(R.id.titleView);
        tv2 = (TextView) findViewById(R.id.contentView);
        tv3 = (TextView) findViewById(R.id.dateView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("entries").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DiaryEntry entry = dataSnapshot.getValue(DiaryEntry.class);
                tv1.setText(entry.getTitle());
                tv2.setText(entry.getContent());
                Date date = new Date(entry.getDate());
                String datedata = DateFormat.format("MMM dd, yyyy h:mmaa", date).toString();
                tv3.setText(datedata);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ShowContent", databaseError.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.deleteitem) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("entries").child(key).removeValue();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
