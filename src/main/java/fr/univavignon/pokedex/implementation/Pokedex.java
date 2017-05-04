package fr.univavignon.pokedex.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * @author Dib Nasreddine
 *
 */
public class Pokedex implements IPokedex, Serializable{

	private static final long serialVersionUID = 3173396585518515389L;
	private Map<Integer, Pokemon> pokemons;
	private IPokemonMetadataProvider pmdtp;
	private IPokemonFactory pokemonFactory;

	
	public Map<Integer, Pokemon> getPokemonsMap() {return pokemons;}
	public void setPokemons(Map<Integer, Pokemon> pokemons) {this.pokemons = pokemons;}

	public IPokemonMetadataProvider getPmdtp() {return pmdtp;}
	public void setPmdtp(IPokemonMetadataProvider pmdtp) {this.pmdtp = pmdtp;}

	public IPokemonFactory getPokemonFactory() {return pokemonFactory;}
	public void setPokemonFactory(IPokemonFactory pokemonFactory) {this.pokemonFactory = pokemonFactory;}

	/**
	 * Constructeur par defaut
	 * @throws PokedexException 
	 */
	public Pokedex() throws PokedexException{
		this.setPokemons(new HashMap<Integer, Pokemon>());
		this.setPmdtp(PokemonMetadataProvider.getInstance());
		this.setPokemonFactory(PokemonFactory.getInstance());
	}

	/**
	 * Constructeur avec aruments
	 * @param pokemonFactory
	 * @param pokemonMetadataProvider
	 */
	public Pokedex(IPokemonFactory pokemonFactory,IPokemonMetadataProvider pokemonMetadataProvider) {
		this.setPokemons(new HashMap<Integer, Pokemon>());
		this.setPokemonFactory(pokemonFactory);
		this.setPmdtp(pokemonMetadataProvider);
	}

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return this.getPmdtp().getPokemonMetadata(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return this.getPokemonFactory().createPokemon(index, cp, hp, dust, candy);
	}

	@Override
	public int size() {
		return this.getPokemonsMap().size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		this.getPokemonsMap().put(this.getPokemons().size(), pokemon);
		return (this.getPokemons().size()-1);
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		if(!this.getPokemonsMap().containsKey(id))
			throw new PokedexException("Aucun pokemon de cet id n'est pas dans le pokedex!");
		return this.getPokemonsMap().get(id);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return new ArrayList<>(this.getPokemonsMap().values());
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>(this.getPokemonsMap().values());
		Collections.sort(pokemons, order);
		return pokemons;
	}
}
