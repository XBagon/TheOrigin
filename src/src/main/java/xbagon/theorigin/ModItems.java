package xbagon.theorigin;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xbagon.theorigin.items.ItemOriginPearl;

public class ModItems {

    public static Item originPearl;

    public void init() {
        originPearl = new ItemOriginPearl();
        NameUtils.setNames(originPearl, "originPearl");
    }

    public void register() {
        registerItem(originPearl);
    }

    private void registerItem(Item item) {
        GameRegistry.register(item);
    }

}
