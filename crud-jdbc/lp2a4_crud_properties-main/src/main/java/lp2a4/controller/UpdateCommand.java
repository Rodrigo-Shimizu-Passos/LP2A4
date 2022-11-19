package lp2a4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lp2a4.Logger;
import lp2a4.modelo.AlunoDAO;
import lp2a4.modelo.AlunoPOJO;

public class UpdateCommand implements Command {
	
	private static Logger log = new Logger(Command.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AlunoPOJO aluno = ControllerUtil.transform(request);
		
		final AlunoDAO dao = AlunoDAO.getInstance();
		
		if (dao.update(aluno)) {
			response.getWriter().append("Aluno atualizado com sucesso!");
			log.info("UPDATE OK!");
		} else {
			response.getWriter().append("Não foi possível atualizar o aluno :(");
			log.info("UPDATE FAILED!");
		}
	}
	}

