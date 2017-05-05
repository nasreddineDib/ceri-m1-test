/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * @author Dib Nasreddine
 *
 */
public class PokedexFactory implements IPokedexFactory{

	private static PokedexFactory INSTANCE;
	
	private PokedexFactory(){}
	
	public static synchronized PokedexFactory getInstance() {
		return INSTANCE == null?new PokedexFactory():INSTANCE;
	}

	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		return new Pokedex(pokemonFactory, metadataProvider);
	}

}
