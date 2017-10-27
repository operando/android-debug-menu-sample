package com.os.operando.debugmenu.sample;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.os.operando.debugmenu.sample.databinding.RowApiEnvironmentItemBinding;

public class ApiEnvironmentListAdapter extends RecyclerArrayAdapter<ApiEnvironment, BindingHolder<ViewDataBinding>> {

    public interface OnApiEnvironmentClickListener {
        void onApiEnvironmentClick(ApiEnvironment apiEnvironment);
    }

    private final OnApiEnvironmentClickListener onApiEnvironmentClickListener;

    public ApiEnvironmentListAdapter(OnApiEnvironmentClickListener onApiEnvironmentClickListener) {
        this.onApiEnvironmentClickListener = onApiEnvironmentClickListener;
    }

    @Override
    public BindingHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingHolder<>(parent.getContext(), parent, R.layout.row_api_environment_item);
    }

    @Override
    public void onBindViewHolder(BindingHolder<ViewDataBinding> holder, int position) {
        RowApiEnvironmentItemBinding binding = (RowApiEnvironmentItemBinding) holder.binding;
        ApiEnvironment apiEnvironment = getItem(position);
        binding.setApiEnvironment(apiEnvironment);
        binding.getRoot().setOnClickListener(v -> onApiEnvironmentClickListener.onApiEnvironmentClick(apiEnvironment));
    }
}