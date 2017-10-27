package com.os.operando.debugmenu.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class RecyclerArrayAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final Object lock = new Object();
    private final List<T> objects;

    public RecyclerArrayAdapter() {
        this(new ArrayList<>());
    }

    public RecyclerArrayAdapter(@NonNull List<T> objects) {
        this.objects = objects;
    }

    @Override
    public int getItemCount() {
        synchronized (lock) {
            return objects.size() + getHeaderPositionOffset() + getFooterPositionOffset();
        }
    }

    public T getItem(int position) {
        int headerPositionOffset = getHeaderPositionOffset();
        if (position < headerPositionOffset) {
            return null;
        }
        synchronized (lock) {
            int footerPositionOffset = getFooterPositionOffset();
            if (position > objects.size() - 1 + footerPositionOffset + headerPositionOffset) {
                return null;
            }
            position -= headerPositionOffset;
            if (position < objects.size()) {
                return objects.get(position);
            }
        }
        return null;
    }

    /**
     * return position offset for data like header counts.
     */
    public int getPositionOffset() {
        return getHeaderPositionOffset();
    }

    /**
     * return position offset for data like header counts.
     */
    protected int getHeaderPositionOffset() {
        return 0;
    }


    /**
     * return position offset for data like footer counts.
     */
    protected int getFooterPositionOffset() {
        return 0;
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void add(@NonNull T object) {
        final int position;
        synchronized (lock) {
            position = objects.size() + getPositionOffset();
            objects.add(object);
        }
        notifyItemInserted(position);
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     */
    public void addAll(@NonNull Collection<? extends T> collection) {
        final int itemCount = collection.size();
        final int position;
        synchronized (lock) {
            position = objects.size() + getPositionOffset();
            objects.addAll(collection);
        }
        notifyItemRangeInserted(position, itemCount);
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     */
    public void addAll(T... items) {
        final int itemCount = items.length;
        final int position;
        synchronized (lock) {
            position = objects.size() + getPositionOffset();
            Collections.addAll(objects, items);
        }
        notifyItemRangeInserted(position, itemCount);
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object   The object to insert into the array.
     * @param position The index at which the object must be inserted.
     */
    public void insert(@NonNull T object, int position) {
        synchronized (lock) {
            objects.add(position - getPositionOffset(), object);
        }
        notifyItemInserted(position);
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    public void remove(@NonNull T object) {
        final int position;
        final boolean isRemoved;
        synchronized (lock) {
            position = objects.indexOf(object) + getPositionOffset();
            isRemoved = objects.remove(object);
        }
        if (isRemoved) {
            notifyItemRemoved(position);
        }
    }

    /**
     * Removes the specified object at the specified index from the array.
     *
     * @param position The index at which the object must be removed.
     */
    public T remove(int position) {
        T prev;
        synchronized (lock) {
            prev = objects.remove(position - getPositionOffset());
        }
        notifyItemRemoved(position);
        return prev;
    }

    /**
     * Moves the specified object
     *
     * @param from The index at which the object must be moved.
     * @param to   The destination index.
     */
    public void move(int from, int to) {
        synchronized (lock) {
            T prev = objects.remove(from - getPositionOffset());
            objects.add(to - getPositionOffset(), prev);
        }
        notifyItemMoved(from, to);
    }

    public void changeItem(int position, T object) {
        synchronized (lock) {
            objects.set(position, object);
        }
        notifyItemChanged(position);
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        final int itemCount;
        synchronized (lock) {
            itemCount = objects.size();
            objects.clear();
        }
        notifyItemRangeRemoved(getPositionOffset(), itemCount);
    }

    public boolean isEmpty() {
        return objects.isEmpty();
    }

    public List<T> getItems() {
        return objects;
    }
}