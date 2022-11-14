package dao;

import com.revature.model.Comment;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public List<Comment> findCommentByPostId(int postId) throws SQLException {

        // try with resources to close connection once sql executed
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM comments \n" +
                    "WHERE post_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, postId);

            ResultSet rs = pstmt.executeQuery();

            List<Comment> comments = new ArrayList<>();

            while (rs.next()) {
               comments.add(
                        new Comment(
                                rs.getInt("comment_id"),
                                rs.getString("comment_message"),
                                rs.getInt("post_Id"),
                                rs.getInt("user_Id")
                        )
                );
            }
            return comments;
        }
    }

    public int createNewComment(Comment comment) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "INSERT INTO comments (comment_message, user_id, post_id) \n" +
                    "VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, comment.getCommentMessage());
            pstmt.setInt(2, comment.getUserId());
            pstmt.setInt(3, comment.getPostId());


            return pstmt.executeUpdate();
    }
}

    public int findAndDeleteCommentById(int commentId) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "DELETE FROM comments \n" +
                    "WHERE comment_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, commentId);

            return pstmt.executeUpdate();
        }
    }

    public void findAndUpdateCommentId(Comment comment) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "UPDATE comments \n" +
                    "SET comment_message = ? \n" +
                    "WHERE comment_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, comment.getCommentMessage());
            pstmt.setInt(2, comment.getCommentId());


            pstmt.executeUpdate();
        }
    }
}
