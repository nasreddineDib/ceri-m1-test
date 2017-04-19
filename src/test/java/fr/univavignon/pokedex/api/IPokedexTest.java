package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.*;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokedexTest {

	@Mock private IPokedex pokedex;
	private int index = 0;
	
	private List<Pokemon> pokemonsArray = new ArrayList<Pokemon>(151);
	private Pokemon bulbi = new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
	private Pokemon aquali = new Pokemon(133,"Aquali",186,168,260,2729,202,5000,4,100);

	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);
		
		//Ajout du premier pokemon
		Mockito.when(pokedex.addPokemon(aquali)).thenReturn(index++);
		pokemonsArray.add(aquali);
		
		//Ajout du deuxieme pokemon
		Mockito.when(pokedex.addPokemon(bulbi)).thenReturn(index++);
		pokemonsArray.add(bulbi);

		Mockito.when(pokedex.getPokemon(0)).thenReturn(aquali);
		Mockito.when(pokedex.getPokemon(1)).thenReturn(bulbi);
		

		Mockito.when(pokedex.size()).thenReturn(index);
		Mockito.when(pokedex.getPokemons()).thenReturn(pokemonsArray);
	}

	@Test
	public void addPokemonTest() {
		assertEquals(pokedex.addPokemon(aquali), 0);
		assertEquals(pokedex.addPokemon(bulbi), 1);
	}
	@Test
	public void getPokemonTest() throws PokedexException {
		assertEquals(pokedex.getPokemon(0), aquali);
		assertEquals(pokedex.getPokemon(1), bulbi);
	}
	@Test
	public void getPokemonsTest(){
		List<Pokemon> pokemons = pokedex.getPokemons();
		assertEquals(pokemonsArray, pokemons);
	}
	@Test
	public void sizeTest() {
		assertEquals(pokedex.size(), 2);
	}
}
