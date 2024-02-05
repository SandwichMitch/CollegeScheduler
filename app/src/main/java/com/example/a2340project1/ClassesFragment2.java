package com.example.a2340project1;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassesFragment2#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ClassesFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView languageLV;
    private EditText itemEdt;
    private EditText timeEdt;
    private EditText instructorEdt;
    public static List<String> classStringList = new ArrayList<String>();
    public static List<ClassesData> classList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassesFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassesFragment2 newInstance(String param1, String param2) {
        ClassesFragment2 fragment = new ClassesFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ClassesFragment2() {
        // Required empty public constructor
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

        View view = inflater.inflate(R.layout.fragment_classes2, container, false);

        languageLV = view.findViewById(R.id.idLVLanguages);
        itemEdt = view.findViewById(R.id.idEdtItemName);
        timeEdt = view.findViewById(R.id.idEdtItemTime);
        instructorEdt = view.findViewById(R.id.idEdtItemInstructor);
        FloatingActionButton add_fab = view.findViewById(R.id.floatingActionButton2);
        FloatingActionButton del_fab = view.findViewById(R.id.floatingActionButton3);
        ArrayAdapter<String> classArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, classStringList);
        languageLV.setAdapter(classArrayAdapter);

        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemEdt.getText().toString();
                String time = timeEdt.getText().toString();
                String instructor = instructorEdt.getText().toString();

                String aggregate = item + " " + time + " " + instructor;
                if (!item.isEmpty() && !time.isEmpty() && !instructor.isEmpty() && !classStringList.contains(aggregate)) {

                    // on below line we are adding item to our list.
                    classStringList.add(aggregate);

                    ClassesData newClass = new ClassesData(item);
                    classList.add(newClass);

                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    classArrayAdapter.notifyDataSetChanged();
                }
                else if (classStringList.contains(aggregate)) {
                    Snackbar.make(view, "This class already exists!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        del_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemEdt.getText().toString();
                String time = timeEdt.getText().toString();
                String instructor = instructorEdt.getText().toString();

                String aggregate = item + " " + time + " " + instructor;
                if (!item.isEmpty() && classStringList.contains(aggregate)) {

                    // on below line we are adding item to our list.
                    classStringList.remove(aggregate);

                    ClassesData newClass = new ClassesData(item);
                    classList.remove(newClass);

                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    classArrayAdapter.notifyDataSetChanged();
                }

                else if (!classStringList.contains(aggregate)) {
                    Snackbar.make(view, "This class doesn't exist!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        languageLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("Edit:");
                alert.setMessage("Edit the class here:");

                // Set an EditText view to get user input
                final EditText input = new EditText(getContext());
                input.setText(item);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        ArrayAdapter adapter = (ArrayAdapter) parent.getAdapter();
                        adapter.insert(input.getText().toString(), position);
                        ClassesData newClass = new ClassesData(input.getText().toString());
                        classList.add(newClass);
                        adapter.remove(item);
                        String[] splitted = item.split("\\s+");
                        ClassesData removeClass = new ClassesData(splitted[0]);
                        classList.remove(removeClass);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });



        return view;
    }
}