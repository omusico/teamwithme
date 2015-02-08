package me.teamwith.teamwithme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.*;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ParseObject userData;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        Log.i("Test", "testing");
        this.getParseData();
        //editText pass in context from the activity
        //link, email,

    }

    private void getParseData()
    {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//                query.whereEqualTo("username", "cupcake");
//               query.findInBackground(new FindCallback<ParseObject>() {
//                       public void done(List<ParseObject> objects, ParseException e) {
//                               if (e == null) {
//                                       Log.i("Profile", objects.get(0).getString("username"));
//                                  } else {
//                                     Log.wtf("Profile", e.getMessage());
//                                }
//                        }
//            });
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//        //equal to alex's entry
//        query.whereEqualTo("objectId", "");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    Log.i("Profile", objects.get(0).getString("username"));
//                    userData = objects.get(0);
//                } else {
//                    Log.wtf("Profile", e.getMessage());
//                }
//            }
//        });
//        Log.d("Profile", "testing");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
            query.whereEqualTo("username", "cupcake");
            query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                      if (e == null) {
                          ParseObject userSkill = new ParseObject("UserSkill");
                          userSkill.put("skillId", objects.get(0).get("skillId"));
                          userSkill.put("userId", objects.get(0).get("userId"));
                          userSkill.saveInBackground();
                             Log.i("Skill", objects.get(0).get("skillId").toString());

                          Log.i("User", objects.get(0).get("userId").toString());

                        } else {
                             Log.wtf("Profile", e.getMessage());
                       }
                      }
               });
        Log.i("Test2", "testing2");


        Log.i("Test3", "testing3");
//            this.editTextFields();





    }
//if checked make in database
// uncheck remove from database

    private void editTextFields()
    {
        EditText editText = (EditText) this.getActivity().findViewById(R.id.email);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    handled = true;
                }
                return handled;
            }
        });

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
        try {
            mListener = (Profile.OnFragmentInteractionListener) activity;
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
