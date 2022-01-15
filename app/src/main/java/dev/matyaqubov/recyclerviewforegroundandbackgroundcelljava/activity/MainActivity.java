package dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.R;
import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.adapter.MainAdapter;
import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.helper.RecyclerViewToachHolder;
import dev.matyaqubov.recyclerviewforegroundandbackgroundcelljava.model.Member;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Member> members;
    private MainAdapter mainAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String android_id = androidID();

            Log.d(android_id, "onCreate: "+ android_id);


        if (isEmulator()) {
            Toast.makeText(this, "emulator", Toast.LENGTH_SHORT).show();
        } else {
            initViews();
        }


    }


    public boolean isNotEmulator(){
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tm.getNetworkOperatorName();
        if("Android".equals(networkOperator)) { //Since Android emulator always retuns "Android" as network operator, I use above code
            // Emulator
            Log.d("@@"," in Emulator");
            return false;
//            finish();
        }
        else {
            // Device
            Log.d("@@","in Device");
            return true;
        }
    }
    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public String androidID(){
       String id= Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }

    private void initViews() {
        context=this;
        members=new ArrayList<>();
        prepareMembers();
        recyclerView=findViewById(R.id.rv_main);
        mainAdapter=new MainAdapter(context,members);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context,1));

        ItemTouchHelper.SimpleCallback itemTouchSimpleCallback = new RecyclerViewToachHolder(0, ItemTouchHelper.LEFT, new RecyclerViewToachHolder.RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

            }
        });

        new ItemTouchHelper(itemTouchSimpleCallback).attachToRecyclerView(recyclerView);

    }

    private void prepareMembers() {
        for (int i = 0; i < 20; i++) {
            members.add(new Member(R.drawable.icon_person,"Bogibek","+998975259712"));
        }
    }
}