package mw.fmp_manipulation;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;

public class DefaultFMPManipulators {

	public abstract static class Multipart implements IMultiPartManipulator {

		protected final long getNumber(String tagName, NBTTagCompound data) {
			NBTBase tag = data.getTag(tagName);
			if (tag != null) {
				switch (tag.getId()) {
				case 1:
					return ((NBTTagByte) tag).data;
				case 2:
					return ((NBTTagShort) tag).data;
				case 3:
					return ((NBTTagInt) tag).data;
				case 4:
					return ((NBTTagLong) tag).data;
				}
			}
			return 0;
		}

		protected final void setNumber(String tagName, long number, NBTTagCompound data) {
			switch (data.getTag(tagName).getId()) {
			case 1:
				if (number >= Byte.MIN_VALUE && number <= Byte.MAX_VALUE) {
					data.setByte(tagName, (byte) number);
					break;
				}
			case 2:
				if (number >= Short.MIN_VALUE && number <= Short.MAX_VALUE) {
					data.setShort(tagName, (short) number);
					break;
				}
			case 3:
				if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
					data.setInteger(tagName, (int) number);
					break;
				}
			case 4:
				data.setLong(tagName, number);
			}
		}
	}

	public static class Face extends Multipart {

		protected static final byte	BOTTOM	= 0;
		protected static final byte	TOP	= 1;
		protected static final byte	NORTH	= 2;
		protected static final byte	SOUTH	= 3;
		protected static final byte	WEST	= 4;
		protected static final byte	EAST	= 5;

		protected String		tagName	= "shape";

		@Override
		public void rotate90(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~7);
			switch ((byte) (number & 7)) {
			case NORTH:
				this.setNumber(this.tagName, rest | EAST, data);
				break;
			case SOUTH:
				this.setNumber(this.tagName, rest | WEST, data);
				break;
			case WEST:
				this.setNumber(this.tagName, rest | NORTH, data);
				break;
			case EAST:
				this.setNumber(this.tagName, rest | SOUTH, data);
				break;
			}
		}

		@Override
		public void rotate180(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~7);
			switch ((byte) (number & 7)) {
			case NORTH:
				this.setNumber(this.tagName, rest | SOUTH, data);
				break;
			case SOUTH:
				this.setNumber(this.tagName, rest | NORTH, data);
				break;
			case WEST:
				this.setNumber(this.tagName, rest | EAST, data);
				break;
			case EAST:
				this.setNumber(this.tagName, rest | WEST, data);
				break;
			}
		}

		@Override
		public void rotate270(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~7);
			switch ((byte) (number & 7)) {
			case NORTH:
				this.setNumber(this.tagName, rest | WEST, data);
				break;
			case SOUTH:
				this.setNumber(this.tagName, rest | EAST, data);
				break;
			case WEST:
				this.setNumber(this.tagName, rest | SOUTH, data);
				break;
			case EAST:
				this.setNumber(this.tagName, rest | NORTH, data);
				break;
			}
		}

		@Override
		public void mirrorX(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~7);
			switch ((byte) (number & 7)) {
			case WEST:
				this.setNumber(this.tagName, rest | EAST, data);
				break;
			case EAST:
				this.setNumber(this.tagName, rest | WEST, data);
				break;
			}
		}

		@Override
		public void mirrorZ(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~7);
			switch ((byte) (number & 7)) {
			case NORTH:
				this.setNumber(this.tagName, rest | SOUTH, data);
				break;
			case SOUTH:
				this.setNumber(this.tagName, rest | NORTH, data);
				break;
			}
		}
	}

	public static class Post extends Multipart {

		private static final byte	UD	= 0;
		private static final byte	NS	= 1;
		private static final byte	WE	= 2;

		protected String		tagName	= "shape";

		@Override
		public void rotate90(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~3);
			switch ((byte) (number & 3)) {
			case NS:
				this.setNumber(this.tagName, rest | WE, data);
				break;
			case WE:
				this.setNumber(this.tagName, rest | NS, data);
				break;
			}
		}

		@Override
		public void rotate180(NBTTagCompound data) {
		}

		@Override
		public void rotate270(NBTTagCompound data) {
			this.rotate90(data);
		}

		@Override
		public void mirrorX(NBTTagCompound data) {
		}

		@Override
		public void mirrorZ(NBTTagCompound data) {
		}

	}

	public static class Edge extends Multipart {

		private static final byte	Y_NW	= 0;
		private static final byte	Y_SW	= 1;
		private static final byte	Y_NE	= 2;
		private static final byte	Y_SE	= 3;
		private static final byte	Z_DW	= 4;
		private static final byte	Z_DE	= 5;
		private static final byte	Z_UW	= 6;
		private static final byte	Z_UE	= 7;
		private static final byte	X_DN	= 8;
		private static final byte	X_UN	= 9;
		private static final byte	X_DS	= 10;
		private static final byte	X_US	= 11;

		protected String		tagName	= "shape";

		@Override
		public void rotate90(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~15);
			switch ((byte) (number & 15)) {
			case Y_NW:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case Y_SW:
				this.setNumber(this.tagName, rest | Y_NW, data);
				break;
			case Y_NE:
				this.setNumber(this.tagName, rest | Y_SE, data);
				break;
			case Y_SE:
				this.setNumber(this.tagName, rest | Y_SW, data);
				break;
			case Z_DW:
				this.setNumber(this.tagName, rest | X_DN, data);
				break;
			case Z_DE:
				this.setNumber(this.tagName, rest | X_DS, data);
				break;
			case Z_UW:
				this.setNumber(this.tagName, rest | X_UN, data);
				break;
			case Z_UE:
				this.setNumber(this.tagName, rest | X_US, data);
				break;
			case X_DN:
				this.setNumber(this.tagName, rest | Z_DE, data);
				break;
			case X_DS:
				this.setNumber(this.tagName, rest | Z_DW, data);
				break;
			case X_UN:
				this.setNumber(this.tagName, rest | Z_UE, data);
				break;
			case X_US:
				this.setNumber(this.tagName, rest | Z_UW, data);
				break;
			}
		}

		@Override
		public void rotate180(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~15);
			switch ((byte) (number & 15)) {
			case Y_NW:
				this.setNumber(this.tagName, rest | Y_SE, data);
				break;
			case Y_SW:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case Y_NE:
				this.setNumber(this.tagName, rest | Y_SW, data);
				break;
			case Y_SE:
				this.setNumber(this.tagName, rest | Y_NW, data);
				break;
			case Z_DW:
				this.setNumber(this.tagName, rest | Z_DE, data);
				break;
			case Z_DE:
				this.setNumber(this.tagName, rest | Z_DW, data);
				break;
			case Z_UW:
				this.setNumber(this.tagName, rest | Z_UE, data);
				break;
			case Z_UE:
				this.setNumber(this.tagName, rest | Z_UW, data);
				break;
			case X_DN:
				this.setNumber(this.tagName, rest | X_DS, data);
				break;
			case X_DS:
				this.setNumber(this.tagName, rest | X_DN, data);
				break;
			case X_UN:
				this.setNumber(this.tagName, rest | X_US, data);
				break;
			case X_US:
				this.setNumber(this.tagName, rest | X_UN, data);
				break;
			}
		}

		@Override
		public void rotate270(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~15);
			switch ((byte) (number & 15)) {
			case Y_NW:
				this.setNumber(this.tagName, rest | Y_SW, data);
				break;
			case Y_SW:
				this.setNumber(this.tagName, rest | Y_SE, data);
				break;
			case Y_NE:
				this.setNumber(this.tagName, rest | Y_NW, data);
				break;
			case Y_SE:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case Z_DW:
				this.setNumber(this.tagName, rest | X_DS, data);
				break;
			case Z_DE:
				this.setNumber(this.tagName, rest | X_DN, data);
				break;
			case Z_UW:
				this.setNumber(this.tagName, rest | X_US, data);
				break;
			case Z_UE:
				this.setNumber(this.tagName, rest | X_UN, data);
				break;
			case X_DN:
				this.setNumber(this.tagName, rest | Z_DW, data);
				break;
			case X_DS:
				this.setNumber(this.tagName, rest | Z_DE, data);
				break;
			case X_UN:
				this.setNumber(this.tagName, rest | Z_UW, data);
				break;
			case X_US:
				this.setNumber(this.tagName, rest | Z_UE, data);
				break;
			}
		}

		@Override
		public void mirrorX(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~15);
			switch ((byte) (number & 15)) {
			case Y_NW:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case Y_SW:
				this.setNumber(this.tagName, rest | Y_SE, data);
				break;
			case Y_NE:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case Y_SE:
				this.setNumber(this.tagName, rest | Y_SW, data);
				break;
			case Z_DW:
				this.setNumber(this.tagName, rest | Z_DE, data);
				break;
			case Z_DE:
				this.setNumber(this.tagName, rest | Z_DW, data);
				break;
			case Z_UW:
				this.setNumber(this.tagName, rest | Z_UE, data);
				break;
			case Z_UE:
				this.setNumber(this.tagName, rest | Z_UW, data);
				break;
			}
		}

		@Override
		public void mirrorZ(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~15);
			switch ((byte) (number & 15)) {
			case Y_NW:
				this.setNumber(this.tagName, rest | Y_SW, data);
				break;
			case Y_SW:
				this.setNumber(this.tagName, rest | Y_NW, data);
				break;
			case Y_NE:
				this.setNumber(this.tagName, rest | Y_SE, data);
				break;
			case Y_SE:
				this.setNumber(this.tagName, rest | Y_NE, data);
				break;
			case X_DN:
				this.setNumber(this.tagName, rest | X_DS, data);
				break;
			case X_DS:
				this.setNumber(this.tagName, rest | X_DN, data);
				break;
			case X_UN:
				this.setNumber(this.tagName, rest | X_US, data);
				break;
			case X_US:
				this.setNumber(this.tagName, rest | X_UN, data);
				break;
			}
		}
	}

	public static class Corner extends Multipart {

		// bit 0x1 - 0 = down, 1 = up

		private static final byte	NW	= 0;
		private static final byte	SW	= 2;
		private static final byte	NE	= 4;
		private static final byte	SE	= 6;

		protected String		tagName	= "shape";

		@Override
		public void rotate90(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~6);
			switch ((byte) (number & 6)) {
			case NW:
				this.setNumber(this.tagName, rest | NE, data);
				break;
			case SW:
				this.setNumber(this.tagName, rest | NW, data);
				break;
			case NE:
				this.setNumber(this.tagName, rest | SE, data);
				break;
			case SE:
				this.setNumber(this.tagName, rest | SW, data);
				break;
			}
		}

		@Override
		public void rotate180(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~6);
			switch ((byte) (number & 6)) {
			case NW:
				this.setNumber(this.tagName, rest | SE, data);
				break;
			case SW:
				this.setNumber(this.tagName, rest | NE, data);
				break;
			case NE:
				this.setNumber(this.tagName, rest | SW, data);
				break;
			case SE:
				this.setNumber(this.tagName, rest | NW, data);
				break;
			}
		}

		@Override
		public void rotate270(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~6);
			switch ((byte) (number & 6)) {
			case NW:
				this.setNumber(this.tagName, rest | SW, data);
				break;
			case SW:
				this.setNumber(this.tagName, rest | SE, data);
				break;
			case NE:
				this.setNumber(this.tagName, rest | NW, data);
				break;
			case SE:
				this.setNumber(this.tagName, rest | NE, data);
				break;
			}
		}

		@Override
		public void mirrorX(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~6);
			switch ((byte) (number & 6)) {
			case NW:
				this.setNumber(this.tagName, rest | NE, data);
				break;
			case SW:
				this.setNumber(this.tagName, rest | SE, data);
				break;
			case NE:
				this.setNumber(this.tagName, rest | NW, data);
				break;
			case SE:
				this.setNumber(this.tagName, rest | SW, data);
				break;
			}
		}

		@Override
		public void mirrorZ(NBTTagCompound data) {
			long number = this.getNumber(this.tagName, data);
			long rest = number & (~6);
			switch ((byte) (number & 6)) {
			case NW:
				this.setNumber(this.tagName, rest | SW, data);
				break;
			case SW:
				this.setNumber(this.tagName, rest | NW, data);
				break;
			case NE:
				this.setNumber(this.tagName, rest | SE, data);
				break;
			case SE:
				this.setNumber(this.tagName, rest | NE, data);
				break;
			}
		}
	}

	protected static void registerFMPManipulators() {
		Face face = new Face();
		ForgeMultiPartManipulator.registerMultiPartManipulator("mcr_face", face);
		ForgeMultiPartManipulator.registerMultiPartManipulator("mcr_hllw", face);

		ForgeMultiPartManipulator.registerMultiPartManipulator("mcr_post", new Post());

		ForgeMultiPartManipulator.registerMultiPartManipulator("mcr_edge", new Edge());

		ForgeMultiPartManipulator.registerMultiPartManipulator("mcr_cnr", new Corner());
	}
}
