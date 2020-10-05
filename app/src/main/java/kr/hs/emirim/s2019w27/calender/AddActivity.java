package kr.hs.emirim.s2019w27.calender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import kr.hs.emirim.s2019w27.calender.DB.AppDatabase;
import kr.hs.emirim.s2019w27.calender.DB.Memo;

public class AddActivity extends AppCompatActivity {
    final int REQ_CODE_SELECT_IMAGE = 100;

    private Spinner categorySpinner;
    private EditText titleEditText;
    private EditText memoEditText;
    private Button deleteButton;
    private Button saveButton;
    private ImageView addImage;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "memo-db").allowMainThreadQueries().build();

        // 저장되어있는 값 불러오기
        categorySpinner.setSelection(db.memoDAO().getCategory());
        titleEditText.setText(db.memoDAO().getTitle());
        memoEditText.setText(db.memoDAO().getMemo());

        // 날짜 받아오기
        Intent intent = getIntent();
        final String date = intent.getExtras().getString("date");

        // 뒤로가기 버튼
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 저장 버튼
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categorySpinner.getSelectedItemPosition() != 0) {
                    Toast.makeText(AddActivity.this, "카테고리를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (!titleEditText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (!memoEditText.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "메모를 입력하세요", Toast.LENGTH_SHORT).show();
                }

                db.memoDAO().insert(new Memo(date, categorySpinner.getSelectedItemPosition(), titleEditText.getText().toString(), memoEditText.getText().toString()));
                categorySpinner.setSelection(db.memoDAO().getCategory());
                titleEditText.setText(db.memoDAO().getTitle());
                memoEditText.setText(db.memoDAO().getMemo() + "\n\n" + db.memoDAO().getDate());
                Toast.makeText(AddActivity.this, "저장되었습니다", Toast.LENGTH_SHORT).show();

            }
        });

        // 삭제 버튼
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.memoDAO().deleteAll();    // delete 수정하세영
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
        Toast.makeText(getBaseContext(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    // 이미지 데이터를 비트맵으로 받아옴
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    // 배치해놓은 ImageView에 set
                    addImage.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getImageNameToUri(Uri data) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }

}