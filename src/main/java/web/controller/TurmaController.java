package web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dao.CursoDao;
import web.dao.TurmaDao;
import web.modelo.Curso;
import web.modelo.Turma;

@Transactional
@Controller
@RequestMapping("/turma")
public class TurmaController {

	private List<Turma> lista_turmas;
	private List<Curso> lista_curso;
	private List<Integer> lista_anos;
	private Turma turma;

	@Autowired
	TurmaDao dao;

	@Autowired
	CursoDao dao_curso;

	public TurmaController() {
		this.lista_anos = new ArrayList<>();
	}

	@RequestMapping("/nova")
	public String novaTurma(Model model) {
		this.lista_curso = dao_curso.lista();
		if (this.lista_curso.size() == 0) {
			return "redirect:/curso/novo";
		}

		listaUltimosCincoAnos();
		model.addAttribute("lista_anos", this.lista_anos);
		model.addAttribute("cursos", this.lista_curso);
		return "turma/novo";
	}

	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public String adiciona(@Valid Turma turma, BindingResult result) {
		// Altera o nome da turma
		turma.setNome(dao_curso.buscaNomePorId(turma.getCurso().getId()) + " - " + turma.getAno_ingresso() + "."
				+ turma.getPeriodo_ingresso() + " - " + turma.getTipo_turma());

		if (result.hasErrors()) {
			return "redirect:nova";
		} else if (dao.buscaPorNome(turma.getNome()).size() > 0) {
			return "redirect:nova";
		}

		// Adiciona no banco de dados
		dao.adiciona(turma);
		return "redirect:lista";
	}

	@RequestMapping("/lista")
	public String lista(Model model) {
		listaUltimosCincoAnos();
		model.addAttribute("cursos", dao_curso.lista());
		model.addAttribute("lista_anos", this.lista_anos);
		model.addAttribute("turmas", dao.listaTurmas());
		return "turma/lista";
	}

	@RequestMapping("/remove")
	public String remove(Turma turma) {
		dao.remove(turma.getId());
		return "redirect:lista";
	}

	@RequestMapping("/exibe")
	public String exibe(Long id, Model model) {
		model.addAttribute("turma", dao.buscaPorId(id));
		return "turma/exibe";
	}

	@RequestMapping("/edita")
	public String edita(Long id, Model model) {

		// Busca a turma e altera o ano e periodo de ingresso
		this.turma = dao.buscaPorId(id);
		String nome[] = this.turma.getNome().replace(".", "#").split(" - ");
		String ingresso[] = nome[1].split("#");
		this.turma.setAno_ingresso(Integer.parseInt(ingresso[0]));
		this.turma.setPeriodo_ingresso(Integer.parseInt(ingresso[1]));
		this.turma.setTipo_turma(nome[2]);

		model.addAttribute("cursos", dao_curso.lista());
		model.addAttribute("turma", this.turma);
		model.addAttribute("lista_anos", this.lista_anos);
		return "turma/edita";
	}

	@RequestMapping(value = "/altera", method = RequestMethod.POST)
	public String altera(@Valid Turma turma, BindingResult result) {
		// Altera o nome da turma
		turma.setNome(dao_curso.buscaNomePorId(turma.getCurso().getId()) + " - " + turma.getAno_ingresso() + "."
				+ turma.getPeriodo_ingresso() + " - " + turma.getTipo_turma());

		this.lista_turmas = dao.buscaPorNome(turma.getNome());
		if (result.hasErrors()) {
			return "redirect:edita?id=" + turma.getId();
		} else if (this.lista_turmas.size() > 0 && this.lista_turmas.get(0).getId() != turma.getId()) {
			return "redirect:edita?id=" + turma.getId();
		}

		dao.altera(turma);
		return "redirect:lista";
	}

	private List<Integer> listaUltimosCincoAnos() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);

		for (int i = 0; i < 6; i++) {
			this.lista_anos.add(ano);
			ano = ano - 1;
		}
		return this.lista_anos;
	}

}
