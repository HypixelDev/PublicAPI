package net.hypixel.api.pets;

import net.hypixel.api.util.Rarity;

public enum PetType {

    SILVERFISH("Silverfish", Rarity.COMMON),
    
    CAT_BLACK("Cat: Black", Rarity.COMMON),
    CAT_RED("Cat: Red", Rarity.COMMON),
    CAT_SIAMESE("Cat: Siamese", Rarity.COMMON),
    CAT_BLACK_BABY("Cat: Black (Baby)", Rarity.RARE),
    CAT_RED_BABY("Cat: Red (Baby)", Rarity.RARE),
    CAT_SIAMESE_BABY("Cat: Siamese (Baby)", Rarity.RARE),
    WILD_OCELOT("Wild Ocelot"),
    WILD_OCELOT_BABY("Wild Ocelot (Baby)"),
    
    WOLF("Wolf", Rarity.COMMON),
    WOLF_BABY("Wolf (Baby)", Rarity.RARE),
    
    BAT("Bat", Rarity.EPIC),
    
    BLACK_RABBIT("Rabbit: Black", Rarity.RARE),
    BLACK_WHITE_RABBIT("Rabbit: Black & White", Rarity.EPIC),
    BROWN_RABBIT("Rabbit: Brown", Rarity.RARE),
    GOLD_RABBIT("Rabbit: Gold", Rarity.EPIC),
    SALT_PEPPER_RABBIT("Rabbit: Salt & Pepper", Rarity.EPIC),
    WHITE_RABBIT("Rabbit: White", Rarity.RARE),
    KILLER_RABBIT("Killer Rabbit", Rarity.EPIC),

    VILLAGER_BLACKSMITH("Villager: Blacksmith", Rarity.RARE),
    VILLAGER_BLACKSMITH_BABY("Villager: Blacksmith (Baby)", Rarity.EPIC),
    VILLAGER_BUTCHER("Villager: Butcher", Rarity.RARE),
    VILLAGER_BUTCHER_BABY("Villager: Butcher (Baby)", Rarity.EPIC),
    VILLAGER_FARMER("Villager: Farmer", Rarity.RARE),
    VILLAGER_FARMER_BABY("Villager: Farmer (Baby)", Rarity.EPIC),
    VILLAGER_LIBRARIAN("Villager: Librarian", Rarity.RARE),
    VILLAGER_LIBRARIAN_BABY("Villager: Librarian (Baby)", Rarity.EPIC),
    VILLAGER_PRIEST("Villager: Priest", Rarity.RARE),
    VILLAGER_PRIEST_BABY("Villager: Priest (Baby)", Rarity.EPIC),
    ZOMBIE_VILLAGER("Villager: Zombie", Rarity.EPIC),
    WITCH("Witch", Rarity.EPIC),
    
    ZOMBIE("Zombie", Rarity.COMMON),
    ZOMBIE_BABY("Zombie (Baby)", Rarity.RARE),
    FROZEN_ZOMBIE("Frozen Zombie", Rarity.EPIC),
    GROWING_ZOMBIE("Growing Zombie", Rarity.EPIC),
    BURNING_ZOMBIE("Burning Zombie", Rarity.EPIC),
    
    RED_HELPER("Red Little Helper", Rarity.EPIC),
    GREEN_HELPER("Green Little Helper", Rarity.EPIC),
    
    IRON_GOLEM("Golem", Rarity.LEGENDARY),
    
    ENDERMAN("Enderman", Rarity.LEGENDARY),

    BLAZE("Blaze", Rarity.LEGENDARY),
    
    SNOWMAN("Snowman", Rarity.EPIC),
    
    HEROBRINE("Herobrine", Rarity.LEGENDARY),
    
    ENDERMITE("Endermite", Rarity.LEGENDARY),
    
    MINI_WITHER("Mini Wither", Rarity.LEGENDARY),
    
