package net.hypixel.api.reply.skyblock.bazaar;

import net.hypixel.api.reply.AbstractReply;

import java.util.List;

public class BazaarProductsReply extends AbstractReply {

    /**
     * List of id of current products in Bazaar
     * @see <a href="https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/skyblock/bazaar/products.md">Bazaar docs</a>
     */
    private List<String> productIds;

    public List<String> getProductIds() {
        return productIds;
    }

    @Override
    public String toString() {
        return "ProductsReply{" +
                "productIds=" + productIds +
                "} " + super.toString();
    }
}
