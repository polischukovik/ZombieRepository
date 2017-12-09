
import javafx.geometry.Point2D;

import java.util.Objects;

abstract class Character extends Movable {

	public int  id;

	public Character(int id, Point2D position) {
		super();
		this.id = id;
		this.position = position;
		System.out.println(this.toString());
	}


	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Character character = (Character) o;
		return id == character.id;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", position=" + position + ", speed=" + getSpeed() + "]";
	}

}