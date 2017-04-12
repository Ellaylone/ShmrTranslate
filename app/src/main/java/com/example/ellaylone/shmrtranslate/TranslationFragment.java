package com.example.ellaylone.shmrtranslate;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by ellaylone on 09.04.17.
 */

public class TranslationFragment extends Fragment {
    public TranslationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_translation, container, false);

        EditText textSource = (EditText) view.findViewById(R.id.text_area_source);

        final TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);

        textSource.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                tabLayout.setVisibility(View.VISIBLE);
                            }
                        }, 100);
                } else {
                    tabLayout.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

}
