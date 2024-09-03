import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
	public static void main(String[] args) {
		int[] ara = {3, 0, -4, 2, 7, 5, 4, 34, -3, -1, 0, 8, 9, 22, 11, 2, 7, 8};
		CommonMethods.swapValues(ara[0], ara[4]);
//		int[] newArray = new int[ara.length];
//		System.arraycopy(ara, 0, newArray, 0, ara.length);
//		newArray[0] = 5;
		System.out.println(Arrays.toString(ara));
	}
}
