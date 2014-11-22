package mw.fmp_manipulation;

import net.minecraft.nbt.NBTTagCompound;

public interface IMultiPartManipulator {

	public void rotate90(NBTTagCompound data);

	public void rotate180(NBTTagCompound data);

	public void rotate270(NBTTagCompound data);

	public void mirrorX(NBTTagCompound data);

	public void mirrorZ(NBTTagCompound data);
}
