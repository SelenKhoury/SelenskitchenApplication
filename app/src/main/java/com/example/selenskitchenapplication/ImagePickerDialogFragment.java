package com.example.selenskitchenapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ImagePickerDialogFragment extends DialogFragment {

    private OnImagePickerListener imagePickerListener;

    public void setOnImagePickerListener(OnImagePickerListener listener) {
        imagePickerListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Option")
                .setItems(new CharSequence[]{"Take Photo", "Choose from Gallery"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (imagePickerListener != null) {
                                    imagePickerListener.onCameraOptionSelected();
                                }
                                break;
                            case 1:
                                if (imagePickerListener != null) {
                                    imagePickerListener.onGalleryOptionSelected();
                                }
                                break;
                        }
                    }
                });

        return builder.create();
    }
}
