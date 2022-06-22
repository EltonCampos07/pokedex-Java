package br.com.pokedex;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import br.com.pokedex.network.HTTPRequest;

public class App {
	
	public static String urlGeral = "https://pokeapi.co/api/v2/pokemon?limit=151"; 
	
	public static void mostraPokemon (JSONArray pokemon, int index) throws JSONException {
		
		JSONObject pokeObject = pokemon.getJSONObject(index);
		String UrlAuxiliar = pokeObject.getString("url");
		
		JSONObject pokemonEscolhido = new HTTPRequest().requestGetMethod(UrlAuxiliar);
		
		System.out.println("\nO nome do Pokemon escolhido: " + pokemonEscolhido.get("name"));
		
		System.out.println("\nOs tipos:\n ");
		
		JSONArray Tipos = pokemonEscolhido.getJSONArray("types");
		
		for (int i = 0; i< Tipos.length(); i++) {
			System.out.println(Tipos.getJSONObject(i).getJSONObject("type").getString("name"));
		}
		
		System.out.println("\nStatus:\n ");
		
		JSONArray Status = pokemonEscolhido.getJSONArray("stats");
		
		for (int i = 0; i< Status.length(); i++) {
			
			System.out.println(Status.getJSONObject(i).getInt("base_stat") + " - " + Status.getJSONObject(i).getJSONObject("stat").getString("name"));

		}
		
		System.out.println("\nHabilidades:\n ");
		
		JSONArray Habilidades = pokemonEscolhido.getJSONArray("abilities");
		
		for (int i = 0; i< Habilidades.length(); i++) {
			
			System.out.println(Habilidades.getJSONObject(i).getJSONObject("ability").getString("name"));
		}
		
		System.out.println("\nMovimentos:\n ");
		
		JSONArray Movimentos = pokemonEscolhido.getJSONArray("moves");
		
		for (int i = 0; i< Movimentos.length(); i++) {
			
			System.out.println(Movimentos.getJSONObject(i).getJSONObject("move").getString("name"));
		}
		
		System.out.println("\nImagem do Pokemon: ");

		Img2Ascii imagem = new Img2Ascii();
		imagem.convertToAscii(pokemonEscolhido.getJSONObject("sprites").getString("front_default"));
			
	}
	

	
    public static void main(String[] args) throws Exception {
    	
		int option; 
		Scanner scanner = new Scanner(System.in);

		JSONObject object = new HTTPRequest()
		.requestGetMethod(urlGeral);


		do{

			System.out.println("\nPOKEDEX - 151 POKEMONS DISPONIVEIS PARA ESCOLHER:\n ");

			JSONArray array = object.getJSONArray("results");

			for (int index = 0; index < array.length(); index++) {

				JSONObject pokeObject = array.getJSONObject(index);

				String pokeNome = pokeObject.getString("name");
				System.out.println(index+1 + " - " + pokeNome);
			}
	
			System.out.println("\nEscolha um Pokemon!!:\n ");
			System.out.println("Caso nao queira escolher nenhum, selecione opcao 0:\n");
			option = scanner.nextInt();
			
			if(option!=0) mostraPokemon (array, option-1);

		}while(option!=0);
        
        scanner.close();

    }
}




