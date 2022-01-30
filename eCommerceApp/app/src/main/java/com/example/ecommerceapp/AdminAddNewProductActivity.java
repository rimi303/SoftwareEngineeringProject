package com.example.ecommerceapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.app.ProgressDialog;
//import android.net.Uri;
//import android.widget.Toast;

//import com.google.android.gms.tasks.Continuation;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.HashMap;


/**
 * A simple {@link AppCompatActivity} subclass.
 * Activities that contain this AppCompatActivity must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to
 * create an instance of this appcompatactivity.
 */


public class AdminAddNewProductActivity extends AppCompatActivity {
    private String CategoryName, Description, Price, ProductName, SaveCurrentDate, SaveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;

    //set GallaerPick as true = 1
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String ProductRandomKey, DownloadImageURL;
    private StorageReference ProductImagesReference;
    private DatabaseReference ProductsReference;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        //initializing CategoryName, ProductImagesReference, ProductsReference
        CategoryName = getIntent().getExtras().get("Category").toString();
        ProductImagesReference = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsReference = FirebaseDatabase.getInstance().getReference().child("Products");

        //initializing AddNewProductButton,  InputProductImage, InputProductName,  InputProductDescription,  InputProductPrice,  LoadingBar
        AddNewProductButton = findViewById(R.id.add_new_product);
        InputProductImage = findViewById(R.id.select_product_image);
        InputProductName = findViewById(R.id.product_name);
        InputProductDescription = findViewById(R.id.product_description);
        InputProductPrice = findViewById(R.id.product_price);
        LoadingBar = new ProgressDialog(this);


        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //User-define function calling1
                OpenGallery();
            }

        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User-define function calling2
                ValidateProductData();
            }
        });

    }

    /**
     * Use this factory method to create a new instance of
     * this method not using the provided parameters.
     *
     * @return nothing any instance of method OpenGallery.
     */

    private void OpenGallery()
    {
        //new intent object GalleryIntent creation
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if condition start here

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }


    /**
     * Use this factory method to create a new instance of
     * this method not using the provided parameters.
     *
     * @return nothing any instance of method ValidateProductData.
     */
    private void ValidateProductData()
    {
       Description = InputProductDescription.getText().toString();
       Price = InputProductPrice.getText().toString();
       ProductName = InputProductName.getText().toString();

       //if-else condition start here
       if(ImageUri == null)
       {
           Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
       }
       else if(TextUtils.isEmpty(Description))
       {
           Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
       }
       else if(TextUtils.isEmpty(Price))
       {
           Toast.makeText(this, "Please write product price...", Toast.LENGTH_SHORT).show();
       }
       else if(TextUtils.isEmpty(ProductName))
       {
           Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
       }
       else
       {
           StoreProductInformation();
       }
        //if-else condition start here
    }

    /**
     * Use this factory method to create a new instance of
     * this method not using the provided parameters.
     *
     * @return nothing any instance of method StoreProductInformation.
     */
    private void StoreProductInformation()
    {
        LoadingBar.setTitle("Adding new book");
        LoadingBar.setMessage("{Dear Admin, Please wait, while we are adding the new product.");
        LoadingBar.setCanceledOnTouchOutside(false);
        LoadingBar.show();

        //Calender object creation and set time and 24-hours date format
        Calendar Calendar1 = Calendar.getInstance() ;

        SimpleDateFormat currentDate = new SimpleDateFormat(" MMM dd, YYYY ");
        SaveCurrentDate = currentDate.format(Calendar1.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat(" HH:MM:ss a ");
        SaveCurrentTime = currentTime.format(Calendar1.getTime());


        ProductRandomKey = SaveCurrentDate + SaveCurrentTime;

        StorageReference FilePath = ProductImagesReference.child(ImageUri.getLastPathSegment() + ProductRandomKey + ".jpg");

        final UploadTask UploadTaskk = FilePath.putFile(ImageUri);

        UploadTaskk.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String Message = e.toString();

                //dismiss loadingBar and Toast creation
                Toast.makeText(AdminAddNewProductActivity.this, "Error: " + Message, Toast.LENGTH_SHORT).show();
                LoadingBar.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddNewProductActivity.this, "Book Image uploaded successfully..", Toast.LENGTH_SHORT).show();

                Task<Uri> UrlTask = UploadTaskk.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        //codition start
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        //image url downloading and return filePath
                        DownloadImageURL = FilePath.getDownloadUrl().toString();
                        return FilePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            DownloadImageURL = task.getResult().toString();
                            //Toast creation
                            Toast.makeText(AdminAddNewProductActivity.this, "Getting Product image Url successfully..", Toast.LENGTH_SHORT).show();

                            //User-define function calling
                            SaveProductInfoDatabase();
                        }

                    }


                });
            }
        });

    }

    /**
     * Use this factory method to create a new instance of
     * this method not using the provided parameters.
     *
     * @return nothing any instance of method aveProductInfoDatabase.
     */

    private void SaveProductInfoDatabase()
    {
        //Put value using map
        HashMap<String, Object> ProductMap = new HashMap<>();
        ProductMap.put("ProductId", ProductRandomKey);
        ProductMap.put("date", SaveCurrentDate);
        ProductMap.put("time", SaveCurrentTime);
        ProductMap.put("description", Description);
        ProductMap.put("image", DownloadImageURL);
        ProductMap.put("category", CategoryName);
        ProductMap.put("price", Price);
        ProductMap.put("ProductName", ProductName);

        ProductsReference.child(ProductRandomKey).updateChildren(ProductMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //if-else condition start here
                if(task.isSuccessful())
                {
                    //Intent object creation9
                    Intent Intentt = new Intent(AdminAddNewProductActivity.this, AdminCategoryActivity.class);
                    startActivity(Intentt);

                    //dismiss loadingBar and Toast creation
                    LoadingBar.dismiss();
                    Toast.makeText(AdminAddNewProductActivity.this, "Book is added successfully...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //dismiss loadingBar
                    LoadingBar.dismiss();

                    String Message = task.getException().toString();
                    //Toast creation
                    Toast.makeText(AdminAddNewProductActivity.this, "Error: " + Message, Toast.LENGTH_SHORT).show();
                }
                //if-else condition end here

            }
        });
    }
}