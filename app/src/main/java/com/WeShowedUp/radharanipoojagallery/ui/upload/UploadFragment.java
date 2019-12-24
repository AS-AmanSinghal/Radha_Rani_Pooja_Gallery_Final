package com.WeShowedUp.radharanipoojagallery.ui.upload;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.UploadResponse.UploadResponse;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment
{
    private static int PReqcode = 1;
    private EditText description, amount;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload, container, false);
        description=root.findViewById(R.id.upload_description);
        amount=root.findViewById(R.id.upload_amount);
        ProfileUpload=root.findViewById(R.id.upload_image);
        button=root.findViewById(R.id.upload_btn);
        return root;
    }

    private ImageView ProfileUpload;
    private Button button;
    private MultipartBody.Part image;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProfileUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT>=22) {
                    checkResequestForPermission();
                } else {
                    openGallery();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (image == null) {
                    Snackbar.make(Objects.requireNonNull(getView()), "Upload Image", Snackbar.LENGTH_SHORT).show();
                } else {
                    //System.out.println(image+" "+getActivity().getIntent().getStringExtra("phone")+" "+description.getText()+" "+amount.getText());
                    new Upload(image, String.valueOf(description.getText()), String.valueOf(amount.getText())).execute();
                }
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Editcheck();
            }
        };

        description.addTextChangedListener(textWatcher);
        amount.addTextChangedListener(textWatcher);
        Editcheck();
    }

    private void Editcheck() {
        if (String.valueOf(description.getText()).isEmpty() || String.valueOf(amount.getText()).isEmpty()) {
            button.setBackgroundColor(Color.GRAY);
            button.setEnabled(false);
        } else {
            button.setBackgroundColor(Color.parseColor("#14CC9C"));
            button.setEnabled(true);
        }
    }

    private void openGallery()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), PReqcode);
    }

    private void checkResequestForPermission()
    {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
        !=PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqcode);
                Snackbar.make(Objects.requireNonNull(getView()), "Accept Request for Uploading Image", Snackbar.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqcode);
                Snackbar.make(Objects.requireNonNull(getView()), "Accept Request for Uploading Image", Snackbar.LENGTH_SHORT).show();
            }
        }
        else
        {
            openGallery();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PReqcode && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            File imageFIle = new File(String.valueOf(imageUri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFIle);
            image = MultipartBody.Part.createFormData("image", imageFIle.getName(), requestBody);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContext()).getContentResolver(), imageUri);
                ProfileUpload.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class Upload extends AsyncTask<Void, Void, Void> {
        MultipartBody.Part image;
        String caption, amount2;

        Upload(MultipartBody.Part image, String caption, String amount) {
            this.image = image;
            this.caption = caption;
            this.amount2 = amount;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                RetrofitClass retrofitClass = new RetrofitClass();
                Call<UploadResponse> call = retrofitClass.retrofit().upload(image, getActivity().getIntent().getStringExtra("phone"), caption, amount2);
                call.enqueue(new Callback<UploadResponse>() {
                    @Override
                    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                        if (response.body() != null) {
                            Snackbar.make(getView(), "Upload Successfully", Snackbar.LENGTH_SHORT).show();
                            description.setText("");
                            amount.setText("");
                            ProfileUpload.setImageResource(R.drawable.ic_upload);

                        }
                    }

                    @Override
                    public void onFailure(Call<UploadResponse> call, Throwable t) {
                        Snackbar.make(getView(), "Try Again", Snackbar.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
