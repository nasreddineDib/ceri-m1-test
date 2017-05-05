/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	private static PokemonTrainerFactory INSTANCE;

	private PokemonTrainerFactory() {}

	public static synchronized PokemonTrainerFactory getInstance() {
		return INSTANCE == null?new PokemonTrainerFactory():INSTANCE;
	}

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {

		final String FILE_PATH = "."+File.separator+"src"+File.separator+"serialized"+File.separator+name+".ser";
		File serializedTrainersFile = new File(FILE_PATH);
		if(serializedTrainersFile.exists()){
			return recupererTrainer(serializedTrainersFile);
		}else{
			try {
				return creerTrainer(name, team, pokedexFactory, serializedTrainersFile);
			} catch (PokedexException e) {
				e.printStackTrace();
				return null;
			}

		}
	}

	/**
	 * @return 
	 * @throws PokedexException 
	 * @throws IOException 
	 * 
	 */
	private PokemonTrainer creerTrainer(String name, Team team, IPokedexFactory pokedexFactory, File serializedTrainersFile) throws PokedexException {
		PokemonTrainer trainer = new PokemonTrainer(name, team, pokedexFactory.createPokedex(PokemonMetadataProvider.getInstance(), PokemonFactory.getInstance()));
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(serializedTrainersFile));
			oos.writeObject(trainer) ;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return trainer;
	}

	/**
	 * @return 
	 * 
	 */
	private PokemonTrainer recupererTrainer(final File serializedTrainersFile) {
		ObjectInputStream objectInputStream;
		PokemonTrainer trainer = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(serializedTrainersFile));
			trainer =(PokemonTrainer)objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return trainer;
	}

}
