package com.example.dell.contentproviderdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ArrayList<Contact> mListContact = new ArrayList<>();
    private final int REQUEST_READ_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayView();
        checkAndRequestPermissions();
    }

    private void displayView() {
        mRecyclerView = findViewById(R.id.recyler_view);
        mRecyclerView.setHasFixedSize(true);

        //StaggeredGridLayoutManager(int spanCount, int orientation)
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        ContactAdapter contactAdapter = new ContactAdapter(this, mListContact);
        mRecyclerView.setAdapter(contactAdapter);
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.READ_CONTACTS }, REQUEST_READ_CONTACT);
            }
            return;
        }
        loadContact();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_READ_CONTACT) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkAndRequestPermissions();
            }
        }
    }

    private void loadContact() {
        // Đối số đầu tiên của query là URI
        Cursor mCursor =
                getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        null, null, null);
        
        if (mCursor != null) {
            //Nếu Cursor không null, di chuyển cursor đến hàng(row) tiếp theo
            while (mCursor.moveToNext()) {
                mListContact.add(new Contact(mCursor.getString(mCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)), mCursor.getString(
                        mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
            }
            mCursor.close();
        }
    }
}
