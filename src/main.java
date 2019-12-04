import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class main extends Application  {
    public void start(Stage stg1){
        Alert alert_info = new Alert(Alert.AlertType.INFORMATION);
        Alert alert_error = new Alert(Alert.AlertType.ERROR);

        double rewardPts = 0;
        double subtotal =0;
        double savings =0;
        double tax=0;
        double grandtotal=0;

        ObservableList<item> cart = FXCollections.observableArrayList();
        TableColumn col_i_code = new TableColumn("Code");
        col_i_code.setMinWidth(100);
        col_i_code.setCellValueFactory(new PropertyValueFactory<item, String>("i_code"));

        TableColumn col_i_name = new TableColumn("Name");
        col_i_name.setMinWidth(100);
        col_i_name.setCellValueFactory(new PropertyValueFactory<item, String>("i_name"));

        TableColumn col_i_quantity = new TableColumn("QT.");
        col_i_quantity.setMinWidth(75);
        col_i_quantity.setCellValueFactory(new PropertyValueFactory<item, Double>("i_quantity"));

        TableColumn col_i_discounted_amount = new TableColumn("Discount");
        col_i_discounted_amount.setMinWidth(100);
        col_i_discounted_amount.setCellValueFactory(new PropertyValueFactory<item, Double>("i_discount"));

        TableColumn col_i_price = new TableColumn("Price");
        col_i_price.setMinWidth(100);
        col_i_price.setCellValueFactory(new PropertyValueFactory<item, String>("i_price"));

        Button btn_cancel1 = new Button("Cancel");
        Button btn_cancel2 = new Button("Cancel");
        Button btn_cancel3 = new Button("Cancel");

        stg1.setTitle("Grocery Billing");
        //Left<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---<---
        BorderPane BPane = new BorderPane();
        Scene sc_main = new Scene(BPane,800,600);

        Button btn_add = new Button();
        Button btn_update = new Button();
        Button btn_remove = new Button();
        Button btn_print = new Button();

        btn_add.setText("Add an Item");
        btn_update.setText("Update an Item");
        btn_remove.setText("Remove an Item");
        btn_print.setText("Print");

        btn_add.setPrefWidth(120);
        btn_update.setPrefWidth(120);
        btn_remove.setPrefWidth(120);
        btn_print.setPrefWidth(120);




        FlowPane flow_main_left = new FlowPane();
        flow_main_left.setPadding(new Insets(20, 0, 5, 20));
        flow_main_left.setVgap(10);
        flow_main_left.setHgap(4);
        flow_main_left.setPrefWrapLength(50);

        flow_main_left.getChildren().addAll(btn_add,btn_update,btn_remove,btn_print);


        BPane.setLeft(flow_main_left);
        //Right--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->--->
        TableView<item> tbl = new TableView<>();
        tbl.setEditable(true);
        tbl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
        tbl.setItems(cart);// table items are getting populated from observable list
        tbl.getColumns().addAll(col_i_code,col_i_name,col_i_quantity,col_i_discounted_amount,col_i_price); // adding columns to table

        BPane.setRight(tbl);



        //Add--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
        FlowPane flow_add_left = new FlowPane();
        flow_add_left.setPadding(new Insets(20, 0, 5, 20));
        flow_add_left.setVgap(10);
        flow_add_left.setHgap(4);
        flow_add_left.setPrefWrapLength(20);


        Text txt_add_title = new Text("Add item \t\t\t");
        txt_add_title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label lbl_i_code = new Label("Item Code: ");
        Label lbl_i_name = new Label("Item Name: ");
        Label lbl_i_quantity = new Label("Item Quantity: ");
        Label lbl_i_price = new Label("Item Price: ");
        Label lbl_i_discounts = new Label("Item Discount (%): ");


        TextField txf_i_code = new TextField();
        TextField txf_i_name = new TextField();
        TextField txf_i_quantity = new TextField();
        TextField txf_i_price = new TextField();
        TextField txf_i_discount = new TextField();

        CheckBox chk_i_tax_exempt = new CheckBox("Tax Exempt (Grocery or Drugs)");

        Button btn_add2 = new Button("Add new Item");
        //btn_add2.setDisable(true);

        flow_add_left.getChildren().addAll(txt_add_title,lbl_i_code,txf_i_code,lbl_i_name,txf_i_name,lbl_i_quantity,txf_i_quantity,
                lbl_i_price,txf_i_price,lbl_i_discounts,txf_i_discount,chk_i_tax_exempt,btn_add2,btn_cancel1);


        EventHandler<ActionEvent> e_add2_btn= e -> {
            if(txf_i_code.getText().isBlank() || txf_i_name.getText().isBlank() ||
                    txf_i_quantity.getText().isBlank() || txf_i_price.getText().isBlank()
                    || txf_i_discount.getText().isBlank()){
                        alert_error.setTitle("Error - Missing Information");
                        alert_error.setHeaderText(null);
                        alert_error.setContentText("Please fill out all the fields and try again");
                        alert_error.showAndWait();
            }
            else{
                item new_item = new item(txf_i_code.getText(),txf_i_name.getText(),
                        Double.parseDouble(txf_i_quantity.getText()),
                        Double.parseDouble(txf_i_price.getText()),
                        Double.parseDouble(txf_i_discount.getText()),
                        chk_i_tax_exempt.isSelected());
                cart.add(new_item);

                txf_i_code.clear();
                txf_i_name.clear();
                txf_i_quantity.clear();
                txf_i_price.clear();
                txf_i_discount.clear();
                chk_i_tax_exempt.setSelected(false);
                BPane.setLeft(flow_main_left);
            }
        };

        EventHandler<ActionEvent> e_add_btn= e -> BPane.setLeft(flow_add_left);


        //update u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u__u
        FlowPane flow_update_left = new FlowPane();
        flow_update_left.setPadding(new Insets(20, 0, 5, 20));
        flow_update_left.setVgap(10);
        flow_update_left.setHgap(4);
        flow_update_left.setPrefWrapLength(200);

        Text txt_update_title = new Text("Update item \t\t\t");
        txt_update_title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label lbl_iu_code = new Label("Item Code: ");
        Label lbl_iu_name = new Label("Item Name: ");
        Label lbl_iu_quantity = new Label("Item Quantity: ");
        Label lbl_iu_price = new Label("Item Price: ");
        Label lbl_iu_discounts = new Label("Item Discount (%): ");

        TextField txf_iu_code = new TextField();
        TextField txf_iu_name = new TextField();
        TextField txf_iu_quantity = new TextField();
        TextField txf_iu_price = new TextField();
        TextField txf_iu_discount = new TextField();

        CheckBox chk_iu_tax_exempt = new CheckBox("Tax Exempt (Grocery or Drugs)");

        Button btn_update2 = new Button("Update");

        flow_update_left.getChildren().addAll(txt_update_title,lbl_iu_code,txf_iu_code,lbl_iu_name,txf_iu_name,lbl_iu_quantity,txf_iu_quantity,
                lbl_iu_price,txf_iu_price,lbl_iu_discounts,txf_iu_discount,chk_iu_tax_exempt,btn_update2,btn_cancel2);

        EventHandler<ActionEvent> e_update2_btn= e -> {
            if(txf_iu_code.getText().isBlank() || txf_iu_name.getText().isBlank() ||
                    txf_iu_quantity.getText().isBlank() || txf_iu_price.getText().isBlank()
                    || txf_iu_discount.getText().isBlank()){
                alert_error.setTitle("Error - Missing Information");
                alert_error.setHeaderText(null);
                alert_error.setContentText("Please fill out all the fields and try again");
                alert_error.showAndWait();
            }
            else{
                Boolean noMatchFound = true;
                for(int i = 0; i<cart.size();i++){
                    item tempItem = cart.get(i);
                    if(tempItem.getI_code().equals(txf_iu_code.getText())){
                        item new_item = new item(txf_iu_code.getText(),txf_iu_name.getText(),
                                Double.parseDouble(txf_iu_quantity.getText()),
                                Double.parseDouble(txf_iu_price.getText()),
                                Double.parseDouble(txf_iu_discount.getText()),
                                chk_iu_tax_exempt.isSelected());
                        cart.get(i).remove_item();
                        cart.remove(i);
                        cart.add(i,new_item);
                        noMatchFound=false;
                        txf_iu_code.clear();
                        txf_iu_name.clear();
                        txf_iu_quantity.clear();
                        txf_iu_price.clear();
                        txf_iu_discount.clear();
                        chk_iu_tax_exempt.setSelected(false);
                        BPane.setLeft(flow_main_left);
                        break;
                    }
                }
                if(noMatchFound)
                {
                    alert_error.setTitle("Error - Item Not Found");
                    alert_error.setHeaderText(null);
                    alert_error.setContentText("No existing item with code: "+txf_iu_code.getText());
                    alert_error.showAndWait();
                }
            }
        };


        EventHandler<ActionEvent> e_update_btn= e -> BPane.setLeft(flow_update_left);

        //remove-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
        FlowPane flow_remove_left = new FlowPane();
        flow_remove_left.setPadding(new Insets(20, 0, 5, 20));
        flow_remove_left.setVgap(10);
        flow_remove_left.setHgap(4);
        flow_remove_left.setPrefWrapLength(200);

        Text txt_remove_title = new Text("Remove item \t\t\t");
        txt_remove_title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label lbl_ir_code = new Label("Item Code: ");
        TextField txf_ir_code = new TextField();
        Button btn_remove2 = new Button("Remove an Item");

        flow_remove_left.getChildren().addAll(txt_remove_title,lbl_ir_code,txf_ir_code,btn_remove2,btn_cancel3);

        EventHandler<ActionEvent> e_remove2_btn= e -> {
            if(txf_ir_code.getText().isBlank()){
                alert_error.setTitle("Error - Missing Information");
                alert_error.setHeaderText(null);
                alert_error.setContentText("Please fill out all the fields and try again");
                alert_error.showAndWait();
            }
            else{
                Boolean noMatchFound = true;
                for(int i = 0; i<cart.size();i++){
                    item tempItem = cart.get(i);
                    if(tempItem.getI_code().equals(txf_ir_code.getText())){
                        cart.get(i).remove_item();
                        cart.remove(i);
                        noMatchFound=false;
                        txf_ir_code.clear();
                        BPane.setLeft(flow_main_left);
                        break;
                    }
                }
                if(noMatchFound)
                {
                    alert_error.setTitle("Error - Item Not Found");
                    alert_error.setHeaderText(null);
                    alert_error.setContentText("No existing item with code: "+txf_iu_code.getText());
                    alert_error.showAndWait();
                }
            }
        };


        EventHandler<ActionEvent> e_remove_btn= e -> BPane.setLeft(flow_remove_left);


        //Print P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--P--
        FlowPane flow_print_left = new FlowPane();
        flow_print_left.setPadding(new Insets(20, 0, 5, 20));
        flow_print_left.setVgap(10);
        flow_print_left.setHgap(4);
        flow_print_left.setPrefWrapLength(120);

        Text txt_print_title = new Text("Print                          ");
        txt_print_title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button btn_print2 = new Button("Edit Cart");
        Button btn_print3 = new Button("Clear Cart");
        flow_print_left.getChildren().addAll(txt_print_title);
        // subtotal, savings, reward point earned so far, tax amount and grand total


        Text txt_print1 = new Text("Subtotal:\t\t\t\t "+subtotal);
        Text txt_print2 = new Text("Savings:\t\t\t\t "+subtotal);
        Text txt_print3 = new Text("Tax:\t\t\t\t\t "+subtotal);
        Text txt_print4 = new Text("Grand total:\t\t\t "+subtotal);
        Text txt_print5 = new Text("Reward Points:\t\t\t "+subtotal+"\t\t");



        flow_print_left.getChildren().addAll(txt_print1,txt_print2,txt_print3,txt_print4,txt_print5,btn_print2,btn_print3);

        EventHandler<ActionEvent> e_print_btn= e ->{
            if(item.reward_discount())
                txt_print5.setText("Reward Points:\t\t\t 10% Bonus Discount used!");
            else
                txt_print5.setText("Reward Points:\t\t\t "+item.cart_rewardPts+"\t\t");
            BPane.setLeft(flow_print_left);
            txt_print1.setText("Subtotal:\t\t\t\t "+item.cart_subtotal);
            txt_print2.setText("Savings:\t\t\t\t "+item.cart_savings);
            txt_print3.setText("Tax:\t\t\t\t\t "+item.cart_tax);
            txt_print4.setText("Grand total:\t\t\t "+item.cart_grandTotal);

        };

        EventHandler<ActionEvent> e_print2_btn= e ->{
            BPane.setLeft(flow_main_left);
        };

        EventHandler<ActionEvent> e_print3_btn= e ->{
            cart.clear();
            item.clear_cart();
            BPane.setLeft(flow_main_left);
        };

        //initial i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--i--
        EventHandler<ActionEvent> e_cancel= e ->{
            BPane.setLeft(flow_main_left);
        };
        btn_add.setOnAction(e_add_btn);
        btn_add2.setOnAction(e_add2_btn);
        btn_update.setOnAction(e_update_btn);
        btn_update2.setOnAction(e_update2_btn);
        btn_remove.setOnAction(e_remove_btn);
        btn_remove2.setOnAction(e_remove2_btn);
        btn_print.setOnAction(e_print_btn);
        btn_print2.setOnAction(e_print2_btn);
        btn_print3.setOnAction(e_print3_btn);
        btn_cancel1.setOnAction(e_cancel);
        btn_cancel2.setOnAction(e_cancel);
        btn_cancel3.setOnAction(e_cancel);

        stg1.setScene(sc_main);
        stg1.show();
    }
    public static void main(String[] args){
        launch(args);
    }



}