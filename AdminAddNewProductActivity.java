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

public class AdminAddNewProductActivity extends AppCompatActivity {
    private String CategoryName, Description, Price, ProductName, SaveCurrentDate, SaveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
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

        CategoryName = getIntent().getExtras().get("Category").toString();
        ProductImagesReference = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsReference = FirebaseDatabase.getInstance().getReference().child("Products");


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
                OpenGallery();
            }

        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });

    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }

    private void ValidateProductData()
    {
       Description = InputProductDescription.getText().toString();
       Price = InputProductPrice.getText().toString();
       ProductName = InputProductName.getText().toString();

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
    }


    private void StoreProductInformation()
    {
        LoadingBar.setTitle("Adding new book");
        LoadingBar.setMessage("{Dear Admin, Please wait, while we are adding the new product.");
        LoadingBar.setCanceledOnTouchOutside(false);
        LoadingBar.show();

        Calendar Calendar1 = Calendar.getInstance() ;

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY");
        SaveCurrentDate = currentDate.format(Calendar1.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:MM:ss a");
        SaveCurrentTime = currentTime.format(Calendar1.getTime());


        ProductRandomKey = SaveCurrentDate + SaveCurrentTime;

        StorageReference FilePath = ProductImagesReference.child(ImageUri.getLastPathSegment() + ProductRandomKey + ".jpg");

        final UploadTask UploadTaskk = FilePath.putFile(ImageUri);

        UploadTaskk.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String Message = e.toString();
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
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        DownloadImageURL = FilePath.getDownloadUrl().toString();
                        return FilePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            DownloadImageURL = task.getResult().toString();
                            Toast.makeText(AdminAddNewProductActivity.this, "Getting Product image Url successfully..", Toast.LENGTH_SHORT).show();

                            SaveProductInfoDatabase();
                        }

                    }


                });
            }
        });

    }

    private void SaveProductInfoDatabase()
    {
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
                if(task.isSuccessful())
                {
                    Intent Intentt = new Intent(AdminAddNewProductActivity.this, AdminCategoryActivity.class);
                    startActivity(Intentt);

                    LoadingBar.dismiss();
                    Toast.makeText(AdminAddNewProductActivity.this, "Book is added successfully...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoadingBar.dismiss();
                    String Message = task.getException().toString();
                    Toast.makeText(AdminAddNewProductActivity.this, "Error: " + Message, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}