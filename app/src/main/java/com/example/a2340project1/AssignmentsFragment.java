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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssignmentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ClassesData currentClassData;
    public Assignment currentAssignment;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText topicEdt;
    private EditText dateEdt;
    private EditText dayEdt;
    private EditText timeEdt;

    private EditText chngtopicEdt;
    private EditText chngdateEdt;
    private EditText chngdayEdt;
    private EditText chngtimeEdt;
    private boolean classSelected;

    public AssignmentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignmentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssignmentsFragment newInstance(String param1, String param2) {
        AssignmentsFragment fragment = new AssignmentsFragment();
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

        return inflater.inflate(R.layout.fragment_assignments, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout assignmentSettings = view.findViewById(R.id.assignmentSettings);
        ConstraintLayout assignmentList = view.findViewById(R.id.assignmentsList);

        //set up recycler view
        RecyclerView recyclerViewAssignments = view.findViewById(R.id.recyclerView);
        recyclerViewAssignments.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<Assignment> defaultList = new ArrayList<>();
        defaultList.add(new Assignment("No Valid Class Selected", 1, 1,
                "N/A"));
        AssignmentAdapter assignmentAdapter = new AssignmentAdapter(this.getContext(), new ArrayList<>());

        TableLayout editAssignment = view.findViewById(R.id.assignmentEdit);
        chngtopicEdt = view.findViewById(R.id.editAssignmentTopic);
        chngdateEdt = view.findViewById(R.id.editAssignmentDate);
        chngdayEdt = view.findViewById(R.id.editAssignmentDateDay);
        chngtimeEdt = view.findViewById(R.id.editAssignmentTime);



        assignmentAdapter.setOnItemClickListener(new AssignmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Assignment assignment) {
                editAssignment.setVisibility(View.VISIBLE);
                assignmentList.setVisibility(View.GONE);
                currentAssignment = assignment;
                Button confirmDelete = view.findViewById(R.id.confirmDeleteAssignmentButton);

                chngtopicEdt.setText(currentAssignment.getTaskName());
                chngdateEdt.setText(String.valueOf(currentAssignment.getDueMonth()));
                chngdayEdt.setText(String.valueOf(currentAssignment.getDueDay()));
                chngtimeEdt.setText(currentAssignment.getTime());

                confirmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentClassData.removeAssignment(currentAssignment);
                        assignmentAdapter.updateData(currentClassData.assignmentList);

                        editAssignment.setVisibility(View.GONE);
                        assignmentList.setVisibility(View.VISIBLE);
                    }
                });

                Button confirmEdit = view.findViewById(R.id.confirmEditAssignmentButton);
                confirmEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String topic = chngtopicEdt.getText().toString();
                        int month;
                        int day;
                        if (chngdateEdt.getText().length() == 0 || chngdayEdt.getText().length() == 0) {
                            month = 0;
                            day = 0;
                        } else {
                            try {
                                month = Integer.parseInt(chngdateEdt.getText().toString());
                                day = Integer.parseInt(chngdayEdt.getText().toString());
                            } catch (NumberFormatException invalidDate) {
                                month = 0;
                                day = 0;
                            }

                        }

                        String time = chngtimeEdt.getText().toString();
                        if ((topic.compareTo("") == 0) || (month == 0) || (day == 0)
                                || (time.compareTo("") == 0)) {
                            Toast noneSelected = Toast.makeText(getContext(), "One or more fields are empty or invalid!", Toast.LENGTH_LONG);
                            noneSelected.show();
                        } else {
                            currentAssignment.setTaskName(chngtopicEdt.getText().toString());
                            currentAssignment.setDueMonth(Integer.parseInt(chngdateEdt.getText().toString()));
                            currentAssignment.setDueDay(Integer.parseInt(chngdayEdt.getText().toString()));
                            currentAssignment.setTime(chngtimeEdt.getText().toString());


                            assignmentAdapter.updateData(currentClassData.assignmentList);

                            editAssignment.setVisibility(View.GONE);
                            assignmentList.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });
        recyclerViewAssignments.setAdapter(assignmentAdapter);
        //set up dropdown
        Spinner dropdown = view.findViewById(R.id.assignmentsDropDown);
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
                List<Assignment> currentAssignmentList = currentClassData.getAssignmentList();
                classSelected = true;

                assignmentAdapter.updateData(currentAssignmentList);
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                assignmentAdapter.updateData(defaultList);
                classSelected = false;
            }
        });



        //set up add assignment button
        Button addAssignment = view.findViewById(R.id.addAssignment);


        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!classSelected) {
                    Toast noneSelected = Toast.makeText(getContext(), "No class selected to add class to!", Toast.LENGTH_LONG);
                    noneSelected.show();
                } else {
                    assignmentSettings.setVisibility(View.VISIBLE);
                    assignmentList.setVisibility(View.GONE);
                }

            }
        });

        //set up edit text
        topicEdt = view.findViewById(R.id.idEdtAssignmentTopic);
        dateEdt = view.findViewById(R.id.idEdtAssignmentDate);
        dayEdt = view.findViewById(R.id.idEdtAssignmentDateDay);
        timeEdt = view.findViewById(R.id.idEdtAssignmentTime);

        //set up confirm add button
        Button confirmAdd = view.findViewById(R.id.addConfirmAssignment);


        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = topicEdt.getText().toString();
                int month;
                int day;
                if (dateEdt.getText().length() == 0 || dayEdt.getText().length() == 0) {
                    month = 0;
                    day = 0;
                } else {
                    try {
                        month = Integer.parseInt(dateEdt.getText().toString());
                        day = Integer.parseInt(dayEdt.getText().toString());
                    } catch (NumberFormatException invalidDate) {
                        month = 0;
                        day = 0;
                    }

                }

                String time = timeEdt.getText().toString();
                if ((topic.compareTo("") == 0) || (month == 0) || (day == 0)
                        || (time.compareTo("") == 0)) {
                    Toast noneSelected = Toast.makeText(getContext(), "One or more fields are empty or invalid!", Toast.LENGTH_LONG);
                    noneSelected.show();
                } else {
                    Assignment newAssignment = new Assignment(topic, month, day, time);
                    currentClassData.addAssignment(newAssignment);
                    assignmentAdapter.updateData(currentClassData.assignmentList);

                    assignmentSettings.setVisibility(View.GONE);
                    assignmentList.setVisibility(View.VISIBLE);
                }

            }
        });





    }


}