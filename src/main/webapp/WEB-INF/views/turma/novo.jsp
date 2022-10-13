<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Turma</title>

<c:import url="../componentes/cabecalho.jsp" />
<div class="jumbotron">
	<div class="container">
		<h1>Cadastrar Turma</h1>
		<p class="lead">Preencha o formul�rio abaixo para realizar o
			cadastro do turma no sistema.</p>
	</div>
</div>
<div class="container">
	<form action="adiciona" method="POST">

		<div class="form-row">
			<!-- ANO INGRESSO -->
			<div class="col-md-6 form-group">
				<label class="col-form-label" for="ano_ingresso">Ano<span
					class="obrigatorio">*</span></label>
				<div class="form-group">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
						<select class="custom-select" name="ano_ingresso" required
							autofocus>
							<c:forEach var="ano" items="${lista_anos}">
								<option value="${ano}">${ano}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<!-- PERIODO-->
			<div class="col-md-3 form-group">
				<label for="periodo_ingresso" class="col-form-label">Per�odo<span
					class="obrigatorio">*</span></label> <select class="custom-select"
					name="periodo_ingresso" required>
					<option value="1">1� Semestre</option>
					<option value="2">2� Semestre</option>
				</select>
			</div>

			<!-- TIPO TURMA -->
			<div class="col-md-3 form-group">
				<label for="tipo_turma" class="col-form-label">Tipo<span
					class="obrigatorio">*</span></label> <select class="custom-select"
					name="tipo_turma" required>
					<option value="�nica">�nica</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					<option value="E">E</option>
				</select>
			</div>

		</div>

		<!-- CURSO -->
		<div class="form-group">
			<label for="curso.id" class="col-form-label">Curso<span
				class="obrigatorio">*</span></label>
			<c:forEach var="curso" items="${cursos}">
				<div class="custom-control custom-radio">
					<input type="radio" id="${curso.id}" name="curso.id"
						value="${curso.id}" class="custom-control-input" checked required>
					<label class="custom-control-label" for="${curso.id}">${curso.nome}</label>
				</div>
			</c:forEach>
		</div>

		<input type="hidden" name="nome" value="nome">

		<!-- ATIVO -->
		<div class="form-group">
			<div class="custom-control custom-checkbox">
				<input type="checkbox" class="custom-control-input"
					id="customCheck1" name="ativo" checked="checked"> <label
					class="custom-control-label" for="customCheck1">Ativa</label>
			</div>
		</div>

		<security:csrfInput />

		<!-- OBTIGAT�RIO -->
		<label for="obrigatorio">(*) Campo obrigat�rio</label>
		<div>
			<button type="reset" class="btn btn-secondary btn-lg">
				<span class="glyphicon glyphicon-trash"></span> Limpar
			</button>
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-floppy-disk"></span> Salvar
			</button>
		</div>
	</form>
</div>

<c:import url="../componentes/rodape.jsp" />