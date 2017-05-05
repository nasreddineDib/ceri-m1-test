package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.Mockito;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokemonFactoryTest {
	@Mock 
	private IPokemonFactory pokemonFactory;
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	
	public IPokemonFactory getPokemonFactory() {return pokemonFactory;}
	public void setPokemonFactory(IPokemonFactory pokemonFactory) {this.pokemonFactory = pokemonFactory;	}
	
	public void setPokemon1(Pokemon pokemon1) {this.pokemon1 = pokemon1;}
	public void setPokemon2(Pokemon pokemon2) {this.pokemon2 = pokemon2;}
	
	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);
		pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
		pokemon2 = new Pokemon(134, "Jolteon", 192, 174, 130, 613, 64, 4000, 0, 56);
		Mockito.when(getPokemonFactory().createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon1);
		Mockito.when(getPokemonFactory().createPokemon(134, 613, 64, 4000, 0)).thenReturn(pokemon2);
	}

	@Test
	public void testPokemon1() {
		Pokemon p = getPokemonFactory().createPokemon(0, 613, 64, 4000, 4);
		assertEquals(pokemon1.getIndex(), p.getIndex());
		assertEquals(pokemon1.getName(), p.getName());
		assertEquals(pokemon1.getAttack(), p.getAttack());
		assertEquals(pokemon1.getDefense(), p.getDefense());
		assertEquals(pokemon1.getStamina(), p.getStamina());
		assertEquals(pokemon1.getCandy(), p.getCandy());
		assertEquals(pokemon1.getCp(), p.getCp());
		assertEquals(pokemon1.getHp(), p.getHp());
		assertEquals(pokemon1.getDust(), p.getDust());
		assertEquals(pokemon1.getIv(), p.getIv(),0.001);		
	}
	
	@Test
	public void testPokemon2() {
		Pokemon p = getPokemonFactory().createPokemon(134, 613, 64, 4000, 0);
		assertEquals(pokemon2.getIndex(), p.getIndex());
		assertEquals(pokemon2.getName(), p.getName());
		assertEquals(pokemon2.getAttack(), p.getAttack());
		assertEquals(pokemon2.getDefense(), p.getDefense());
		assertEquals(pokemon2.getStamina(), p.getStamina());
		assertEquals(pokemon2.getCandy(), p.getCandy());
		assertEquals(pokemon2.getCp(), p.getCp());
		assertEquals(pokemon2.getHp(), p.getHp());
		assertEquals(pokemon2.getDust(), p.getDust());
		assertEquals(pokemon2.getIv(), p.getIv(),0.001);		
	}
}