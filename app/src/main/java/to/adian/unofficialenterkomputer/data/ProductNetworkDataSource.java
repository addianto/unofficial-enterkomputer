package to.adian.unofficialenterkomputer.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProductNetworkDataSource {

    private static final String TAG = ProductNetworkDataSource.class.getName();
    private static ProductNetworkDataSource instance;
    private Context context;

    private ProductNetworkDataSource(Context context) {
        this.context = context;
    }

    public static ProductNetworkDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new ProductNetworkDataSource(context);
            Log.d(TAG, "Created product network data source for the 1st time");
        }

        return instance;
    }

    public LiveData<List<Product>> getProductsFromEndpoint(String endpoint) {
        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        List<Product> _products = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(endpoint,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            int id = Integer.parseInt(obj.getString("id"));
                            String name = obj.getString("name");
                            int price = Integer.parseInt(obj.getString("price"));
                            Product product = new Product(id, name, price);
                            Log.d(TAG, "Parsed: " + product.getName());
                            _products.add(new Product(id, name, price));
                        }
                    } catch (JSONException ex) {
                        Log.e(TAG, "Failed to parse JSON", ex);
                        Log.v(TAG, "response: " + response);
                    }
                    products.postValue(_products);
                },
                error -> {
                    Log.e(TAG, "Something wrong with the network", error);
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
        queue.start();

        return products;
    }
}
