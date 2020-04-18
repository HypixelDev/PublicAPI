package net.hypixel.api.reply.skyblock;

import com.google.gson.annotations.SerializedName;
import net.hypixel.api.reply.AbstractReply;

import java.util.List;
import java.util.Map;

public class BazaarReply extends AbstractReply {

    private long lastUpdated;
    private Map<String, Product> products;

    public Map<String, Product> getProducts() {
        return products;
    }

    /**
     * Returns {@link Product} from bazaar reply.
     * Returns null if product does not exist
     *
     * @param productId product in bazaar
     * @return instance of Product
     */
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public String toString() {
        return "BazaarReply{" +
                "lastUpdated=" + lastUpdated +
                ", products=" + products +
                "} " + super.toString();
    }

    public class Product {

        @SerializedName("product_id")
        private String productId;

        @SerializedName("sell_summary")
        private List<Summary> sellSummary;

        @SerializedName("buy_summary")
        private List<Summary> buySummary;

        @SerializedName("quick_status")
        private Status quickStatus;

        public String getProductId() {
            return productId;
        }

        public List<Summary> getSellSummary() {
            return sellSummary;
        }

        public List<Summary> getBuySummary() {
            return buySummary;
        }

        public Status getQuickStatus() {
            return quickStatus;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "productId='" + productId +
                    ", sellSummary=" + sellSummary +
                    ", buySummary=" + buySummary +
                    ", quickStatus=" + quickStatus +
                    "}";
        }

        public class Summary {

            private int amount;
            private double pricePerUnit;
            private int orders;

            public int getAmount() {
                return amount;
            }

            public double getPricePerUnit() {
                return pricePerUnit;
            }

            public int getOrders() {
                return orders;
            }

            @Override
            public String toString() {
                return "Summary{" +
                        "amount=" + amount +
                        ", pricePerUnit=" + pricePerUnit +
                        ", orders=" + orders +
                        "}";
            }
        }

        public class Status {

            private String productId;

            private double sellPrice;

            private int sellVolume;

            private int sellMovingWeek;

            private int sellOrders;

            private double buyPrice;

            private int buyVolume;

            private int buyMovingWeek;

            private int buyOrders;

            public String getProductId() {
                return productId;
            }

            public double getSellPrice() {
                return sellPrice;
            }

            public int getSellVolume() {
                return sellVolume;
            }

            public int getSellMovingWeek() {
                return sellMovingWeek;
            }

            public int getSellOrders() {
                return sellOrders;
            }

            public double getBuyPrice() {
                return buyPrice;
            }

            public int getBuyVolume() {
                return buyVolume;
            }

            public int getBuyMovingWeek() {
                return buyMovingWeek;
            }

            public int getBuyOrders() {
                return buyOrders;
            }

            @Override
            public String toString() {
                return "Status{" +
                        "productId='" + productId +
                        ", sellPrice=" + sellPrice +
                        ", sellVolume=" + sellVolume +
                        ", sellMovingWeek=" + sellMovingWeek +
                        ", sellOrders=" + sellOrders +
                        ", buyPrice=" + buyPrice +
                        ", buyVolume=" + buyVolume +
                        ", buyMovingWeek=" + buyMovingWeek +
                        ", buyOrders=" + buyOrders +
                        "}";
            }
        }
    }
}
