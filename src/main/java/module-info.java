module com.example.account {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.account to javafx.fxml;
    exports com.example.account;
}