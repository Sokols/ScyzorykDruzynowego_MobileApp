package pl.sokols.scyzorykdruzynowego.ui.main.stamps.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

import pl.sokols.scyzorykdruzynowego.data.entity.Stamp;
import pl.sokols.scyzorykdruzynowego.databinding.ListitemStampBinding;
import pl.sokols.scyzorykdruzynowego.ui.main.stamps.StampsFragment;

public class StampsAdapter extends RecyclerView.Adapter<StampsAdapter.StampsViewHolder> {

    public interface OnStampClickListener {
        void onStampClick(Stamp stamp);
    }

    public static class StampsViewHolder extends RecyclerView.ViewHolder {

        private ListitemStampBinding mBinding;
        private StampsFragment mFragment;

        public StampsViewHolder(ListitemStampBinding binding, StampsFragment fragment) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mFragment = fragment;
        }

        public void bind(Stamp stamp, OnStampClickListener listener) {
            mBinding.setStamp(stamp);
            GlideToVectorYou.justLoadImage(mFragment.requireActivity(), Uri.parse(stamp.getStampImageURL()), mBinding.stampIcon);
            itemView.setOnClickListener(view -> listener.onStampClick(stamp));
        }
    }

    private List<Stamp> mStampList;
    private StampsFragment mFragment;
    private OnStampClickListener mListener;

    public StampsAdapter(List<Stamp> stampList, StampsFragment fragment, OnStampClickListener listener) {
        this.mStampList = stampList;
        this.mFragment = fragment;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public StampsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListitemStampBinding binding = ListitemStampBinding.inflate(layoutInflater, parent, false);
        return new StampsViewHolder(binding, mFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull StampsViewHolder holder, int position) {
        holder.bind(mStampList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mStampList == null ? 0 : mStampList.size();
    }
}