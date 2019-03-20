package com.example.realmexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.realmexam.models.Student;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private RecyclerView mRecyclerView;

    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.name_edit);
        mAgeEditText = findViewById(R.id.age_edit);
        mRecyclerView = findViewById(R.id.recycler_view);

        // 쿼리
        RealmResults<Student> results = realm.where(Student.class)
                .sort("age", Sort.DESCENDING).findAll();

        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(results);
        mRecyclerView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                int age = Integer.parseInt(mAgeEditText.getText().toString());

                // DB에 저장
                realm.beginTransaction();
                Student student = realm.createObject(Student.class);
                student.setName(name);
                student.setAge(age);
                realm.commitTransaction();
            }
        });
    }
}