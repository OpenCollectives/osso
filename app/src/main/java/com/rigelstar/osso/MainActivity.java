package com.rigelstar.osso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rigelstar.osso.net.FetchBooksTask;
import com.rigelstar.osso.ui.RecyclerViewSpacingItemDecorator;
import com.rigelstar.osso.model.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView allBooksList;
    private RecyclerView rentedBooksList;

    private ProgressBar allBooksProgressBar;
    private ProgressBar rentedBooksProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allBooksList = findViewById(R.id.home_page_all_books_list);
        allBooksList.addItemDecoration(new RecyclerViewSpacingItemDecorator(10, RecyclerViewSpacingItemDecorator.HORIZONTAL));
        allBooksList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        allBooksProgressBar = findViewById(R.id.home_page_all_books_progress_bar);

        rentedBooksList = findViewById(R.id.home_page_rented_books_list);
        rentedBooksList.addItemDecoration(new RecyclerViewSpacingItemDecorator(10, RecyclerViewSpacingItemDecorator.HORIZONTAL));
        rentedBooksList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rentedBooksProgressBar = findViewById(R.id.home_page_rented_books_progress_bar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rentedBooksProgressBar.setVisibility(View.VISIBLE);
        allBooksProgressBar.setVisibility(View.VISIBLE);

        FetchBooksTask fetchAllBooksTask = new FetchBooksTask((books) -> {
            allBooksList.setAdapter(new DefaultBookListAdapter(books));
            allBooksProgressBar.setVisibility(View.GONE);
        }, new FetchBooksTask.StringToBookListConverter());
        fetchAllBooksTask.execute("http://10.0.2.2:5000/books?&access_token=12&content_type=json");

        FetchBooksTask fetchRentedBooksTask = new FetchBooksTask((books) -> {
            rentedBooksList.setAdapter(new DefaultBookListAdapter((books)));
            rentedBooksProgressBar.setVisibility(View.GONE);

            if(books.size() == 0)
            {
                LinearLayout noRentedBooksMessage = findViewById(R.id.home_page_no_rented_books_message_container);
                noRentedBooksMessage.setVisibility(View.VISIBLE);
            }
        }, new FetchBooksTask.StringToBookListConverter());
        fetchRentedBooksTask.execute("http://10.0.2.2:5000/rented-books?user_id=6380869d6fca88af4832b231&access_token=12&content_type=json");
    }
}

class DefaultBookListAdapter extends RecyclerView.Adapter<DefaultBookListAdapter.BookViewHolder>
{
    private final List<Book> books;

    public DefaultBookListAdapter(List<Book> books)
    {
        this.books = books;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView bookImage;
        public TextView bookName, bookAuthor;

        public BookViewHolder(View view)
        {
            super(view);
            this.bookImage = view.findViewById(R.id.book_list_item_image);
            this.bookName = view.findViewById(R.id.book_list_item_name);
            this.bookAuthor = view.findViewById(R.id.book_list_item_author);
        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_books_list_item, null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = this.books.get(position);
        holder.bookName.setText(book.getName());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookImage.setImageResource(R.drawable.open_book_64);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }
}