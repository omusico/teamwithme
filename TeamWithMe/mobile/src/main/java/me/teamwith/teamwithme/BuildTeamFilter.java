package me.teamwith.teamwithme;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * A fragment to take input as to what hackathon the user is attending.
 */
public class BuildTeamFilter extends Fragment {
    private static final String ARG_USER_ID = "userId";

    private String mUserId;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BuildTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildTeamFilter newInstance(String userId) {
        BuildTeamFilter fragment = new BuildTeamFilter();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public BuildTeamFilter() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mUserId = getArguments().getString(ARG_USER_ID);
        }

        // ArrayAdapter to contain all of our hackathon names
        final ArrayAdapter<String> hackathons =
                new ArrayAdapter<String>(getActivity().getApplicationContext(),
                        android.R.layout.simple_list_item_1);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hackathon");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> events, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < events.size(); i++)
                        hackathons.add(events.get(i).getString("name"));

                    AutoCompleteTextView txtView =
                            (AutoCompleteTextView) getActivity().findViewById(R.id.hackathonView);

                    txtView.setAdapter(hackathons);
                    txtView.setTextColor(Color.BLACK);
                } else {
                    Log.wtf("BuildTeamFilter", "Couldn't load hackathon data.");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_filter, container, false);

        final AutoCompleteTextView txtView =
                (AutoCompleteTextView) view.findViewById(R.id.hackathonView);

        final Button next = (Button) view.findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, BuildTeamFragment.newInstance("xKYreGKk3X", txtView.getText().toString()))
                        .commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}