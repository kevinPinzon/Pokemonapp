# Pokemonapp

Para abrir la aplicación, al abrir el proyecto con Android Studio, debería funcionar
o intente sincronizar y ejecutar la aplicación.

Caracteristicas del proyecto:
* Se implemento arquitectura MVVM
* Login/sigup con email$password de firebase
* Se utiliza RTDB para la persistencia de datos
* Se guardan los datos del usuario en RTDB
* Se guardan y se listan equipos pokemon en RTDB
* Se emplemento glide para mostrar imagenes de pokemon
* Se emplemento retrofit para cargar datos de https://pokeapi.co/
* Se implemento Crashlytics 
* Esquema utilizado en RTDB:
Users {
    id {
        id
        email
        name 
        teams {
              teamId {
                    id
                    name
                    pokemon {
                        index { 
                            pokedexDescription
                            pokemonImg
                            pokemonName
                            pokemonNumber
                            pokemonType
                        }
                    } 
                } 
            }
        }
    }
}
  
