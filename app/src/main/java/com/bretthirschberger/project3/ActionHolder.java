package com.bretthirschberger.project3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionHolder.ActionHolderListener} interface
 * to handle interaction events.
 * Use the {@link ActionHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionHolder extends Fragment implements View.OnDragListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView mHolders[];
//    private ImageView mHolder2;
//    private ImageView mHolder3;
//    private ImageView mHolder4;

    private Direction[] mDirections;

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
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDirections = new Direction[]{Direction.NONE, Direction.NONE, Direction.NONE, Direction.NONE};
        mHolders = new ImageView[4];
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_action_holder, container, false);
        mHolders[0] = v.findViewById(R.id.holder1);
        mHolders[1] = v.findViewById(R.id.holder2);
        mHolders[2] = v.findViewById(R.id.holder3);
        mHolders[3] = v.findViewById(R.id.holder4);
        mHolders[0].setOnDragListener((dragged, event) -> {
            int dragEvent = event.getAction();
            ImageView target = (ImageView) dragged;
            final ImageView view = (ImageView) event.getLocalState();
            if (dragEvent == DragEvent.ACTION_DROP) {
                target.setImageDrawable(view.getDrawable());
                Log.i("ID",dragged.getId()+"");
                Log.i("ID img",view.getId()+"");
                Log.i("ID UP",R.id.up+"");
                switch (view.getId()) {
                    case R.id.up:
                        mDirections[0] = Direction.UP;
                        break;
                    case R.id.down:
                        mDirections[0] = Direction.DOWN;
                        break;
                    case R.id.left:
                        mDirections[0] = Direction.LEFT;
                        break;
                    case R.id.right:
                        mDirections[0] = Direction.RIGHT;
                        break;
                    default:
                        mDirections[0] = Direction.NONE;
                        break;
                }
            }
            return true;
        });
        mHolders[1].setOnDragListener((dragged, event) -> {
            int dragEvent = event.getAction();
            ImageView target = (ImageView) dragged;

            final ImageView view = (ImageView) event.getLocalState();
            if (dragEvent == DragEvent.ACTION_DROP) {
                target.setImageDrawable(view.getDrawable());
                switch (view.getId()) {
                    case R.id.up:
                        mDirections[1] = Direction.UP;
                        break;
                    case R.id.down:
                        mDirections[1] = Direction.DOWN;
                        break;
                    case R.id.left:
                        mDirections[1] = Direction.LEFT;
                        break;
                    case R.id.right:
                        mDirections[1] = Direction.RIGHT;
                        break;
                    default:
                        mDirections[1] = Direction.NONE;
                        break;
                }
            }
            return true;
        });
        mHolders[2].setOnDragListener((dragged, event) -> {
            int dragEvent = event.getAction();
            ImageView target = (ImageView) dragged;

            final ImageView view = (ImageView) event.getLocalState();
            if (dragEvent == DragEvent.ACTION_DROP) {
                target.setImageDrawable(view.getDrawable());
                switch (view.getId()) {
                    case R.id.up:
                        mDirections[2] = Direction.UP;
                        break;
                    case R.id.down:
                        mDirections[2] = Direction.DOWN;
                        break;
                    case R.id.left:
                        mDirections[2] = Direction.LEFT;
                        break;
                    case R.id.right:
                        mDirections[2] = Direction.RIGHT;
                        break;
                    default:
                        mDirections[2] = Direction.NONE;
                        break;
                }
            }
            return true;
        });
        mHolders[3].setOnDragListener((dragged, event) -> {
            int dragEvent = event.getAction();
            ImageView target = (ImageView) dragged;

            final ImageView view = (ImageView) event.getLocalState();
            if (dragEvent == DragEvent.ACTION_DROP) {
                target.setImageDrawable(view.getDrawable());
                switch (view.getId()) {
                    case R.id.up:
                        mDirections[3] = Direction.UP;
                        break;
                    case R.id.down:
                        mDirections[3] = Direction.DOWN;
                        break;
                    case R.id.left:
                        mDirections[3] = Direction.LEFT;
                        break;
                    case R.id.right:
                        mDirections[3] = Direction.RIGHT;
                        break;
                    default:
                        mDirections[1] = Direction.NONE;
                        break;
                }
            }
            return true;
        });
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


    public Direction[] getDirections() {
        return mDirections;
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
