package net.kdt.pojavlaunch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class TranslateableArrayAdapter<T extends TranslateableArrayAdapter.Translateable> extends ArrayAdapter<T> {

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    @SuppressWarnings("unused")
    public TranslateableArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Translateable translateable = getItem(position);
        if(view instanceof TextView) {
            ((TextView)view).setText(translateable.getTranslationString());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        Translateable translateable = getItem(position);
        if(view instanceof TextView) {
            ((TextView)view).setText(translateable.getTranslationString());
        }
        return view;
    }

    public interface Translateable {
        int getTranslationString();
    }
}