    CLONE("Clone", Rarity.LEGENDARY),
    
    MINECART("Minecart", Rarity.LEGENDARY),
    
    TNT("TNT", Rarity.LEGENDARY),
    
    GRINCH("Grinch", Rarity.LEGENDARY),
    
    BEE("Bee", Rarity.LEGENDARY),
    
    FIREFLY("Firefly", Rarity.LEGENDARY),
    
    CYCLOPS("Cyclops", Rarity.LEGENDARY),
    
    SPIDER("Spider", Rarity.EPIC),
    CAVE_SPIDER("Cave Spider", Rarity.RARE),
    BOUNCY_SPIDER("Bouncy Spider", Rarity.LEGENDARY),
    SPIDER_JOCKEY("Spider Jockey", Rarity.EPIC),
    SNOWMAN_JOCKEY("Snowman Jockey", Rarity.LEGENDARY),
    
    CHICKEN("Chicken", Rarity.COMMON),
    CHICKEN_BABY("Chicken (Baby)", Rarity.RARE),
    CHICKEN_JOCKEY("Chicken Jockey", Rarity.LEGENDARY),
    
    COW("Cow", Rarity.COMMON),
    COW_BABY("Cow (Baby)", Rarity.RARE),
    MOOSHROOM("Mooshroom", Rarity.EPIC),
    MOOSHROOM_BABY("Mooshroom (Baby)", Rarity.LEGENDARY),
    
    CREEPER("Creeper", Rarity.EPIC),
    CREEPER_POWERED("Powered Creeper", Rarity.LEGENDARY),
    
    HORSE_BLACK("Horse: Black", Rarity.EPIC),
    HORSE_BROWN("Horse: Brown", Rarity.COMMON),
    HORSE_CHESTNUT("Horse: Chestnut", Rarity.RARE),
    HORSE_CREAMY("Horse: Creamy", Rarity.RARE),
    HORSE_DARK_BROWN("Horse: Dark Brown", Rarity.RARE),
    HORSE_GREY("Horse: Gray", Rarity.RARE),
    HORSE_WHITE("Horse: White", Rarity.EPIC),
    BROWN_HORSE_BABY("Horse: Brown (Baby)", Rarity.RARE),
    HORSE_CHESTNUT_BABY("Horse: Chestnut (Baby)", Rarity.EPIC),
    HORSE_CREAMY_BABY("Horse: Creamy (Baby)", Rarity.EPIC),
    HORSE_DARK_BROWN_BABY("Horse: Dark Brown (Baby)", Rarity.EPIC),
    HORSE_GRAY_BABY("Horse: Gray (Baby)", Rarity.EPIC),
    HORSE_UNDEAD("Undead Horse", Rarity.EPIC),
    HORSE_SKELETON("Skeleton Horse", Rarity.LEGENDARY),
    MULE("Mule", Rarity.RARE),
    DONKEY("Donkey", Rarity.RARE),
    
    PIG("Pig", Rarity.COMMON),
    PIG_BABY("Pig (Baby)", Rarity.RARE),
    PIG_ZOMBIE("Pig Zombie", Rarity.EPIC),
    PIG_ZOMBIE_BABY("Pig Zombie (Baby)", Rarity.LEGENDARY),
    
