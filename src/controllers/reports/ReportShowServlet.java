package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Approval;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportShowServlet
 */
@WebServlet("/reports/show")
public class ReportShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

		//承認情報を取得
		Approval a = em.createNamedQuery("getLatestStatus", Approval.class)
										.setParameter("report", r)
										.getSingleResult();

		//プロジェクトIDを取得（プロジェクト詳細リターン用）
		if(request.getParameter("project_id") != null){
			request.setAttribute("project_id", request.getParameter("project_id"));
		}


		em.close();

		request.setAttribute("report", r);
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("approval", a);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
		rd.forward(request, response);

	}

}
