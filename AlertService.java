public class AlertService implements StockObserver {

    @Override
    public void onLowStock(Product product) {
        System.out.println();
        System.out.println("RESTOCK ALERT");
        System.out.println(" Product ID:    " + product.getId());
        System.out.println(" Product Name:  " + product.getName());
        System.out.println(" Remaining Qty: " + product.getQuantity() + " (Threshold is " + product.getReorderThreshold() + ")");
        System.out.println();
    }
}
