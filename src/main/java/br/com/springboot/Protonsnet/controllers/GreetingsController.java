package br.com.springboot.Protonsnet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.Protonsnet.model.Usuario;
import br.com.springboot.Protonsnet.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* Injeção de dependência (IC/CD/CDI)*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
		return "Olá mundo " + nome;
    	
    }
    
    /*
     * listaUsuario
     * Método de listar os usuários da API
     * Retorna uma lista de todos os usuários.
     */
    @GetMapping(value = "listatodos")
    @ResponseBody //Retorna os dados parao corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	//Executa consulta no banco de dados
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	//Retorna a lista em JSON
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
}
