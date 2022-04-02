package net.tigereye.hellishmaterials.datapack;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public class LussDustLootSerializer {
    public Pair<Identifier, Pair<Identifier,Integer>> read(Identifier id, LussDustLootJsonFormat lootJson) {

        if (lootJson.blockID == null) {
            throw new JsonSyntaxException("Luss Dust Loot entry" + id + " must provide a block");
        }
        if (lootJson.itemID == null && !lootJson.blacklisted) {
            throw new JsonSyntaxException("Luss Dust Loot entry" + id + " must provide a item unless blacklisted");
        }
        //isDrawback will default to false
        //magnitude will probably default to 0, which is ok

        if(lootJson.blacklisted){
            return new Pair<>(new Identifier(lootJson.blockID),new Pair<>(null,0));
        }
        if(lootJson.average == 0){
            lootJson.average = 1;
        }
        return new Pair<>(new Identifier(lootJson.blockID), new Pair<>(new Identifier(lootJson.itemID),lootJson.average));

    }
}
