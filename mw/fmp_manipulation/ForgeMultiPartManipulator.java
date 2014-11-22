package mw.fmp_manipulation;

import java.util.HashMap;
import java.util.Map;

import mw.library.Blocks;
import mw.library.IBlockManipulator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ForgeMultiPartManipulator implements IBlockManipulator {

	private static final Map<String, IMultiPartManipulator> manipulators = new HashMap<String, IMultiPartManipulator>();
	private static final int R90 = 0;
	private static final int R180 = 1;
	private static final int R270 = 2;
	private static final int MX = 3;
	private static final int MZ = 4;
	
	public static final boolean registerMultiPartManipulator(String id, IMultiPartManipulator manipulator) {
		if (manipulators.containsKey(id)) {
			return false;
		}
		manipulators.put(id, manipulator);
		return true;
	}
	
	private Blocks manipulate(Blocks block, int mode) {
		NBTTagList parts = block.nbtData.getTagList("parts");
		for (int i = 0; i < parts.tagCount(); i++) {
			NBTTagCompound part = (NBTTagCompound) parts.tagAt(i);
			IMultiPartManipulator manipulator = manipulators.get(part.getString("id"));
			if (manipulator != null) {
				switch (mode) {
				case R90:
					manipulator.rotate90(part);
					break;
				case R180:
					manipulator.rotate180(part);
					break;
				case R270:
					manipulator.rotate270(part);
					break;
				case MX:
					manipulator.mirrorX(part);
					break;
				case MZ:
					manipulator.mirrorZ(part);
					break;
				}
			}
		}
		return block;
	}
	
	@Override
	public Blocks rotate90(Blocks block) {
		return this.manipulate(block, R90);
	}

	@Override
	public Blocks rotate180(Blocks block) {
		return this.manipulate(block, R180);
	}

	@Override
	public Blocks rotate270(Blocks block) {
		return this.manipulate(block, R270);
	}

	@Override
	public Blocks mirrorX(Blocks block) {
		return this.manipulate(block, MX);
	}

	@Override
	public Blocks mirrorZ(Blocks block) {
		return this.manipulate(block, MZ);
	}
	
	static {
		DefaultFMPManipulators.registerFMPManipulators();
	}
}
