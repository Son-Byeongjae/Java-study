package selfTest;

import java.util.Arrays;

public class MyCustom implements MyInterface {
	private Integer move_type = 0;
	private Boolean isAttack = false;
	
	public void move(String key) {
		if( key.equals("w") ) move_type = 1;
		else if ( key.equals("s") ) move_type = 2;
		else if ( key.equals("a") ) move_type = 3;
		else if ( key.equals("d") ) move_type = 4;
		else move_type = 5;
	}
	public void attack(String key) {
		if( key.equals("spacebar") ) isAttack = true;
		else isAttack = false;
	}
	public void sortItem(Item[] itemList) {
		Arrays.sort(itemList);
	}
	public Integer getMoveType() {
		return new Integer(move_type);
	}
	public Boolean getIsAttack() {
		return new Boolean(isAttack);
	}
}
