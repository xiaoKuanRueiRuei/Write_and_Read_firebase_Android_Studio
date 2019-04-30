package com.example.readandwrite;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        listView.setAdapter(adapter);

        DatabaseReference reference_contacts = FirebaseDatabase.getInstance().getReference("contact");
        reference_contacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                    adapter.add(ds.child("name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //連接資料庫
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //目錄 (第一層)
        DatabaseReference ContactsRef = database.getReference("XiaoLuJiaYou");
        //幫第二層命名
        //TextView child1 = (TextView) findViewById(R.id.child1) ;
        //TextView child2 = (TextView) findViewById(R.id.child2) ;
        //TextView child3 = (TextView) findViewById(R.id.child3) ;
        //幫第三層命名
        //TextView name1 = (TextView) findViewById(R.id.name1) ;
        //TextView name2 = (TextView) findViewById(R.id.name2) ;
        //TextView name3 = (TextView) findViewById(R.id.name3) ;

        //TextView sex1 = (TextView) findViewById(R.id.sex1) ;
        //TextView sex2 = (TextView) findViewById(R.id.sex2) ;
        //TextView sex3 = (TextView) findViewById(R.id.sex3) ;

        //TextView age1 = (TextView) findViewById(R.id.age1) ;
        //TextView age2 = (TextView) findViewById(R.id.age2) ;
        //TextView age3 = (TextView) findViewById(R.id.age3) ;

        //將資料放入ContactsInfo
        ContactsInfo data1 = new ContactsInfo("XiaoLu", "Girl",20);
        //ContactsInfo data1 = new ContactsInfo(name1.toString(), sex1.toString(),age1.toString());
        ContactsInfo data2 = new ContactsInfo("XiaoKuan","Man",21);
        //ContactsInfo data2 = new ContactsInfo(name2.toString(),sex2.toString(),age2.toString());
        ContactsInfo data3 = new ContactsInfo("JiaYouXiaoLu","Wahaha",22);
        //ContactsInfo data3 = new ContactsInfo(name3.toString(),sex3.toString(),age3.toString());

        //將contact1放人子目錄 /ex1/1
        ContactsRef.child("contact").child("1").setValue(data1);
        //ContactsRef.child("contact").child(child1.toString()).setValue(data1);
        //將contact1放人子目錄 /ex1/2
        ContactsRef.child("contact").child("2").setValue(data2);
        //ContactsRef.child("contact").child(child2.toString()).setValue(data2);
        //將contact1放人子目錄 /ex1/3
        ContactsRef.child("contact").child("3").setValue(data3);
        //ContactsRef.child("contact").child(child3.toString()).setValue(data3);
    }

    public class ContactsInfo {

        private  String name;
        private  String sex;
        private  long age;

        public ContactsInfo() {
        }

        public ContactsInfo(String name, String sex, long age){
            this.age = age;
            this.name = name;
            this.sex = sex;
        }

        public String getName(){
            return this.name = name;
        }
        public String getSex(){
            return this.sex = sex;
        }
        public long getAge(){
            return this.age = age;
        }
    }
}
