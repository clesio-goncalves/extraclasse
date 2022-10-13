<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Listar Turmas</title>
<c:import url="../componentes/css_data_table.jsp" />
<c:import url="../componentes/cabecalho.jsp" />

<div class="container">

	<div class="card border-light mb-3">
		<div class="card-header">Listagem de Turmas</div>

		<!-- Table -->
		<div class="card-body" id="tabela">
			<table id="tabela_id"
				class="table table-striped table-bordered dt-responsive nowrap"
				style="width: 100%">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Ativa</th>
						<th>A��es</th>
					</tr>
				</thead>
				<tbody>
					<!-- percorre turmas montando as linhas da tabela -->
					<c:forEach var="turma" items="${turmas}">
						<tr>
							<td>${turma.id}</td>
							<td>${turma.nome}</td>

							<!-- Ativo -->
							<c:if test="${turma.ativo eq true}">
								<td>Sim</td>
							</c:if>
							<c:if test="${turma.ativo eq false}">
								<td>N�o</td>
							</c:if>

							<td>
								<!-- Exibir --> <a
								href="<c:url value="/turma/exibe?id=${turma.id}"/>"
								class="btn btn-info btn-sm" data-tooltip="tooltip"
								data-placement="bottom" title="Exibir"> <span
									class="glyphicon glyphicon-search"></span></a> <!-- Editar --> <a
								href="<c:url value="/turma/edita?id=${turma.id}" />"
								class="btn btn-warning btn-sm" data-tooltip="tooltip"
								data-placement="bottom" title="Editar"><span
									class="glyphicon glyphicon-pencil"></span> </a> <!-- Excluir -->
								<button type="button" class="btn btn-danger btn-sm"
									data-tooltip="tooltip" data-placement="bottom" title="Excluir"
									data-toggle="modal" data-target="#modal${turma.id}">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
								<div class="modal fade" id="modal${turma.id}">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">Exclus�o da turma</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<p>
													Deseja realmente excluir a turma <br>ID (${turma.id})
													-> ${turma.nome}?
												</p>
											</div>
											<div class="modal-footer">
												<a href="<c:url value="/turma/remove?id=${turma.id}" />"
													class="btn btn-danger"><span
													class="glyphicon glyphicon-trash"></span> Excluir</a>
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">
													<span class="glyphicon glyphicon-log-out"></span> Fechar
												</button>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div align="center">
		<a href="<c:url value="/turma/nova" />" class="btn btn-primary btn-lg"><span
			class="glyphicon glyphicon-plus"></span> Cadastrar</a>
	</div>
</div>

<c:import url="../componentes/js_data_table.jsp" />
<c:import url="../componentes/rodape.jsp" />