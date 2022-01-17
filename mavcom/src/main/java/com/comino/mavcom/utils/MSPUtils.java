package com.comino.mavcom.utils;

public class MSPUtils {

	public static boolean isChanged(int o, int n, int mask, boolean edge) {
		if (edge)
			return ((o & mask) != (n & mask) && ((n & mask) == mask));
		return ((o & mask) != (n & mask) && ((n & mask) == 0));
	}

}
