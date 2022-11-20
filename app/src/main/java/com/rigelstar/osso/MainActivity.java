package com.rigelstar.osso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String rentedBooks[] = {
                "Karnali Blues",
                "Arki Aaimaai"
        };

        BooksListItemAdapter blia = new BooksListItemAdapter(this, rentedBooks, new int[] {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background});
        ListView rentedBooksListView = findViewById(R.id.home_page_rented_books_list);
        rentedBooksListView.setAdapter(blia);
    }
}

class BooksListItemAdapter extends BaseAdapter
{
    private Context context;
    private String[] bookNames;
    private int[] bookImages;
    private LayoutInflater inflater;

    public BooksListItemAdapter(Context ctx, String[] bookNames, int[] bookImages)
    {
        this.context = ctx;
        this.bookNames = bookNames;
        this.bookImages = bookImages;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return bookImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.default_books_list_item, null);
        TextView bookName = view.findViewById(R.id.book_list_item_name);
        ImageView bookImage = view.findViewById(R.id.book_list_item_image);
        bookName.setText(bookNames[position]);
        bookImage.setImageResource(bookImages[position]);
        return view;
    }
}