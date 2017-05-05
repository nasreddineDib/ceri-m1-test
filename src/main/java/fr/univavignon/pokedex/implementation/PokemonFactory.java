/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import java.io.Serializable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;
import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonFactory implements IPokemonFactory, Serializable {

	private static final long serialVersionUID = 21528211181680756L;
	private static PokemonFactory INSTANCE;

	private PokemonFactory() {}

	public static synchronized PokemonFactory getInstance() {
		return INSTANCE == null?new PokemonFactory():INSTANCE;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadataProvider pmdtp;
		PokemonMetadata pmdt;
		try {
			pmdtp = PokemonMetadataProvider.getInstance();		
			pmdt = pmdtp.getPokemonMetadata(index);
		} catch (PokedexException e) {
			return null;
		}
		return calculIV(index, 
				pmdt.getName(),
				pmdt.getAttack(), 
				pmdt.getDefense(),
				pmdt.getStamina(), 
				cp,
				hp,
				dust,
				candy);
	}

	/**
	 * 
	 * @param name
	 * @param cp
	 * @param hp
	 * @param dust
	 * @return
	 */
	private Pokemon calculIV(//NOPMD
			int index,
			String name,
			int attack,
			int defense,
			int stamina,
			int cp,
			int hp,
			int dust,
			int candy) {
		final String url = "https://pokeassistant.com/main/ivcalculator?locale=en";
		ChromeDriverManager.getInstance().setup();
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(url);

		webDriver.findElement(By.xpath("//*[@id=\"search_pokemon_name\"]")).sendKeys(name);
		webDriver.findElement(By.xpath("//*[@class=\"tt-dataset tt-dataset-0\"]")).click();
		webDriver.findElement(By.xpath("//*[@id=\"search_cp\"]")).sendKeys(Integer.toString(cp));
		webDriver.findElement(By.xpath("//*[@id=\"search_hp\"]")).sendKeys(Integer.toString(hp));
		webDriver.findElement(By.xpath("//*[@id=\"search_dust\"]")).sendKeys(Integer.toString(dust));
		webDriver.findElement(By.xpath("//*[@id=\"calculatebtn\"]")).click();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
		}
		String iv = webDriver.findElement(By.xpath("//*[@id=\"possibleCombinationsStringmax\"]//b")).getText();
		webDriver.quit();
		return new Pokemon(index,name,attack,defense,stamina,cp,hp,dust,candy,Math.round(Float.parseFloat(iv.replace("%", ""))));
	}

}
