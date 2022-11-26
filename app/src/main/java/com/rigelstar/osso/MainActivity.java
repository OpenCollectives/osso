package com.rigelstar.osso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.rigelstar.osso.ui.RecyclerViewSpacingItemDecorator;
import com.rigelstar.osso.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> allBooks = new ArrayList<Book>()
        {{
            add(new Book("Karnali Blues", "Buddhisagar"));
            add(new Book("Arki Aaimaai", "Nilam Karki"));
        }};

        List<Book> rentedBooks = new ArrayList<Book>()
        {{
            add(new Book("Priya Sufi", "Subin Bhattarai"));
        }};

        RecyclerView allBooksList = findViewById(R.id.home_page_all_books_list);
        DefaultBookListAdapter allBookListAdapter = new DefaultBookListAdapter(this, allBooks);
        allBooksList.addItemDecoration(new RecyclerViewSpacingItemDecorator(10, RecyclerViewSpacingItemDecorator.HORIZONTAL));
        allBooksList.setAdapter(allBookListAdapter);
        allBooksList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        if(rentedBooks.size() == 0)
        {
            LinearLayout noRentedBooksLayout = findViewById(R.id.home_page_no_rented_books_message_container);
            noRentedBooksLayout.setVisibility(View.VISIBLE);
        }

        ProgressBar allBooksProgressBar = findViewById(R.id.home_page_all_books_progress_bar);
        allBooksProgressBar.setVisibility(View.GONE);

        RecyclerView rentedBooksList = findViewById(R.id.home_page_rented_books_list);
        DefaultBookListAdapter rentedBookListAdapter = new DefaultBookListAdapter(this, rentedBooks);
        rentedBooksList.addItemDecoration(new RecyclerViewSpacingItemDecorator(10, RecyclerViewSpacingItemDecorator.HORIZONTAL));
        rentedBooksList.setAdapter(rentedBookListAdapter);
        rentedBooksList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ProgressBar rentedBooksProgressBar = findViewById(R.id.home_page_rented_books_progress_bar);
        rentedBooksProgressBar.setVisibility(View.GONE);
    }
}

class DefaultBookListAdapter extends RecyclerView.Adapter<DefaultBookListAdapter.BookViewHolder>
{
    private final List<Book> books;
    private final Context context;

    public DefaultBookListAdapter(Context context, List<Book> books)
    {
        this.books = books;
        this.context = context;
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