package kr.hs.emirim.s2019w27.calender;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.loader.content.CursorLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;
import kr.hs.emirim.s2019w27.calender.DB.Memo;

public class AddActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    final int REQ_CODE_SELECT_IMAGE = 1;

    private Uri uri;
    private Spinner categorySpinner;
    private EditText titleEditText;
    private EditText memoEditText;
    private Button deleteButton;
    private Button saveButton;
    private ImageView addImage;
    private ImageView backButton;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 날짜 받아오기
        Intent intent = getIntent();
        final String date = intent.getExtras().getString("Date");
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        categorySpinner = findViewById(R.id.categorySpinner);
        titleEditText = findViewById(R.id.titleEditText);
        memoEditText = findViewById(R.id.memoEditText);
        deleteButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);
        addImage = findViewById(R.id.add_img_btn);
        backButton = findViewById(R.id.backButton);

        final BitmapDrawable basicImg = (BitmapDrawable)getResources().getDrawable(R.drawable.add_img);

        final AppDatabase db = AppDatabase.getInstance(this);

        // 갤러리 접근 권한
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        // 저장되어있는 값 불러오기
        categorySpinner.setSelection(db.memoDAO().getCategory(date));
        titleEditText.setText(db.memoDAO().getTitle(date));
        memoEditText.setText(db.memoDAO().getMemo(date));
        if (db.memoDAO().getUri(date) != null) {
            setImage(Uri.parse(db.memoDAO().getUri(date)));
        } else {
            addImage.setImageDrawable(basicImg);
        }

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 저장 버튼
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categorySpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(AddActivity.this, "카테고리를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (titleEditText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (memoEditText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "메모를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (uri == null) {
                    Toast.makeText(AddActivity.this, "사진을 첨부하세요", Toast.LENGTH_SHORT).show();
                } else {
                    if(db.memoDAO().getTitle(date) == null) {
                        db.memoDAO().insert(new Memo(date, categorySpinner.getSelectedItemPosition(), titleEditText.getText().toString(), memoEditText.getText().toString(), uri.toString()));
                        categorySpinner.setSelection(db.memoDAO().getCategory(date));
                        titleEditText.setText(db.memoDAO().getTitle(date));
                        memoEditText.setText(db.memoDAO().getMemo(date));
                        Toast.makeText(AddActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        if(uri != null && !uri.equals(db.memoDAO().getUri(date))) {
                            db.memoDAO().updateModify(categorySpinner.getSelectedItemPosition(), titleEditText.getText().toString(), memoEditText.getText().toString(), date);
                            db.memoDAO().updateUri(uri.toString(), date);
                        } else {
                            db.memoDAO().updateModify(categorySpinner.getSelectedItemPosition(), titleEditText.getText().toString(), memoEditText.getText().toString(), date);
                        }
                        categorySpinner.setSelection(db.memoDAO().getCategory(date));
                        titleEditText.setText(db.memoDAO().getTitle(date));
                        memoEditText.setText(db.memoDAO().getMemo(date));
                        Toast.makeText(AddActivity.this, "수정되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // 삭제 버튼
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.memoDAO().delete(date);
                categorySpinner.setSelection(0);
                titleEditText.setText("");
                memoEditText.setText("");
                addImage.setImageDrawable(basicImg);
                Toast.makeText(AddActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                
            }
        });

        // 이미지 추가 버튼
       addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // ACTION_PICK 연결로 갤러리 불러오기
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    uri = data.getData();
                    setImage(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String getImagePathFromURI(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this, data, proj,null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imgPath = cursor.getString(column_index);

        return imgPath;
    }

    private void setImage(Uri uri) {
        try {
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            String imgPath = getImagePathFromURI(uri);
            int degree = getExifOrientation(imgPath);
            bitmap = getRotatedBitmap(bitmap, degree);

            addImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getExifOrientation(String filePath) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                }
            }
        }
        return 0;
    }

    private Bitmap getRotatedBitmap(Bitmap bitmap, int degree) {
        if (degree != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap != tmpBitmap) {
                    bitmap.recycle();
                    bitmap = tmpBitmap;
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}