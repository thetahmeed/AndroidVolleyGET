1. Add on gradle.build (app)
implementation 'com.android.volley:volley:1.1.1'

2. Create a new java class called MySingleton.java
public class MySingleton {
    private static MySingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;

    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    
}

3. Add inside onCreate method in MainActivity.java
        
        ParseJsonData();

    }
    // making a request
    public void ParseJsonData(){

        String myUrl = "http://api.openweathermap.org/data/2.5/weather?q=Dhaka&appid=a25fb4ecfae2a25c9a7da72cbe18a0d8";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
            // to get data
            @Override
            public void onResponse(JSONObject response) {
               textView.setText(""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               textView.setText(""+error.getMessage());

            }
        })
        /*{
            // to send data
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return super.getParams();
            }
        }*/
        ;
        // passing the request to MySingleton
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

5. Add internet permission in Menifest.xml

 /*// to get an JsonArray
                        try {
                            // Array name will be here
                            JSONArray jsonArray = response.getJSONArray("weather");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject items = jsonArray.getJSONObject(i);
                                // object name will be here
                                String icon = items.getString("icon");
                                textView.setText(icon);

                            } // end of for loop
                        } catch (JSONException e) {
                            e.printStackTrace();

                        } // end of try catch*/

/*// to get data from JsonObject
                        try {
                            // object name will be here
                            JSONObject jsonObject = response.getJSONObject("main");
                            // key will be here
                            int temp = jsonObject.getInt("temp");
                            textView.setText(""+temp);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        } // end of try catch*/

