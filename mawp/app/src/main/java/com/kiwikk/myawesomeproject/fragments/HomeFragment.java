package com.kiwikk.myawesomeproject.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kiwikk.myawesomeproject.R;
import com.kiwikk.myawesomeproject.elements.WeekButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final int WEEK_ROWS = 80;
    private static final int WEEK_COLUMNS = 50;

    private static View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onStart() {
        super.onStart();
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);

        for (int i = 0; i <= WEEK_ROWS; i++) {
            TableRow tableRow = new TableRow(this.getContext());
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j <= WEEK_COLUMNS; j++) {
                if (i == 0) {
                    TextView textView = new TextView(this.getContext());
                    if (j != 0 && j % 10 == 0 || j == 1) textView.setText(Integer.toString(j));
                    else textView.setText("");

                    tableRow.addView(textView);
                    continue;
                }

                WeekButton weekButton = new WeekButton(this.getContext(), i * 10 + j);
                weekButton.setLayoutParams(new TableRow.LayoutParams(100, 100));
                if (j == 0) {
                    TextView textView = new TextView(this.getContext());
                    if (i % 10 == 0 || i == 1) textView.setText(Integer.toString(i));
                    else textView.setText("");

                    tableRow.addView(textView);
                    continue;
                }

                tableRow.addView(weekButton, j);
            }

            tableLayout.addView(tableRow, i);
        }

    }
}