    SHEEP_BLACK("Sheep: Black", Rarity.EPIC),
    SHEEP_BLACK_BABY("Sheep: Black (Baby)", Rarity.LEGENDARY),
    SHEEP_BLUE("Sheep: Blue", Rarity.RARE),
    SHEEP_BLUE_BABY("Sheep: Blue (Baby)", Rarity.EPIC),
    SHEEP_BROWN("Sheep: Brown", Rarity.COMMON),
    SHEEP_BROWN_BABY("Sheep: Brown (Baby)", Rarity.RARE),
    SHEEP_CYAN("Sheep: Cyan", Rarity.RARE),
    SHEEP_CYAN_BABY("Sheep: Cyan (Baby)", Rarity.EPIC),
    SHEEP_GRAY("Sheep: Gray", Rarity.COMMON),
    SHEEP_GRAY_BABY("Sheep: Gray (Baby)", Rarity.RARE),
    SHEEP_GREEN("Sheep: Green", Rarity.RARE),
    SHEEP_GREEN_BABY("Sheep: Green (Baby)", Rarity.EPIC),
    SHEEP_LIGHT_BLUE("Sheep: Light Blue", Rarity.RARE),
    SHEEP_LIGHT_BLUE_BABY("Sheep: Light Blue (Baby)", Rarity.EPIC),
    SHEEP_LIME("Sheep: Lime", Rarity.RARE),
    SHEEP_LIME_BABY("Sheep: Lime (Baby)", Rarity.EPIC),
    SHEEP_MAGENTA("Sheep: Magenta", Rarity.RARE),
    SHEEP_MAGENTA_BABY("Sheep: Magenta (Baby)", Rarity.EPIC),
    SHEEP_ORANGE("Sheep: Orange", Rarity.RARE),
    SHEEP_ORANGE_BABY("Sheep: Orange (Baby)", Rarity.EPIC),
    SHEEP_PINK("Sheep: Pink", Rarity.EPIC),
    SHEEP_PINK_BABY("Sheep: Pink (Baby)", Rarity.LEGENDARY),
    SHEEP_PURPLE("Sheep: Purple", Rarity.RARE),
    SHEEP_PURPLE_BABY("Sheep: Purple (Baby)", Rarity.EPIC),
    SHEEP_RED("Sheep: Red", Rarity.RARE),
    SHEEP_RED_BABY("Sheep: Red (Baby)", Rarity.EPIC),
    SHEEP_SILVER("Sheep: Silver", Rarity.COMMON),
    SHEEP_SILVER_BABY("Sheep: Silver (Baby)", Rarity.RARE),
    SHEEP_WHITE("Sheep: White", Rarity.COMMON),
    SHEEP_WHITE_BABY("Sheep: White (Baby)", Rarity.RARE),
    SHEEP_YELLOW("Sheep: Yellow", Rarity.RARE),
    SHEEP_YELLOW_BABY("Sheep: Yellow (Baby)", Rarity.EPIC),
    SHEEP_RAINBOW("Sheep: Rainbow", Rarity.LEGENDARY),
    BOUNCY_SHEEP("Bouncy Sheep", Rarity.EPIC),
    MERRY_SHEEP("Merry Sheep", Rarity.EPIC),
    
    SLIME_BIG("Slime (Big)", Rarity.LEGENDARY),
    SLIME_SMALL("Slime (Small)", Rarity.EPIC),
    SLIME_TINY("Slime (Tiny)", Rarity.RARE),
    MAGMA_CUBE_BIG("Magma Cube (Big)", Rarity.LEGENDARY),
    MAGMA_CUBE_SMALL("Magma Cube (Small)", Rarity.EPIC),
    MAGMA_CUBE_TINY("Magma Cube (Tiny)", Rarity.EPIC),
    
    SKELETON("Skeleton", Rarity.EPIC),
    WITHER_SKELETON("Wither Skeleton", Rarity.LEGENDARY),
    FROZEN_SKELETON("Frozen Skeleton", Rarity.EPIC),
    SMOLDERING_SKELETON("Smoldering Skeleton", Rarity.EPIC),
    
    GUARDIAN("Guardian", Rarity.RARE),
    ELDER_GUARDIAN("Elder Guardian", Rarity.LEGENDARY),

    SQUID("Squid"),
    FLYING_SQUID("Flying Squid", Rarity.LEGENDARY);



    public static final PetType[] VALUES = values();

    private final String name;
    private final Rarity rarity;

    PetType(String name) {
        this(name, null);
    }

    PetType(String name, Rarity rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
