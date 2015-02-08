package me.teamwith.teamwithme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    private static final String ARG_USER_ID = "userId";
    private OnFragmentInteractionListener mListener;
    public static String userID;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Team.
     */

    public static TeamFragment newInstance(String iD) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, iD);
        fragment.setArguments(args);
        //userID = iD;
        return fragment;
    }

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            userID = getArguments().getString(ARG_USER_ID);
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TeamUser");
        query.whereEqualTo("teamId", 1); // Our team id is 1, I'm hardcoding it for demo purposes.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> members, ParseException e) {
                if (e == null) {
                    // Grab the layout where we will just simply append team members.
                    final LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.teamLayout);

                    // We loop through each team member and get their name
                    for (int i = 0; i < members.size(); i++) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                        query.whereEqualTo("objectId", members.get(i).getString("userId"));
                        query.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> match, ParseException e) {
                                if (e == null) {
                                    Context context = getActivity().getApplicationContext();
                                    TextView text = new TextView(context);
                                    text.setText(match.get(0).getString("name"));
                                    text.setTextColor(Color.BLACK);
                                    layout.addView(text);
                                } else {
                                    Log.wtf("TeamFragment", "Couldn't load member info.");
                                }
                            }
                        });
                    }

                } else {
                    Log.wtf("TeamFragment", "Couldn't load team!");
                }
            }
        });



        /*
        // Get current user's profile picture, name and skills.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("objectId", userID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {
                    Context context = getActivity().getApplicationContext();
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageButton);

                    String url = object.get(0).getString("githubPictureUrl");
                    String name = object.get(0).getString("name");
                    TextView view = (TextView) getActivity().findViewById(R.id.user1);
                    view.setText(name);
                    Picasso.with(context).load(url).into(imageView);
                } else {
                    Log.i("team", "It's not working.");
                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TeamUser");
        query2.whereEqualTo("userId", userID);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {

                    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("TeamUser");
                    query3.whereEqualTo("teamId", object.get(0).getString("teamId"));
                    query3.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> object, ParseException e) {
                            if (e == null) {

                                for (int i = 0; i < object.size(); i++) {
                                    ParseQuery<ParseObject> query4 = ParseQuery.getQuery("TeamUser");
                                    query4.whereEqualTo("userId", object.get(i).getString("userId"));
                                    query4.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> object, ParseException e) {
                                            if (e == null) {

                                                Context context = getActivity().getApplicationContext();
                                                ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageButton);

                                                String url = object.get(0).getString("githubPictureUrl");
                                                String name = object.get(0).getString("name");
                                                TextView view = (TextView) getActivity().findViewById(R.id.user1);
                                                view.setText(name);
                                                Picasso.with(context).load(url).into(imageView);

                                            } else {
                                                Log.i("team", "It's not working.");
                                            }
                                        }
                                    });
                                }
                            } else {
                                Log.i("team", "It's not working.");
                            }
                        }
                    });

                } else {
                    Log.i("team", "It's not working.");
                }
            }
        });
        */
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_fragment_team, container, false);
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
            mListener = (OnFragmentInteractionListener) activity;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
