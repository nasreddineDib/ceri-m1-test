package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokemonTrainerFactoryTest {

	@Mock private IPokemonTrainerFactory pokemonTrainerFactory;
	@Mock private IPokedex pokedex;
	@Mock private IPokedexFactory pokedexFactory;
	@Mock private PokemonTrainer spark;
	@Mock private PokemonTrainer blanche;
	@Mock private PokemonTrainer candela;

	public void setSpark(PokemonTrainer Spark) {this.spark = Spark;}
	public void setBlanche(PokemonTrainer blanche) {this.blanche = blanche;}
	public void setCandela(PokemonTrainer candela) {this.candela = candela;}
	public IPokemonTrainerFactory getPokemonTrainerFactory() {return pokemonTrainerFactory;}
	public void setPokemonTrainerFactory(IPokemonTrainerFactory pokemonTrainerFactory) {this.pokemonTrainerFactory = pokemonTrainerFactory;}
	public IPokedexFactory getPokedexFactory() {return pokedexFactory;}
	public void setPokedexFactory(IPokedexFactory pokedexFactory) {this.pokedexFactory = pokedexFactory;}

	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);

		//On initialise le leader de la INSTINCT
		setSpark(new PokemonTrainer("Spark", Team.INSTINCT,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Spark", Team.INSTINCT,  getPokedexFactory())).thenReturn(spark);

		//On initialise le leader de la team MYSTIC
		setBlanche(new PokemonTrainer("Blanche", Team.MYSTIC,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Blanche", Team.MYSTIC,  getPokedexFactory())).thenReturn(blanche);

		//On initialise le leader de la VALOR
		setCandela(new PokemonTrainer("Candela", Team.VALOR,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Candela", Team.VALOR,  getPokedexFactory())).thenReturn(candela);
	}


	@Test
	public void createTrainerTest() {
		assertEquals(spark, pokemonTrainerFactory.createTrainer("Spark", Team.INSTINCT,  getPokedexFactory()));
		assertEquals(blanche, pokemonTrainerFactory.createTrainer("Blanche", Team.MYSTIC,  getPokedexFactory()));
		assertEquals(candela, pokemonTrainerFactory.createTrainer("Candela", Team.VALOR,  getPokedexFactory()));

		//Team INSTINCT
		PokemonTrainer sparkTrainer = getPokemonTrainerFactory().createTrainer("Spark", Team.INSTINCT, getPokedexFactory());
		assertEquals("Spark", sparkTrainer.getName());
		assertEquals(0, sparkTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.INSTINCT, sparkTrainer.getTeam());

		//Team MYSTIC
		PokemonTrainer blancheTrainer = getPokemonTrainerFactory().createTrainer("Blanche", Team.MYSTIC, getPokedexFactory());
		assertEquals("Blanche", blancheTrainer.getName());
		assertEquals(0, blancheTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.MYSTIC, blancheTrainer.getTeam());

		//Team VALOR
		PokemonTrainer candelaTrainer = getPokemonTrainerFactory().createTrainer("Candela", Team.VALOR, getPokedexFactory());
		assertEquals("Candela", candelaTrainer.getName());
		assertEquals(0, candelaTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.VALOR, candelaTrainer.getTeam());
	}

}
