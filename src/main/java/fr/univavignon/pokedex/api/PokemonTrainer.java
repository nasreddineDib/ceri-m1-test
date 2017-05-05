package fr.univavignon.pokedex.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Trainer POJO.
 * 
 * @author fv
 */
public class PokemonTrainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5676039693652862951L;

	/** Trainer name. **/
	private final String name;

	/** Trainer team. **/
	private final Team team;

	/** Trainer pokedex. **/
	private final IPokedex pokedex;

	/**
	 * Default constructor.
	 * 
	 * @param name Trainer name.
	 * @param team Trainer team.
	 * @param pokedex Trainer pokedex.
	 */
	public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
		this.name = name;
		this.team = team;
		this.pokedex = pokedex;
	}

	/** Name getter. **/
	public String getName() {
		return name;
	}

	/** Team getter. **/
	public Team getTeam() {
		return team;
	}

	/** Pokedex getter. **/
	public IPokedex getPokedex() {
		return pokedex;
	}

	public void updateSerializedOBJ() {
		final String FILE_PATH = "."+File.separator+"src"+File.separator+"serialized"+File.separator+name+".ser";
		File serializedTrainersFile = new File(FILE_PATH);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedTrainersFile));
			oos.writeObject(this) ;
			oos.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

}
