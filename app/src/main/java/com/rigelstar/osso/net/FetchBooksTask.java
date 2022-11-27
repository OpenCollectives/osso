package com.rigelstar.osso.net;

import android.util.Log;

import com.rigelstar.osso.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchBooksTask extends FetchURLTask<List<Book>>
{
    public FetchBooksTask(FetchURLTaskPostExecution<List<Book>> postExecution, Converter<List<Book>> converter) {
        super(postExecution, converter);
    }

    public static class StringToBookListConverter implements Converter<List<Book>>
    {
        @Override
        public List<Book> convert(String data) {
            List<Book> books = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(new String(data));
                for(int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = (JSONObject) array.get(i);
                    String bookName = (String) jsonObject.get("name");
                    String bookAuthor = (String) jsonObject.get("author");
                    books.add(new Book(bookName, bookAuthor));
                }
            }
            catch(JSONException je)
            {
                je.printStackTrace();
                Log.d("DEBUG", je.getMessage());
                Log.d("DEBUG", je.getLocalizedMessage());
            }
            return books;
        }
    }
}
