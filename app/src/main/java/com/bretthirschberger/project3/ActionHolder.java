package com.bretthirschberger.project3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionHolder.ActionHolderListener} interface
 * to handle interaction events.
 * Use the {@link ActionHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionHolder extends Fragment implements View.OnDragListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView mHolder1;
    private ImageView mHolder2;
    private ImageView mHolder3;
    private ImageView mHolder4;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ActionHolderListener mListener;

    public ActionHolder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionHolder.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionHolder newInstance(String param1, String param2) {
        ActionHolder fragment = new ActionHolder();
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
        View.OnDragListener onDragListener=(v, event) -> {
            int dragEvent = event.getAction();
            ImageView target = (ImageView)v;

            final ImageView view = (ImageView) event.getLocalState();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
//                    Toast.makeText(, "Entered", +Toast.LENGTH_SHORT).show();
//                    v.setBackgroundResource(R.drawable.ic_launcher_background);
//                    v.setBackground(view.getBackground());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
//                    v.setBackgroundResource(R.drawable.common_google_signin_btn_icon_disabled);

                    break;
                case DragEvent.ACTION_DROP:
//                    Toast.makeText(v.getApplicationContext(), "Dropped", Toast.LENGTH_SHORT).show();
                    target.setImageDrawable(view.getDrawable());
                    break;
            }
            return true;
        };
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_action_holder, container, false);
        mHolder1=v.findViewById(R.id.holder1);
        mHolder2=v.findViewById(R.id.holder2);
        mHolder3=v.findViewById(R.id.holder3);
        mHolder4=v.findViewById(R.id.holder4);
        mHolder1.setOnDragListener(onDragListener);
        mHolder2.setOnDragListener(onDragListener);
        mHolder3.setOnDragListener(onDragListener);
        mHolder4.setOnDragListener(onDragListener);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActionHolderListener) {
            mListener = (ActionHolderListener) context;
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

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
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
    public interface ActionHolderListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
