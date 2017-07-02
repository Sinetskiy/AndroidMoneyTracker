package moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.api.AddResult;
import moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.api.Result;
import moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.api.LSApi;
import static android.app.Activity.RESULT_OK;
import static moneytrackerjune17.loftschool.com.loftschoolmoneytrackerjune17.AddItemActivity.RC_ADD_ITEM;

/**
 * Created by andreysinetskiy on 29.06.17.
 */

public class ItemsFragment extends Fragment {

    private static final int LOADER_ITEMS = 0;
    private static final int LOADER_ADD = 1;
    private static final int LOADER_REMOVE = 2;

    public static final String ARG_TYPE = "type";
    private ItemsAdapter adapter = new ItemsAdapter();

    private String type;
    private LSApi api;
    private View add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView items = (RecyclerView) view.findViewById(R.id.items);
        items.setAdapter(adapter);
        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddItemActivity.class);
                intent.putExtra(AddItemActivity.EXTRA_TYPE , type);
                startActivityForResult(intent, AddItemActivity.RC_ADD_ITEM);
            }
        });

        type = getArguments().getString(ARG_TYPE);
        api = ((LSApp) getActivity().getApplication()).api();

        loadItems();
    }


    private void loadItems() {

        getLoaderManager().initLoader(LOADER_ITEMS, null, new LoaderManager.LoaderCallbacks<List<Item>>() {

            @Override
            public Loader<List<Item>> onCreateLoader(int id, Bundle args) {

                return new AsyncTaskLoader<List<Item>>(getContext()) {
                    @Override
                    public List<Item> loadInBackground() {
                        try {
                            return api.items(type).execute().body();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {
                if (data == null) {
                    Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                } else {
                    adapter.clear();
                    adapter.addAll(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<List<Item>> loader) {
            }
        }).forceLoad();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_ADD_ITEM && resultCode == RESULT_OK){
            Item item = (Item) data.getSerializableExtra(AddItemActivity.RESULT_ITEM);
            Toast toast = Toast.makeText(getContext(), item.name + " " + item.price, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void addItem(final Item item) {

        getLoaderManager().initLoader(LOADER_ADD, null, new LoaderManager.LoaderCallbacks<AddResult>() {

            @Override
            public Loader<AddResult> onCreateLoader(int id, Bundle args) {
                return new AsyncTaskLoader<AddResult>(getContext()) {
                    @Override
                    public AddResult loadInBackground() {
                        try {
                            return api.add(item.name, item.price, item.type).execute().body();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<AddResult> loader, AddResult data) {
                adapter.updateId(item, data.id);
            }

            @Override
            public void onLoaderReset(Loader<AddResult> loader) {

            }
        }).startLoading();
    }

    private void removeItem(final Item item) {

        getLoaderManager().initLoader(LOADER_REMOVE, null, new LoaderManager.LoaderCallbacks<Result>() {

            @Override
            public Loader<Result> onCreateLoader(int id, Bundle args) {
                return new AsyncTaskLoader<Result>(getContext()) {
                    @Override
                    public Result loadInBackground() {
                        try {
                            return api.remove(item.id).execute().body();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<Result> loader, Result data) {

            }

            @Override
            public void onLoaderReset(Loader<Result> loader) {

            }
        }).startLoading();
    }

}
