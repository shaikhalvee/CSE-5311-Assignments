package commons;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {
	private K key;
	private V value;

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

	public Pair(@NamedArg("key") K key, @NamedArg("value") V value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		String var10000 = String.valueOf(this.key);
		return var10000 + "=" + String.valueOf(this.value);
	}

	public int hashCode() {
		int var1 = 7;
		var1 = 31 * var1 + (this.key != null ? this.key.hashCode() : 0);
		var1 = 31 * var1 + (this.value != null ? this.value.hashCode() : 0);
		return var1;
	}

	public boolean equals(Object var1) {
		if (this == var1) {
			return true;
		} else if (!(var1 instanceof Pair)) {
			return false;
		} else {
			Pair var2 = (Pair)var1;
			if (this.key != null) {
				if (!this.key.equals(var2.key)) {
					return false;
				}
			} else if (var2.key != null) {
				return false;
			}

			if (this.value != null) {
				if (!this.value.equals(var2.value)) {
					return false;
				}
			} else if (var2.value != null) {
				return false;
			}

			return true;
		}
	}
}
