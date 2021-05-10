package net.hypixel.api.reply.skyblock;

import com.google.gson.annotations.SerializedName;
import net.hypixel.api.reply.AbstractReply;

import java.util.List;
import java.util.Map;

public class SkyBlockBazaarReply extends AbstractReply {

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

            private long amount;
            private double pricePerUnit;
            private long orders;

            public long getAmount() {
                return amount;
            }

            public double getPricePerUnit() {
                return pricePerUnit;
            }

            public long getOrders() {
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
            private long sellVolume;
            private long sellMovingWeek;
            private long sellOrders;
            private double buyPrice;
            private long buyVolume;
            private long buyMovingWeek;
            private long buyOrders;

            public String getProductId() {
                return productId;
            }

            public double getSellPrice() {
                return sellPrice;
            }

            public long getSellVolume() {
                return sellVolume;
            }

            public long getSellMovingWeek() {
                return sellMovingWeek;
            }

            public long getSellOrders() {
                return sellOrders;
            }

            public double getBuyPrice() {
                return buyPrice;
            }

            public long getBuyVolume() {
                return buyVolume;
            }

            public long getBuyMovingWeek() {
                return buyMovingWeek;
            }

            public long getBuyOrders() {
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
