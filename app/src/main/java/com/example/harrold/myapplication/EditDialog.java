package com.example.harrold.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Harrold on 2018-02-04.
 */

public class EditDialog extends AppCompatDialogFragment {

    private EditText editTextName3;
    private EditText editTextDate3;
    private EditText editTextCost3;
    private EditText editTextComment3;

    private EditDialogListener listener2;

    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_dialog_layout, null);

        //Bundle bundle = getIntent().getExtras();

        builder.setView(view)
                .setTitle("Edit Subscription")
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //remove item at i from arraylist


                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String subName = editTextName3.getText().toString();
                        String subDate = editTextDate3.getText().toString();
                        String subCost = editTextCost3.getText().toString();
                        String subComment = editTextComment3.getText().toString();
                        listener2.applyEdit(subName, subDate, subCost, subComment);
                    }
                });

        editTextName3 = view.findViewById(R.id.name_edit_text);
        editTextDate3 = view.findViewById(R.id.date_edit_text);
        editTextCost3 = view.findViewById(R.id.cost_edit_text);
        editTextComment3 = view.findViewById(R.id.comment_edit_text);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener2 = (EditDialog.EditDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must Implement Example Dialoge Listener");
        }
    }

    public interface EditDialogListener {
        void applyEdit(String subName, String subDate, String subCost, String subComment);
    }
}
