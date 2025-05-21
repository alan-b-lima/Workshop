package model.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Classe utilitária para conversão de objetos de e para JSON. Utiliza a
 * biblioteca Gson para realizar a conversão.
 * 
 * @author Alan Lima
 * 
 * @see com.google.gson.Gson
 * @see com.google.gson.reflect.TypeToken
 */
public class Json {

    /**
     * Instância do Gson para conversão de objetos de e para JSON.
     * 
     * @see com.google.gson.Gson
     */
    public static final Gson GSON = new Gson();

    /**
     * Construtor privado para evitar instanciação da classe.
     */
    private Json() {

    }

    /**
     * Converte um objeto para um JSON.
     * 
     * @param <T>    tipo do objeto a ser convertido.
     * @param object objeto a ser convertido.
     * @return JSON convertido.
     * 
     * @see com.google.gson.Gson#toJson(T)
     */
    public static <T> String stringify(T object) {
        return GSON.toJson(object);
    }

    /**
     * Converte um JSON para um objeto do tipo T.
     * O tipo T deve ser conhecido em tempo de execução.
     * O tipo T deve ser uma classe plana.
     * 
     * @param <T>  tipo do objeto a ser convertido.
     * @param json JSON a ser convertido.
     * @return objeto convertido.
     * 
     * @see com.google.gson.Gson#fromJson(String, Class)
     */
    public static <T> T parse(String json) {
        TypeToken<T> type = new TypeToken<>() {};
        return GSON.fromJson(json, type);
    }

    /**
     * Converte um JSON para um objeto do tipo T.
     * O tipo T deve ser conhecido em tempo de execução.
     * 
     * @param <T>   tipo do objeto a ser convertido.
     * @param json  JSON a ser convertido.
     * @param clazz classe do objeto a ser convertido.
     * @return objeto convertido.
     * 
     * @see com.google.gson.Gson#fromJson(String, Class)
     */
    public static <T> T parse(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * Converte um JSON para um objeto do tipo T.
     * O tipo T deve ser conhecido em tempo de execução.
     * 
     * @param <T>  tipo do objeto a ser convertido.
     * @param json JSON a ser convertido.
     * @param type tipo do objeto a ser convertido.
     * @return objeto convertido.
     * 
     * @see com.google.gson.Gson#fromJson(String, TypeToken)
     */
    public static <T> T parse(String json, TypeToken<T> type) {
        return GSON.fromJson(json, type);
    }
}
