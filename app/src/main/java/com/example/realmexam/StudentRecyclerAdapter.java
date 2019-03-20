package com.example.realmexam;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexam.models.Student;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class StudentRecyclerAdapter extends RealmRecyclerViewAdapter<Student, StudentRecyclerAdapter.StudentViewHolder> {

    public StudentRecyclerAdapter(
            @Nullable OrderedRealmCollection<Student> data) {
        super(data, true);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // ViewHolder 만드는 부분
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student, viewGroup, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int position) {
        // 실제로 화면에 뿌려주는 부분
        final Student student = getItem(position);
        studentViewHolder.nameTextView.setText(student.getName());
        studentViewHolder.ageTextView.setText(student.getAge() + "");
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ageTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text);
            ageTextView = itemView.findViewById(R.id.age_text);
        }
    }
}
