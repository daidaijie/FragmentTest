package com.example.baicheng.fragmenttest;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baicheng.fragmenttest.model.Crime;
import com.example.baicheng.fragmenttest.model.CrimeLab;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {

    private static final String TAG = "CrimeFragment";

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mEditText;
    private Button mDateButton;
    private CheckBox mSolveCheckBox;


    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getCrimeLab(getActivity()).getCrime(crimeID);
//        Toast.makeText(getActivity(), "" + (mCrime == null), Toast.LENGTH_SHORT).show();
        returnResult();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mEditText = (EditText) v.findViewById(R.id.crime_tilte);
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mSolveCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);

        mEditText.setText(mCrime.getTitle());

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolveCheckBox.setChecked(mCrime.isSolved());
        mSolveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK,null);
    }

}
