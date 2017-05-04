package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokedexTest {

	@Mock private IPokedex pokedex;
	private int index;
	private int nbrPokemon;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>(151);
	private Pokemon pokemon1 = new Pokemon(0,"Bulbizarre",126,126,90,613,64,4000,4,56);
	private Pokemon pokemon2 = new Pokemon(133,"Aquali",186,168,260,2729,202,5000,4,100);
	
	public IPokedex getPokedex() {return pokedex;}
	public void setPokedex(IPokedex pokedex) {this.pokedex = pokedex;}

	public int getIndex() {return index;}
	public void setIndex(int index) {this.index = index;}

	public int getNbrPokemon() {return nbrPokemon;}
	public void setNbrPokemon(int nbrPokemon) {this.nbrPokemon = nbrPokemon;}

	public List<Pokemon> getPokemons() {return pokemons;}
	public void setPokemons(List<Pokemon> pokemons) {this.pokemons = pokemons;}

	public Pokemon getPokemon1() {return pokemon1;}
	public void setPokemon1(Pokemon pokemon1) {this.pokemon1 = pokemon1;}

	public Pokemon getPokemon2() {return pokemon2;}
	public void setPokemon2(Pokemon pokemon2) {this.pokemon2 = pokemon2;}

	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);
		index = 0;
		nbrPokemon = 0;
		//Ajout du premier pokemon
		Mockito.when(this.getPokedex().addPokemon(this.getPokemon2())).thenReturn(index++);
		this.getPokemons().add(this.getPokemon2());

		//Ajout du deuxieme pokemon
		Mockito.when(this.getPokedex().addPokemon(this.getPokemon1())).thenReturn(index++);
		pokemons.add(this.getPokemon1());

		Mockito.when(this.getPokedex().getPokemon(0)).thenReturn(this.getPokemon1());
		Mockito.when(this.getPokedex().getPokemon(1)).thenReturn(this.getPokemon2());
		Mockito.when(this.getPokedex().getPokemon(-1)).thenThrow(new PokedexException("Aucun pokemon de cet id n'est pas dans le pokedex!"));
		Mockito.when(this.getPokedex().getPokemon(300)).thenThrow(new PokedexException("Aucun pokemon de cet id n'est pas dans le pokedex!"));

		Mockito.when(this.getPokedex().size()).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return nbrPokemon;
			}
		});
		
		Mockito.when(this.getPokedex().addPokemon(this.getPokedex().createPokemon(0, 613, 64, 4000, 4))).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return nbrPokemon;
			}
		});
		Mockito.when(this.getPokedex().addPokemon(this.getPokedex().createPokemon(133, 1984, 172, 3500, 4))).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return nbrPokemon;
			}
		});

		Mockito.when(this.getPokedex().getPokemons()).thenReturn(pokemons);
	}

	@Test(expected=PokedexException.class)
	public void testPokemonException1() throws PokedexException{
		this.getPokedex().getPokemon(-1);
	}

	@Test(expected=PokedexException.class)
	public void testPokemonException2() throws PokedexException{
		this.getPokedex().getPokemon(300);
	}

	@Test
	public void addPokemonTest() {
		assertEquals(this.getPokedex().addPokemon(this.getPokemon2()), 0);
		assertEquals(this.getPokedex().addPokemon(this.getPokemon1()), 1);
	}
	@Test
	public void getPokemonTest() throws PokedexException {
		this.getPokedex().addPokemon(pokemon1);
		assertEquals(this.getPokedex().getPokemon(0), this.getPokemon1());
		this.getPokedex().addPokemon(pokemon2);
		assertEquals(this.getPokedex().getPokemon(1), this.getPokemon2());
	}
	@Test
	public void getPokemonsTest(){
		List<Pokemon> pokemons = this.getPokedex().getPokemons();
		assertEquals(this.getPokemons(), pokemons);
	}
	@Test
	public void sizeTest() {
		assertEquals(0,this.getPokedex().size());
		assertEquals(0, this.mockAddModifier(this.getPokedex().createPokemon(0, 613, 64, 4000, 4)));
		assertEquals(1,this.getPokedex().size());
		assertEquals(1, this.mockAddModifier(this.getPokedex().createPokemon(133, 1984, 172, 3500, 4)));
	}

	public int mockAddModifier(Pokemon pokemon){
		int res = this.getPokedex().addPokemon(pokemon);
		index++;
		nbrPokemon++;
		return res;
	}
}
