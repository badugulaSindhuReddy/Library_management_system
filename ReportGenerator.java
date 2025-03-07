import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    public List<String> generateOverdueReport() throws SQLException {
        List<String> report = new ArrayList<>();
        String sql = "SELECT u.name, b.title, t.checkout_date " +
                     "FROM transactions t " +
                     "JOIN users u ON t.user_id = u.id " +
                     "JOIN books b ON t.book_id = b.id " +
                     "WHERE t.return_date IS NULL AND t.checkout_date < DATE_SUB(CURDATE(), INTERVAL 14 DAY)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String line = String.format("User: %s, Book: %s, Checkout Date: %s",
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getDate("checkout_date"));
                report.add(line);
            }
        }
        return report;
    }

    public List<String> generatePopularBooksReport() throws SQLException {
        List<String> report = new ArrayList<>();
        String sql = "SELECT b.title, COUNT(*) as checkout_count " +
                     "FROM transactions t " +
                     "JOIN books b ON t.book_id = b.id " +
                     "GROUP BY b.id " +
                     "ORDER BY checkout_count DESC " +
                     "LIMIT 10";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String line = String.format("Book: %s, Times Checked Out: %d",
                        rs.getString("title"),
                        rs.getInt("checkout_count"));
                report.add(line);
            }
        }
        return report;
    }
}
