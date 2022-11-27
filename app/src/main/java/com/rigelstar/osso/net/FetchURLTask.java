package com.rigelstar.osso.net;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchURLTask<R> extends AsyncTask<String, Void, R>
{
    private final FetchURLTaskPostExecution<R> postExecution;
    private final Converter<R> converter;

    public FetchURLTask(FetchURLTaskPostExecution<R> postExecution, Converter<R> converter)
    {
        super();
        this.postExecution = postExecution;
        this.converter = converter;
    }

    @Override
    protected R doInBackground(String... strings) {
        String strData = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(strings[0]).openConnection();
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            InputStream stream = connection.getInputStream();
            int contentLength = Math.max(connection.getContentLength(), 0);
            byte[] data = new byte[contentLength];
            if(stream.read(data, 0, contentLength) != contentLength)
            {
                Log.d("DEBUG", "Could not read full data from URL: '" + strings[0] + "'");
            }
            else
            {
                strData = new String(data);
            }
            stream.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        if(converter == null)
        {
            return (R) strData;
        }
        return converter.convert(strData);
    }

    @Override
    protected void onPostExecute(R rs) {
        super.onPostExecute(rs);
        if(this.postExecution != null)
            postExecution.execute(rs);
    }

    public interface FetchURLTaskPostExecution<T>
    {
        void execute(T arg);
    }

    public interface Converter<R>
    {
        /**
         * Convert string to given type.
         * @param data String data
         * @return String data converted to given type
         * **/
        R convert(String data);
    }

    public static class StringToJSONObjectConverter implements Converter<JSONObject>
    {
        @Override
        public JSONObject convert(String data) {
            try {
                return new JSONObject(data);
            }
            catch(JSONException je)
            {
                je.printStackTrace();
                Log.d("DEBUG", je.getMessage());
            }
            return null;
        }
    }
}