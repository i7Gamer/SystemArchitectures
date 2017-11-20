package at.fhv.itb.sem5.lib.pmp.interfaces;

import java.io.StreamCorruptedException;

public interface Writeable<T> {
	public void write(T value) throws StreamCorruptedException;
}
