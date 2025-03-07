import java.sql.*;
import java.time.LocalDate;

public class TransactionManager {
    public void checkoutBook(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO transactions (user_id, book_id, checkout_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();
        }
        updateBookAvailability(bookId, -1);
    }

    public void returnBook(int userId, int bookId) throws SQLException {
        String sql = "UPDATE transactions SET return_date = ? WHERE user_id = ? AND book_id = ? AND return_date IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, userId);
            pstmt.setInt(3, bookId);
            pstmt.executeUpdate();
        }
        updateBookAvailability(bookId, 1);
    }

    private void updateBookAvailability(int bookId, int change) throws SQLException {
        String sql = "UPDATE books SET available = available + ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, change);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        }
    }
}
