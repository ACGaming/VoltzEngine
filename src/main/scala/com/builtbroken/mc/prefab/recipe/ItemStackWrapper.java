package com.builtbroken.mc.prefab.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by robert on 1/10/2015.
 */
public class ItemStackWrapper
{
    public final ItemStack itemStack;
    public boolean meta_compare = true;
    public boolean nbt_compare = true;
    public boolean stack_size = false;

    public ItemStackWrapper(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean item = false;
        boolean meta = !meta_compare;
        boolean nbt = !nbt_compare;
        boolean size = !stack_size;
        if (itemStack == null || object == null)
        {
            return false;
        }
        else if (object instanceof ItemStack || object instanceof ItemStackWrapper)
        {
            ItemStack i = object instanceof ItemStack ? (ItemStack) object : ((ItemStackWrapper) object).itemStack;
            item = itemStack.isItemEqual(i);
            if (meta_compare)
                meta = itemStack.getItemDamage() == i.getItemDamage();
            if (stack_size)
                size = itemStack.stackSize == i.stackSize;
            if (nbt_compare)
            {
                if (itemStack.getTagCompound() == null && i.getTagCompound() == null)
                {
                    nbt = true;
                }
                else if (itemStack.getTagCompound() != null && i.getTagCompound() == null)
                {
                    nbt = false;
                }
                else if (itemStack.getTagCompound() == null && i.getTagCompound() != null)
                {
                    nbt = false;
                } //TODO this may need to be manually done
                else if (itemStack.getTagCompound().equals(i.getTagCompound()))
                {
                    nbt = true;
                }
            }
        }
        else if (meta_compare == false && nbt_compare == false && stack_size == false)
        {
            if (object instanceof Item)
            {
                item = itemStack.getItem() == (Item) object;
            }
            else if (object instanceof Block)
            {
                item = itemStack.getItem() == Item.getItemFromBlock((Block) object);
            }
        }

        return item && meta && nbt && size;
    }
}