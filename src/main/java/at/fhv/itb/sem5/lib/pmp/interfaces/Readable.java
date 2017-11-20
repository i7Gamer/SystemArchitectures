package at.fhv.itb.sem5.lib.pmp.interfaces;

import java.io.StreamCorruptedException;

public interface Readable<T>  {
	public T read() throws StreamCorruptedException;
}
