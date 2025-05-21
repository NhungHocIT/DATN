package com.example.GoalMaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public interface OnCommentClickListener {
        void onCommentClick(Post post);
    }

    private Context context;
    private List<Post> postList;
    private User currentUser;
    private OnCommentClickListener listener;

    public PostAdapter(Context context, List<Post> postList, User currentUser, OnCommentClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.currentUser = currentUser;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.tvUsername.setText(post.getUsername());
        holder.tvTime.setText(post.getTime());
        holder.tvContent.setText(post.getContent());

        // Trạng thái like
        if (post.isLiked()) {
            holder.tvLikeText.setText("Đã thích");
            holder.imgLike.setImageResource(R.drawable.like);
        } else {
            holder.tvLikeText.setText("Thích");
            holder.imgLike.setImageResource(R.drawable.dontlike);
        }

        // Sự kiện Like
        holder.imgLike.setOnClickListener(v -> {
            post.toggleLike();
            notifyItemChanged(position);
        });
        holder.tvLikeText.setOnClickListener(v -> {
            post.toggleLike();
            notifyItemChanged(position);
        });

        // Sự kiện bình luận
        holder.imgComment.setOnClickListener(v -> showCommentDialog(post));
        holder.tvComment.setOnClickListener(v -> showCommentDialog(post));

        // Sự kiện chia sẻ
        holder.imgShare.setOnClickListener(v -> {
            long newId = System.currentTimeMillis(); // dùng thời gian làm ID tạm
            Post newPost = new Post(newId, currentUser.getUsername(), "Chia sẻ", post.getContent());
            postList.add(0, newPost);
            notifyItemInserted(0);
            Toast.makeText(context, "Bài viết đã được chia sẻ!", Toast.LENGTH_SHORT).show();
        });

        holder.tvShare.setOnClickListener(v -> {
            long newId = System.currentTimeMillis();
            Post newPost = new Post(newId, currentUser.getUsername(), "Chia sẻ", post.getContent());
            postList.add(0, newPost);
            notifyItemInserted(0);
            Toast.makeText(context, "Bài viết đã được chia sẻ!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvTime, tvContent, tvLikeText, tvComment, tvShare;
        ImageView imgLike, imgComment, imgShare;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvLikeText = itemView.findViewById(R.id.tvLikeText);
            imgLike = itemView.findViewById(R.id.imgdontLike);
            imgComment = itemView.findViewById(R.id.imgComment);
            tvComment = itemView.findViewById(R.id.tvComment);
            imgShare = itemView.findViewById(R.id.imgShare);
            tvShare = itemView.findViewById(R.id.tvShare);
        }
    }

    // Phương thức hiển thị Dialog bình luận
    private void showCommentDialog(Post post) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null);
        EditText etComment = dialogView.findViewById(R.id.edtComment);
        Button btnSendComment = dialogView.findViewById(R.id.btnSendComment);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView).setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();

        btnSendComment.setOnClickListener(v -> {
            String newComment = etComment.getText().toString();
            if (!newComment.isEmpty()) {
                post.addComment(newComment);
                Toast.makeText(context, "Bình luận đã được gửi", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
