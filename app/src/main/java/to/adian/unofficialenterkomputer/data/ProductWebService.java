package to.adian.unofficialenterkomputer.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import to.adian.unofficialenterkomputer.model.Product;

public class ProductWebService implements ProductRemoteDataSource {

    private static final String TAG = ProductWebService.class.getName();
    private Context context;

    public ProductWebService(Context context) {
        this.context = context;
    }

    @Override
    public MutableLiveData<List<Product>> getProductsByEndpoint(String url) {
        MutableLiveData<List<Product>> productsData = new MutableLiveData<>();
        JsonArrayRequest request = new JsonArrayRequest(url,
                response -> {
                    List<Product> products = Collections.emptyList();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            int id = Integer.valueOf(obj.getString("id"));
                            String name = obj.getString("name");
                            int price = Integer.valueOf(obj.getString("price"));
                            String category = obj.getString("category_description");

                            Product product = new Product(id, name, price, category);
                            products.add(product);
                        } catch (JSONException ex) {
                            Log.e(TAG, "Failed converting JSON data to Java object", ex);
                        }
                    }
                    productsData.setValue(products);
                },
                error -> Log.e(TAG, "Error while making request", error));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
        queue.start();

        return productsData;
    }
}
