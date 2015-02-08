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
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * A fragment to assist with building a team in an intuitive manner.
 */
public class BuildTeamFragment extends Fragment {
    private static final String ARG_USER_ID = "userId";
    private static final String ARG_HACKATHON = "hackathon";

    private String mUserId;
    private String mHackathon;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BuildTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildTeamFragment newInstance(String userId, String hackathon) {
        BuildTeamFragment fragment = new BuildTeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        args.putString(ARG_HACKATHON, hackathon);
        fragment.setArguments(args);
        return fragment;
    }

    public BuildTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mUserId = getArguments().getString(ARG_USER_ID);
            mHackathon = getArguments().getString(ARG_HACKATHON);
        }

        // Get the URL's for the hackathon images
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hackathon");
        query.whereEqualTo("name", mHackathon);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> events, ParseException e) {
                if (e == null) {
                    String logoUrl = events.get(0).getString("logoUrl");
                    String coverUrl = events.get(0).getString("coverUrl");

                    ImageView logo = (ImageView) getActivity().findViewById(R.id.hackLogo);
                    ImageView cover = (ImageView) getActivity().findViewById(R.id.hackCover);

                    Picasso.with(getActivity().getApplicationContext())
                           .load(logoUrl)
                           .into(logo);

                    Picasso.with(getActivity().getApplicationContext())
                            .load(coverUrl)
                            .into(cover);
                } else {
                    Log.wtf("BuildTeamFilter", "Couldn't load hackathon data.");
                }
            }
        });
        */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserSkill");
        query.whereNotEqualTo("userId", "xKYreGKk3X");
        query.whereNotEqualTo("userId", "na9edSCXZf");
        query.whereNotEqualTo("userId", "6VEKUs5w8T");
        query.whereNotEqualTo("userId", "S7Fcopiwzx");
        query.whereNotEqualTo("userId", "hPF2eZxxvB");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null) {
                    Random r = new Random();
                    ParseObject user = users.get(r.nextInt(users.size()));
                    Vector<ParseObject> skills = new Vector<ParseObject>();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getString("userId").equals(user.getString("userId"))) {
                            skills.add(users.get(i));
                        }
                    }

                    // Get name, etc.
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                    query.whereEqualTo("objectId", user.getString("userId"));
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> people, ParseException e) {
                            if (e == null) {
                                String name = people.get(0).getString("name");
                                String email = people.get(0).getString("email");

                                // TODO: Update the view elements with the data
                            } else {
                                Log.wtf("BuildTeamFragment", "Couldn't find shit");
                            }
                        }
                    });
                } else {
                    Log.wtf("BuildTeamFragment", "Oh noooo");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_build_team, container, false);
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
        try {
            mListener = (BuildTeamFragment.OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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