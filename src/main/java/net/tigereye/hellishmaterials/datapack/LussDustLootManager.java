package net.tigereye.hellishmaterials.datapack;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class LussDustLootManager implements SimpleSynchronousResourceReloadListener {

    private static final String RESOURCE_LOCATION = "luss_dust_loot";
    private final LussDustLootSerializer SERIALIZER = new LussDustLootSerializer();
    private static final Map<Identifier, Pair<Identifier,Integer>> knownOutputs = new HashMap<>();
    private static List<ShapelessRecipe> singleBlockRecipes;

    @Override
    public Identifier getFabricId() {
        return new Identifier(HellishMaterials.MODID, RESOURCE_LOCATION);
    }

    @Override
    public void reload(ResourceManager manager) {
        knownOutputs.clear();
        HellishMaterials.LOGGER.info("Loading Lust Dust Loot Entries.");
        for(Identifier id : manager.findResources(RESOURCE_LOCATION, id -> id.getPath().endsWith(".json")).keySet()) {
            var resource = manager.getResource(id);
            if (resource.isEmpty()) {
                HellishMaterials.LOGGER.error("Failed to load resource: " + id);
                continue;
            }
            try(InputStream stream = resource.get().getInputStream()) {
                Reader reader = new InputStreamReader(stream);
                Pair<Identifier,Pair<Identifier,Integer>> flavorDataPair = SERIALIZER.read(id,new Gson().fromJson(reader,LussDustLootJsonFormat.class));
                if(knownOutputs.containsKey(flavorDataPair.getLeft())){
                    HellishMaterials.LOGGER.warn("Duplicate loot entry for " +flavorDataPair.getLeft()+ " found.");
                    HellishMaterials.LOGGER.warn("If either blacklists the item it will be blacklisted, otherwise the first entry read will be kept.");
                    if(flavorDataPair.getRight().getLeft() == null){
                        knownOutputs.put(flavorDataPair.getLeft(), flavorDataPair.getRight());
                    }
                }
                else {
                    knownOutputs.put(flavorDataPair.getLeft(), flavorDataPair.getRight());
                }
            } catch(Exception e) {
                HellishMaterials.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
            }
        }
        HellishMaterials.LOGGER.info("Loaded "+ knownOutputs.size()+" Lust Dust Loot Entries.");
    }

    @Nullable
    public static Pair<Identifier,Integer> getOutput(Identifier block){
        return knownOutputs.get(block);
    }

    public static Pair<Identifier,Integer> getOutput(Identifier block, World world){
        if(knownOutputs.containsKey(block)){
            return knownOutputs.get(block);
        }
        return deriveOutputFromRecipes(block,world);
    }

    private static Pair<Identifier,Integer> deriveOutputFromRecipes(Identifier block, World world){
        if(singleBlockRecipes == null) populateRecipeList(world);
        Pair<Identifier,Integer> output;
        for (ShapelessRecipe recipe:
                singleBlockRecipes) {
            if(recipe.getIngredients().get(0).test(Registry.BLOCK.get(block).asItem().getDefaultStack())){
                ItemStack itemStack = recipe.getOutput();
                output = new Pair<>(Registry.ITEM.getId(itemStack.getItem()),itemStack.getCount());
                knownOutputs.put(block,output);
                return output;
            }
        }
        output = new Pair<>(null,0);
        knownOutputs.put(block,output);
        return output;
    }

    private static void populateRecipeList(World world){
        singleBlockRecipes = new LinkedList<>();
        List<CraftingRecipe> craftingRecipes = world.getRecipeManager().listAllOfType(RecipeType.CRAFTING);
        for (CraftingRecipe recipe:
                craftingRecipes) {
            if(recipe instanceof ShapelessRecipe && recipe.getIngredients().size() == 1) {
                singleBlockRecipes.add((ShapelessRecipe)recipe);
            }
        }
    }
}
