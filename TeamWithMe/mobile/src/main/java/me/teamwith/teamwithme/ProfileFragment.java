package me.teamwith.teamwithme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.*;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final String ARG_USER_ID = "userId";

    private String mUserId;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userId The ID of the user that is signed in.
     * @return A new instance of fragment Profile.
     */
    public static ProfileFragment newInstance(String userId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mUserId = getArguments().getString(ARG_USER_ID);
        }

    }

    private void setupSkillTicks(final Activity myActivity) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Skill");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    LinearLayout skillLayout = (LinearLayout) myActivity.findViewById(R.id.skillLayout);

                    for (int i = 0; i < parseObjects.size(); i++) {
                        CheckBox box = new CheckBox(myActivity.getApplicationContext());
                        box.setText(parseObjects.get(i).getString("name"));
                        skillLayout.addView(box);
                    }
                } else {
                    Log.wtf("ProfileFragment", "No skills found :(");
                }
            }
        });
    }

    private void getParseData()
    {
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Skill");
        //shitty fix
        for (int i = 0; i < 28; i++ )
        {

        }
        query.whereEqualTo("name", "Go");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> skillList, ParseException e) {
                if (e == null) {
                    String skillID = skillList.get(0).getObjectId();
                    ParseObject userSkill = new ParseObject("UserSkill");
                    userSkill.put("userId", "xKYreGKk3X");
                    userSkill.put("skillId", skillID);
                    userSkill.saveEventually();
                    Log.i("PF", "Saved (hopefully)");
                } else {
                    Log.wtf("PF", "Oh no :(");
                }
            }
        });
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
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

        this.setupSkillTicks(activity);

        try {
            mListener = (ProfileFragment.OnFragmentInteractionListener) activity;
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
        public void onFragmentInteraction(Uri uri);
    }

}
