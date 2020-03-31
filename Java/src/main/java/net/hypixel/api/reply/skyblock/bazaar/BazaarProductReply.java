package net.hypixel.api.reply.skyblock.bazaar;

import com.google.gson.annotations.SerializedName;
import net.hypixel.api.reply.AbstractReply;

import java.util.List;

public class BazaarProductReply extends AbstractReply {

    /**
     * Product info of item in bazaar
     * @see <a href="https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/skyblock/bazaar/product.md">Bazaar docs</a>
     */
    @SerializedName("product_info")
    private ProductInfo productInfo;

    public BazaarProductReply(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toString() {
        return "ProductsReply{" +
                "productInfo=" + productInfo +
                "} " + super.toString();
    }

    public class ProductInfo {
        @SerializedName("product_id")
        private String productId;

        @SerializedName("buy_summary")
        private List<ProductSummary> buySummary;

        @SerializedName("sell_summary")
        private List<ProductSummary> sellSummary;

        @SerializedName("quick_status")
        private ProductStatus quickStatus;

        @SerializedName("week_historic")
        private List<ProductStatus> weekHistory;

        @Override
        public String toString() {
            return "ProductInfo{" +
                    "productId='" + productId +
                    ", buySummery=" + buySummary +
                    ", sellSummery=" + buySummary +
                    ", quickStatus=" + quickStatus +
                    ", weekHistory=" + weekHistory +
                    "}";
        }
    }


    public class ProductSummary {

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
            return "ProductSummery{" +
                    "amount=" + amount +
                    ", pricePerUnit=" + pricePerUnit +
                    ", orders=" + orders +
                    "}";
        }
    }

    public class ProductStatus {

        private String productId;
        private long timestamp;
        private int nowBuyVolume;
        private int nowSellVolume;

        private double buyCoins;
        private int buyVolume;
        private int buys;

        private double sellCoins;
        private int sellVolume;
        private int sells;

        public String getProductId() {
            return productId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public int getNowBuyVolume() {
            return nowBuyVolume;
        }

        public int getNowSellVolume() {
            return nowSellVolume;
        }

        public double getBuyCoins() {
            return buyCoins;
        }

        public int getBuyVolume() {
            return buyVolume;
        }

        public int getBuys() {
            return buys;
        }

        public double getSellCoins() {
            return sellCoins;
        }

        public int getSellVolume() {
            return sellVolume;
        }

        public int getSells() {
            return sells;
        }

        @Override
        public String toString() {
            return "ProductStatus{" +
                    "productId='" + productId +
                    ", timestamp=" + timestamp +
                    ", nowBuyVolume=" + nowBuyVolume +
                    ", nowSellVolume=" + nowSellVolume +
                    ", buyCoins=" + buyCoins +
                    ", buyVolume=" + buyVolume +
                    ", buys=" + buys +
                    ", sellCoins=" + sellCoins +
                    ", sellVolume=" + sellVolume +
                    ", sells=" + sells +
                    "}";
        }
    }
}
