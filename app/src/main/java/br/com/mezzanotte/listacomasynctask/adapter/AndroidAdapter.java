package br.com.mezzanotte.listacomasynctask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mezzanotte.listacomasynctask.R;
import br.com.mezzanotte.listacomasynctask.listener.OnItemClickListener;
import br.com.mezzanotte.listacomasynctask.model.Android;

/**
 * Created by logonrm on 29/01/2018.
 */
public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Android> androids;
    private OnItemClickListener listener;

    public AndroidAdapter(Context context, List<Android> androids) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.androids = androids;
    }

    @Override
    public AndroidItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_android, parent, false);
        return new AndroidItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AndroidItemViewHolder holder, int position) {
        Android android = androids.get(position);
        holder.tvAPI.setText(android.getApi());
        holder.tvNome.setText(android.getNome());
        holder.tvVersao.setText(android.getVersao());
        Picasso.with(context).load(android.getUrlImagem()).error(R.drawable.error).placeholder(R.drawable.loadingcircles).into(holder.ivLogo);
    }

    @Override
    public int getItemCount() {
        return androids.size();
    }

    public Android getItem(int position) {
        return androids.get(position);
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    protected class AndroidItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivLogo;
        private TextView tvNome;
        private TextView tvAPI;
        private TextView tvVersao;

        public AndroidItemViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvAPI = itemView.findViewById(R.id.tvAPI);
            tvVersao = itemView.findViewById(R.id.tvVersao);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(v, getAdapterPosition());
            }
        }
    }

}
