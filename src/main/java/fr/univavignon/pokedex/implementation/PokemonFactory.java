/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonFactory implements IPokemonFactory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 21528211181680756L;
	private static PokemonFactory INSTANCE;

	private PokemonFactory() {}
	
	public static synchronized PokemonFactory getInstance() {
		return INSTANCE == null?new PokemonFactory():INSTANCE;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

		PokemonMetadataProvider pmdtp;

		try {
			pmdtp = PokemonMetadataProvider.getInstance();
		} catch (PokedexException e) {
			e.printStackTrace();
			return null;
		}

		PokemonMetadata pmdt;

		try {
			pmdt = pmdtp.getPokemonMetadata(index);
		} catch (PokedexException e) {
			e.printStackTrace();
			return null;
		}

		String name = pmdt.getName();
		Pokemon pokemon = new Pokemon(index, pmdt.getName(), pmdt.getAttack(), pmdt.getDefense(), pmdt.getStamina(), cp, hp, dust, candy, calculIV(name, cp, dust));
		return pokemon;
	}

	/**
	 * @param name
	 * @param cp
	 * @param dust
	 * @return
	 */
	private double calculIV(String name, int cp, int dust) {
		// TODO Temporaire a changer par selenium
		return 56.0;
	}

}
