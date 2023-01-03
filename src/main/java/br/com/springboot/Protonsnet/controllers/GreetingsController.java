package br.com.springboot.Protonsnet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 
     */
    @GetMapping(value = "listatodos")
    @ResponseBody //Retorna os dados parao corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	//Executa consulta no banco de dados
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	//Retorna a lista em JSON
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
    
    /*
     * salvar
     * Método de salvar os dados do usuário
     */
    @PostMapping(value = "salvar")
    @ResponseBody//Descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    /*
     * delete
     * Método que elimina o usuário do banco
     */
    @DeleteMapping(value = "delete")
    @ResponseBody //Retorna a descrição da resposta
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    }
    
    /*
     * buscarusuario
     * Procura usuário pelo id
     */
    @GetMapping(value = "buscarusuario")
    @ResponseBody //Retorna a descrição da resposta
    public ResponseEntity<Usuario> buscarusuario(@RequestParam(name = "iduser") Long iduser){
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    /*
     * atualizar
     * Atualiza as modificações nos dados do usuário
     */
    @PutMapping(value = "atualizar")
    @ResponseBody //Retorna a descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	if(usuario.getId()==null) {
    		return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    /*
     * buscarPorNome
     * Busca usuário por nome
     */
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}
