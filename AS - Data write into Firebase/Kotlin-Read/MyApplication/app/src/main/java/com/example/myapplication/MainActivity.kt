package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import android.view.View
import com.google.firebase.database.DatabaseError

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //以下為讀入檔
        //首先，因為此案例是以ListView作為顯示畫面，因此在此定義
        val listView = findViewById<View>(R.id.list) as ListView
        //在此創立一個Array，進行資料讀入時的容器
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1
        )
        //將剛剛定義好的listview，丟入上面容器的值
        listView.adapter = adapter

        //此處進行檔案讀入

        //FirebaseDatabase.getInstance().getReference("contact")
        //此處是將已經連線上的Firebase中，既定母欄位contact，做出連結
        val reference_contact = FirebaseDatabase.getInstance().getReference("contact")
        reference_contact.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            //此處是讀入子項目
            //adapter.add(ds.child("name").value!!.toString())
            //取得名為name的值
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                adapter.clear()
                for (ds in dataSnapshot.children) {
                    adapter.add(ds.child("name").value!!.toString())
                }
            }
        })
    }
}