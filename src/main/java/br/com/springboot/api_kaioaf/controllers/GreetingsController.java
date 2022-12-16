package br.com.springboot.api_kaioaf.controllers;

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



import br.com.springboot.api_kaioaf.model.Usuario;
import br.com.springboot.api_kaioaf.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Minha Api de java Bem Vindo " + name + "!";
    }
    
    
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)    
    public String retornaOlaMundo(@PathVariable  String nome) {
    	
    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuarioRepository.save(usuario);
    	
    	
    	return "Ola mundo " + nome;
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<java.util.List<Usuario>> listaUsuario(){
		
    	java.util.List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<java.util.List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "gravar")
    @ResponseBody
    public ResponseEntity<Usuario> gravar(@RequestBody Usuario usuario){
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Campo id é obrigatorio para atualização", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Usuário deletado com sucesso ", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "name") String nome){
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

}
