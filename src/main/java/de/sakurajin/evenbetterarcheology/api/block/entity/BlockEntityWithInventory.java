package de.sakurajin.evenbetterarcheology.api.block.entity;

import de.sakurajin.evenbetterarcheology.registry.ModNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@code SidedInventory} implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use {@link Inventories#writeNbt(NbtCompound, DefaultedList)} and {@link Inventories#readNbt(NbtCompound, DefaultedList)}
 * on {@linkplain #getItems() the item list}.
 *
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 * @author Juuz
 */
public abstract class BlockEntityWithInventory extends BlockEntity implements SidedInventory {

    //count of custom slots inside the table
    protected final DefaultedList<ItemStack> inventory;
    public BlockEntityWithInventory(BlockEntityType<?> type, BlockPos pos, BlockState state, DefaultedList<ItemStack> inventory) {
        super(type, pos, state);
        this.inventory = inventory;
    }

    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     *
     * @return the item list
     */
    public DefaultedList<ItemStack> getItems(){
        return inventory;
    }

    // SidedInventory

    /**
     * Gets the available slots to automation on the side.
     *
     * <p>The default implementation returns an array of all slots.
     *
     * @param side the side
     * @return the available slots
     */
    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    /**
     * Returns true if the stack can be inserted in the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be inserted
     */
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return true;
    }

    /**
     * Returns true if the stack can be extracted from the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be extracted
     */
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return true;
    }

    // Inventory

    /**
     * Returns the inventory size.
     *
     * <p>The default implementation returns the size of {@link #getItems()}.
     *
     * @return the inventory size
     */
    @Override
    public int size() {
        return getItems().size();
    }

    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the item in the slot.
     *
     * @param slot the slot
     * @return the item in the slot
     */
    @Override
    public ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    /**
     * Takes a stack of the size from the slot.
     *
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     *
     * @param slot the slot
     * @param count the item count
     * @return a stack
     */
    @Override
    public ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }

        return result;
    }

    /**
     * Removes the current stack in the {@code slot} and returns it.
     *
     * <p>The default implementation uses {@link Inventories#removeStack(List, int)}
     *
     * @param slot the slot
     * @return the removed stack
     */
    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     *
     * <p>If the stack is too big for this inventory ({@link Inventory#getMaxCountPerStack()}),
     * it gets resized to this inventory's maximum amount.
     *
     * @param slot the slot
     * @param stack the stack
     */
    @Override
    public void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }

    public List<ItemStack> getInventoryContents() {
        return Arrays.asList(this.inventory.toArray(new ItemStack[0]));
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    public void clear() {
        getItems().clear();
    }

    @Override
    public void markDirty() {
        transferDataToClients();
        super.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public void transferDataToClients(){
        transferDataToClients(null);
    }

    public void transferDataToClients(PlayerEntity player){
        if(world != null && !world.isClient()) {
            ModNetworking.ItemUpdatePacket packet = new ModNetworking.ItemUpdatePacket(getPos(), getInventoryContents());
            if(player == null) {
                ModNetworking.ARCHEOLOGY_CHANNEL.serverHandle(this).send(packet);
            } else {
                ModNetworking.ARCHEOLOGY_CHANNEL.serverHandle(player).send(packet);
            }
        }
    }

    public void requestDataTransfer(){
        if(world != null){
            ModNetworking.ARCHEOLOGY_CHANNEL.clientHandle().send(new ModNetworking.UpdateRequestPacket(getPos()));
        }
    }
}