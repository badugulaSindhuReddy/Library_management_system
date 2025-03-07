import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryManagementSystem extends Application {
    private BookManager bookManager = new BookManager();
    private UserManager userManager = new UserManager();
    private TransactionManager transactionManager = new TransactionManager();
    private ReportGenerator reportGenerator = new ReportGenerator();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        TabPane tabPane = new TabPane();

        Tab addBookTab = new Tab("Add Book");
        addBookTab.setContent(createAddBookPane());
        tabPane.getTabs().add(addBookTab);

        Tab searchBookTab = new Tab("Search Book");
        searchBookTab.setContent(createSearchBookPane());
        tabPane.getTabs().add(searchBookTab);

        Tab checkoutTab = new Tab("Checkout");
        checkoutTab.setContent(createCheckoutPane());
        tabPane.getTabs().add(checkoutTab);

        Tab returnTab = new Tab("Return");
        returnTab.setContent(createReturnPane());
        tabPane.getTabs().add(returnTab);

        Tab reportsTab = new Tab("Reports");
        reportsTab.setContent(createReportsPane());
        tabPane.getTabs().add(reportsTab);

        Scene scene = new Scene(tabPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createAddBookPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField isbnField = new TextField();
        TextField quantityField = new TextField();

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("ISBN:"), 0, 2);
        grid.add(isbnField, 1, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(quantityField, 1, 3);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            try {
                Book book = new Book(0, titleField.getText(), authorField.getText(), isbnField.getText(),
                        Integer.parseInt(quantityField.getText()), Integer.parseInt(quantityField.getText()));
                bookManager.addBook(book);
                showAlert("Success", "Book added successfully!");
            } catch (Exception ex) {
                showAlert("Error", "Failed to add book: " + ex.getMessage());
            }
        });
        grid.add(addButton, 1, 4);

        return grid;
    }

    private GridPane createSearchBookPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField searchField = new TextField();
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        grid.add(new Label("Search Title:"), 0, 0);
        grid.add(searchField, 1, 0);
        grid.add(resultArea, 0, 1, 2, 1);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            try {
                Book book = bookManager.searchBook(searchField.getText());
                if (book != null) {
                    resultArea.setText(book.toString());
                } else {
                    resultArea.setText("No book found.");
                }
            } catch (Exception ex) {
                showAlert("Error", "Failed to search book: " + ex.getMessage());
            }
