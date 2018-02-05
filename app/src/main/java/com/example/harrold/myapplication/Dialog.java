package com.example.harrold.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Harrold on 2018-02-04.
 *
 * derived from https://www.youtube.com/watch?v=ARezg1D9Zd0
 */

public class Dialog extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextCost;
    private EditText editTextComment;

    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // this grabs the layout from an xml and puts into view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog_layout, null);

        // takes the View inflated above and shows on dialog box
        builder.setView(view)
                .setTitle("Add Subscription")
                .setMessage("Save blank to cancel.")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String subName = editTextName.getText().toString();
                        String subDate = editTextDate.getText().toString();
                        String subCost = editTextCost.getText().toString();
                        String subComment = editTextComment.getText().toString();
                        listener.applyText(subName, subDate, subCost, subComment);
                    }
                });

        editTextName = view.findViewById(R.id.name_edit_text);
        editTextDate = view.findViewById(R.id.date_edit_text);
        editTextCost = view.findViewById(R.id.cost_edit_text);
        editTextComment = view.findViewById(R.id.comment_edit_text);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must Implement Example Dialoge Listener");
        }
    }

    public interface DialogListener {
        void applyText(String subName, String subDate, String subCost, String subComment);
    }
}
