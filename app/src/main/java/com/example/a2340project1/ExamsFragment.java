package com.example.a2340project1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExamsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExamsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ClassesData currentClassData;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText topicEdt;
    private EditText dateEdt;
    private EditText dayEdt;
    private EditText timeEdt;
    private EditText locationEdt;

    public ExamsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExamsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExamsFragment newInstance(String param1, String param2) {
        ExamsFragment fragment = new ExamsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_exams, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set up recycler view
        RecyclerView recyclerViewExams = view.findViewById(R.id.recyclerView);
        recyclerViewExams.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<Exam> defaultList = new ArrayList<>();
        defaultList.add(new Exam("No Valid Class Selected", 1, 1,
                "N/A", "N/A"));
        ExamAdapter examAdapter = new ExamAdapter(this.getContext(), new ArrayList<>());
        examAdapter.setOnItemClickListener(new ExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exam exam) {

            }
        });
        recyclerViewExams.setAdapter(examAdapter);
        //set up dropdown
        Spinner dropdown = view.findViewById(R.id.examsDropDown);
        List<String> classesOptions = ClassesFragment2.classStringList;
        List<ClassesData> classDataOptions = ClassesFragment2.classList;

        //String[] testArray = new String[]{"test", "next", "lol"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item, classesOptions);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentClassData = classDataOptions.get(position);
                List<Exam> currentExamList = currentClassData.getExamList();

                examAdapter.updateData(currentExamList);
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                examAdapter.updateData(defaultList);
            }
        });

        //set up add exam button
        Button addExam = view.findViewById(R.id.addExam);

        TableLayout examSettings = view.findViewById(R.id.examSettings);
        ConstraintLayout examList = view.findViewById(R.id.examsList);
        addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examSettings.setVisibility(View.VISIBLE);
                examList.setVisibility(View.GONE);

            }
        });

        //set up edit text
        topicEdt = view.findViewById(R.id.idEdtExamTopic);
        dateEdt = view.findViewById(R.id.idEdtExamDate);
        dayEdt = view.findViewById(R.id.idEdtExamDateDay);
        timeEdt = view.findViewById(R.id.idEdtExamTime);
        locationEdt = view.findViewById(R.id.idEdtExamLocation);

        //set up confirm add button
        Button confirmAdd = view.findViewById(R.id.addConfirm);


        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = topicEdt.getText().toString();
                int month = Integer.parseInt(dateEdt.getText().toString());
                int day = Integer.parseInt(dayEdt.getText().toString());
                String time = timeEdt.getText().toString();
                String location = locationEdt.getText().toString();

                Exam newExam = new Exam(topic, month, day, time, location);
                currentClassData.addExam(newExam);
                examAdapter.updateData(currentClassData.examList);

                examSettings.setVisibility(View.GONE);
                examList.setVisibility(View.VISIBLE);
            }
        });


    }


}