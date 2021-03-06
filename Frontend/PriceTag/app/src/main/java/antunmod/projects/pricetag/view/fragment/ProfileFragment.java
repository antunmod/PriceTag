package antunmod.projects.pricetag.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.service.UtilService;
import antunmod.projects.pricetag.transfer.UserInformation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    UserInformation userInformation;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        // Set values
        if (bundle != null) {
            userInformation = (UserInformation) bundle.getSerializable("userInformation");
        }
    }

    View inflatedView;
    TextView textView_name;
    TextView textView_email;
    TextView textView_points;
    TextView textView_rating;
    TextView textView_userType;
    TextView textView_signupDate;
    TextView textView_feedbacksGiven;
    TextView textView_feedbacksReceived;



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);

        textView_name = inflatedView.findViewById(R.id.textView_name);
        textView_email = inflatedView.findViewById(R.id.textView_email);
        textView_points = inflatedView.findViewById(R.id.textView_points);
        textView_rating = inflatedView.findViewById(R.id.textView_rating);
        textView_userType = inflatedView.findViewById(R.id.textView_user_type);
        textView_signupDate = inflatedView.findViewById(R.id.textView_signup_date);
        textView_feedbacksGiven = inflatedView.findViewById(R.id.textView_feedbacks_given);
        textView_feedbacksReceived = inflatedView.findViewById(R.id.textView_feedbacks_received);


        textView_name.setText(userInformation.getName());
        textView_email.setText(userInformation.getEmail());
        textView_points.setText(userInformation.getPoints().toString());
        textView_rating.setText(userInformation.getInformationValidity());
        textView_userType.setText(userInformation.getDescription());
        textView_signupDate.setText(userInformation.getSignupDate());
        textView_feedbacksGiven.setText(userInformation.getFeedbacksGiven().toString());
        textView_feedbacksReceived.setText(userInformation.getFeedbacksReceived().toString());

        UtilService.hideKeyboard(getActivity());

        return inflatedView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
