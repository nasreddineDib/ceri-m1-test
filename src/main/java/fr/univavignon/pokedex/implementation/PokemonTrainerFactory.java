/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.univavignon.pokedex.api.IPokedex;
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
		try{
			return serializedTrainersFile.exists()?recupererTrainer(serializedTrainersFile):creerTrainer(name, team, pokedexFactory, serializedTrainersFile);
		}catch(ClassNotFoundException | IOException | PokedexException e){
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param team
	 * @param pokedexFactory
	 * @param serializedTrainersFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws PokedexException
	 */
	private PokemonTrainer creerTrainer(String name, Team team, IPokedexFactory pokedexFactory, File serializedTrainersFile) throws FileNotFoundException, IOException, PokedexException{
		PokemonTrainer trainer = null;
		IPokedex pokedex = pokedexFactory.createPokedex(PokemonMetadataProvider.getInstance(), PokemonFactory.getInstance());
		trainer = new PokemonTrainer(name, team, pokedex);
		pokedex.setPokemonTrainer(trainer);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedTrainersFile));
		oos.writeObject(trainer) ;
		oos.close();
		return trainer;
	}

	/**
	 * 
	 * @param serializedTrainersFile
	 * @return
	 */
	private PokemonTrainer recupererTrainer(File serializedTrainersFile)throws ClassNotFoundException, IOException {
		ObjectInputStream objectInputStream;
		PokemonTrainer trainer = null;
		objectInputStream = new ObjectInputStream(new FileInputStream(serializedTrainersFile));
		trainer =(PokemonTrainer)objectInputStream.readObject();
		objectInputStream.close();
		return trainer;
	}

}
